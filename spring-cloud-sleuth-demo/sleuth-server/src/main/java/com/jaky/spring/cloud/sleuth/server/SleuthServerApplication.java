package com.jaky.spring.cloud.sleuth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;

/**
 * Created by xiaomo.wj on 2018/1/29.
 */
@SpringBootApplication
@EnableZipkinStreamServer
public class SleuthServerApplication {

  public static void main(String[] args) {
    SpringApplication.run(SleuthServerApplication.class, args);
  }
}
