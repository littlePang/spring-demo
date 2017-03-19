package com.jaky.spring.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jaky on 2/20/17.
 */
public class SystemInfoQueryTest {

    private static final Logger logger = LoggerFactory.getLogger(SystemInfoQueryTest.class);

    @Test
    public void system_properties_test() {
        logger.info("system properties : {}", System.getProperties());
    }

    @Test
    public void system_env_test() {
        logger.info("system env : {}", System.getenv());
    }
}
