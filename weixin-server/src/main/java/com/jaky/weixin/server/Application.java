package com.jaky.weixin.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by xiaomo.wj on 2018/4/15.
 */
@SpringBootApplication
@Controller
public class Application {

  private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }

  @RequestMapping("saveUserInfo")
  @ResponseBody
  public String saveUserInfo(String userName) {
    LOGGER.info("save user info , user name is {}", userName);
    return "success";
  }


}
