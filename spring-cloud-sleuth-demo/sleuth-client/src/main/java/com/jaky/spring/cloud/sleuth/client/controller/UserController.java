package com.jaky.spring.cloud.sleuth.client.controller;

import com.jaky.spring.cloud.sleuth.client.dao.UserDao;
import com.jaky.spring.cloud.sleuth.client.entity.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * Created by xiaomo.wj on 2018/1/31.
 */
@RestController
@RequestMapping("user")
public class UserController {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

  @Resource
  private UserDao userDao;

  @RequestMapping("find")
  public UserInfo findByUserInfo(int id) {
    return userDao.findById(id);
  }

}
