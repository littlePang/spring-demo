package com.jaky.test.demo.config;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by jaky.wang on 2017/9/30.
 */
@Configuration
public class BeanConfig {

  private static final Logger LOG = LoggerFactory.getLogger(BeanConfig.class);

  @Bean
  public DataSource dataSource(PoolProperties poolProperties) {
    LOG.info("database properties detail {}", poolProperties);
    return new org.apache.tomcat.jdbc.pool.DataSource(poolProperties);
  }

  @Bean
  @ConfigurationProperties("databaseProperties")
  public PoolProperties databaseProperties() {
    return new PoolProperties();
  }
}
