package com.jaky.spring.redis.demo;

/**
 * @author xiaomo.wj
 * @date 2018/9/19.
 */
public interface Lock {

  void lock();

  boolean unlock();

}
