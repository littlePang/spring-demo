package com.jaky.spring.redis.demo;

import java.util.Collections;

import redis.clients.jedis.Jedis;

/**
 * @author xiaomo.wj
 * @date 2018/9/19.
 */
public class RedisLock implements Lock {

  private static final String LOCK_KEY = "lock_key";
  private static final String SUCCESS = "OK";

  private final Jedis JEDIS;

  private int maxWaitLockTime = 60000; // 最长锁等待时间，1min

  private ThreadLocal<Long> lockTime = new ThreadLocal<>();

  public RedisLock(String ip, int port) {
    JEDIS = new Jedis(ip, port);
  }

  public void lock2() {
    // 从 2.6.12 开始，可以在set上加上这些选项，所以set已经可以完全取代 setnx, setex, psetex 的功能
    // http://redis.cn/commands/set.html

    while (true) {
      long nowTime = System.currentTimeMillis();
      if (SUCCESS.equalsIgnoreCase(JEDIS.set(LOCK_KEY, Long.toString(nowTime), "nx", "px", 60000))) {
        lockTime.set(nowTime);
        return;
      }
    }

  }

  /**
   * 分布式锁 博客：https://wudashan.cn/2017/10/23/Redis-Distributed-Lock-Implement/
   *
   * 在上面的博客中提到下面这种方式的三个问题：
   * 1. 由于是客户端自己生成过期时间，所以需要强制要求分布式下每个客户端的时间必须同步 (这个确实是个问题，没法保证每个客户端的时候都是同步的，不同步可能导致多个客户端同时持有锁)
   * 2. 当锁过期的时候，如果多个客户端同时执行jedis.getSet()方法，那么虽然最终只有一个客户端可以加锁，但是这个客户端的锁的过期时间可能被其他客户端覆盖
   * (这个应该在可接受范围内，如果能同时抢占锁，那肯定是在一个极短的时间内，不过话又说回来，如果是上面那个客户端时间不同步的情况下呢？)
   * 3. 锁不具备拥有者标识，即任何客户端都可以解锁。(这个我理解没啥问题，解锁的时候肯定会有个判断是否仍是当前客户端加锁的逻辑，时间戳啥的)
   *
   * 所以上面那个博客推荐使用 上面 {@link RedisLock#lock2()} 方法的实现来处理
   */
  @Override
  public void lock() {
    while (true) {
      // 尝试获取锁，如果获取成功，直接返回
      long nowTime = System.currentTimeMillis();
      Long lockResult = JEDIS.setnx(LOCK_KEY, Long.toString(nowTime));
      if (lockResult.equals(1L)) {
        lockTime.set(nowTime);
        return;
      }

      // 获取失败，则查看是否锁等待超时
      String preLockTime = JEDIS.get(LOCK_KEY);
      // 获取不到上次锁等待时间，则继续尝试加锁
      if (preLockTime == null) {
        continue;
      }

      // 超过最长等待时间, 则尝试强制获取锁
      if (Long.valueOf(preLockTime) + maxWaitLockTime < nowTime) {
        String oldLockTime = JEDIS.getSet(LOCK_KEY, Long.toString(nowTime));
        // 强制获取锁，如果得到的上次加锁时间与上面获取的一致，则说明其他线程没有抢占到锁，本线程抢占到了锁
        if (oldLockTime != null && preLockTime.equals(oldLockTime)) {
          lockTime.set(nowTime);
          return;
        }
      }
    }
  }

  @Override
  public boolean unlock() {
    if (lockTime.get() == null) {
      return false;
    }

    // 使用lua代码，在redis中保证它的原子性
    String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
    Object result = JEDIS.eval(script, Collections.singletonList(LOCK_KEY),
        Collections.singletonList(Long.toString(lockTime.get())));

    if (SUCCESS.equals(result)) {
      return true;
    }
    return false;
  }
}
