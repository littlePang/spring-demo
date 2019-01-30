package com.jaky.spring.cloud.feign.client;

import com.netflix.client.config.IClientConfig;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
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
    ConfigurableApplicationContext ac = SpringApplication.run(Application.class);
    IClientConfig bean = ac.getBean(IClientConfig.class);
    System.out.println(bean.getClientName());
    System.out.println(ToStringBuilder.reflectionToString(bean));
  }

}
