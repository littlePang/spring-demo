package com.jaky.spring.cloud.feign.client;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xiaomo.wj
 * @date 2018/7/27.
 */
@Component
public class InvokeTestService {

  @Resource
  private SayHelloService sayHelloService;

  @Scheduled(fixedRate = 5000, initialDelay = 10000)
  public void f() {
    System.out.println("execute service");
    System.out.println(sayHelloService.say());

  }

}
