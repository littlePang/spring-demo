package com.jaky.spring.cloud.feign.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author xiaomo.wj
 * @date 2018/7/27.
 */
@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableScheduling
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

}
