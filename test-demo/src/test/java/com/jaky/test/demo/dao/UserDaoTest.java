package com.jaky.test.demo.dao;

import java.util.List;

import com.jaky.test.demo.entity.UserInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by jaky.wang on 2017/9/30.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(scripts = {"classpath:sql/create_table.sql", "classpath:sql/insert_data.sql"})
@SqlConfig(dataSource = "dataSource")
public class UserDaoTest {

  private static final Logger LOG = LoggerFactory.getLogger(UserDaoTest.class);

  @Resource
  private UserDao userDao;

  @Value("#{'${zh.test:}'.split(',')}")
  private List<String> zhs;

  @Test
  public void find_one_test() {
    UserInfo info = userDao.findByid(1);
    Assert.assertNotNull(info);
    Assert.assertEquals(1, info.getId());
    Assert.assertEquals("jaky.wang", info.getName());
  }

  @Test
  public void zh_test() {
    System.out.println(zhs);
  }
}
