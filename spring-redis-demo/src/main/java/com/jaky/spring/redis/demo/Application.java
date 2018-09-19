package com.jaky.spring.redis.demo;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

/**
 * @author xiaomo.wj
 * @date 2018/9/18.
 */
public class Application {

  public static void main(String[] args) {
    Jedis jedis = new Jedis("127.0.0.1", 6379);
    jedis.set("foo", "bar");
    String value = jedis.get("foo");
    System.out.println(value);
  }




}
