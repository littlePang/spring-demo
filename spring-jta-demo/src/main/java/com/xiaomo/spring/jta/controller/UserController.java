package com.xiaomo.spring.jta.controller;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaomo.wj
 * @date 2018-12-31.
 */
@RestController
public class UserController {

  @Resource
  private JdbcTemplate jdbcTemplate1;

  @Resource
  private JdbcTemplate jdbcTemplate2;

  @RequestMapping("create")
  @Transactional
  public String create(String name, String address) {
    jdbcTemplate1.batchUpdate(String.format("insert into name(name) value('%s')", name));
    jdbcTemplate2.batchUpdate(String.format("insert into address(address) value('%s')", address));
    return "success";
  }

}
