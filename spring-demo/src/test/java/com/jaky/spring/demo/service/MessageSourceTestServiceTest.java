package com.jaky.spring.demo.service;

import com.jaky.spring.demo.BaseConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by jaky on 3/6/17.
 */
public class MessageSourceTestServiceTest extends BaseConfig {

    public static final Logger logger = LoggerFactory.getLogger(MessageSourceTestService.class);

    @Resource
    private MessageSourceTestService messageSourceTestService;

    @Test
    public void doProcessMessage() throws Exception {

        String msg = messageSourceTestService.doProcessMessage();
        logger.info("process message result : {}", msg);
    }

}