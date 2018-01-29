package com.jaky.spring.cloud.sleuth.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiaomo.wj on 2018/1/29.
 */
@Controller
@SpringBootApplication
public class SleuthClientApplication {

  public static void main(String[] args) {
    SpringApplication.run(SleuthClientApplication.class, args);
  }

  @ResponseBody
  @RequestMapping("hello")
  public String helloWorld() {
    return "hello world";
  }

}
