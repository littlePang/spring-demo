package com.jaky.spring.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 * Created by jaky on 1/16/17.
 */
@Service
public class TestService {

    private static Logger logger = LoggerFactory.getLogger(TestService.class);

    @Value("${test.redirect.url}")
    private String redirectUrl;

    public void redirect() {
        logger.info("redirect to url : {}", redirectUrl);
    }

    @Bean
    public TwoService twoTwoService() {
        return new TwoService();
    }

}
