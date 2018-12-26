package com.jaky.spring.redis.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author xiaomo.wj
 * @date 2018/9/18.
 */
public class Application {

  public static void main(String[] args) throws InterruptedException {

    new RuntimeException().getStackTrace();

    ExecutorService executorService = Executors.newFixedThreadPool(20);

    for (int i = 0; i < 20; i++) {
      executorService.submit(()-> {
        int times = 0;
        RedisLock lock = new RedisLock("127.0.0.1", 6379);

        do {
          try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " get this lock");
            TimeUnit.SECONDS.sleep(50);
            System.out.println(Thread.currentThread().getName() + " release this lock");
            lock.unlock();
          } catch (Exception e) {
            e.printStackTrace();
          } finally {
            lock.unlock();
          }
        } while (times++ < 5);
      });
    }

    executorService.shutdown();
    executorService.awaitTermination(10, TimeUnit.MINUTES);

  }




}
