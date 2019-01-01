package com.xiaomo.spring.jta;

import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 * @author xiaomo.wj
 * @date 2019-01-01.
 */
@Configuration
@EnableTransactionManagement
public class AppConfiguration {

  // =========== 第一个 数据源 ==========

  @Bean
  @Primary
  @ConfigurationProperties("spring.jta.atomikos.datasource.db1")
  public AtomikosDataSourceBean db1() {
    return new AtomikosDataSourceBean();
  }

  @Bean
  public JdbcTemplate jdbcTemplate1() {
    return new JdbcTemplate(db1());

  }

  // =========== 第二个 数据源 ==========

  @Bean
  @ConfigurationProperties("spring.jta.atomikos.datasource.db2")
  public AtomikosDataSourceBean db2() {
    return new AtomikosDataSourceBean();
  }

  @Bean
  public JdbcTemplate jdbcTemplate2() {
    return new JdbcTemplate(db2());

  }

  // ============ 事务管理器 ==========

  @Bean(destroyMethod = "close", initMethod = "init")
  public UserTransactionManager userTransactionManager() {
    UserTransactionManager userTransactionManager = new UserTransactionManager();
    userTransactionManager.setForceShutdown(false);
    return userTransactionManager;
  }

  @Bean
  public JtaTransactionManager transactionManager() {
    JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
    jtaTransactionManager.setTransactionManager(userTransactionManager());
    return jtaTransactionManager;
  }



}
