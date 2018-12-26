package com.jaky.spring.cloud.feign.client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xiaomo.wj
 * @date 2018/7/27.
 */
@Component
public class InvokeTestService implements CommandLineRunner {

  @Resource
  private SayHelloService sayHelloService;

  @Override
  public void run(String... args) throws Exception {
    String say = sayHelloService.say();
    System.out.println(say);
  }
}
