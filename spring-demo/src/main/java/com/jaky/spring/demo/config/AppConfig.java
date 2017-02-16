package com.jaky.spring.demo.config;

import com.jaky.spring.demo.service.TestService;
import com.jaky.spring.demo.service.TwoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by jaky on 1/17/17.
 */
@Configuration
@ComponentScan("com.jaky.spring.demo.service")
@PropertySource("common-config.properties")
public class AppConfig {


    @Bean
    public TwoService twoService() {
        return new TwoService();
    }

}
