package com.jaky.spring.demo;

import com.jaky.spring.demo.config.AppConfig;
import com.jaky.spring.demo.service.TestService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * Created by jaky on 1/17/17.
 */
public class ContainerInitTest {

    private static Logger logger = LoggerFactory.getLogger(ContainerInitTest.class);

    @Test
    public void classpath_init_test() {
        ListableBeanFactory ac = new ClassPathXmlApplicationContext("spring.xml");
        String[] definitionNames = ac.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            logger.info("beanDefinitionName {}", definitionName);
        }

        TestService service = ac.getBean(TestService.class);
        service.redirect();
    }

    @Test
    public void annotation_init_test() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        String[] definitionNames = ac.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            logger.info("beanDefinitionName {}", definitionName);
        }

        TestService service = ac.getBean(TestService.class);
        service.redirect();
    }

    @Test
    public void file_init_test() {
        ApplicationContext ac = new FileSystemXmlApplicationContext("/src/main/resources/spring.xml");
        String[] definitionNames = ac.getBeanDefinitionNames();
        for (String definitionName : definitionNames) {
            logger.info("beanDefinitionName {}", definitionName);
        }

        TestService service = ac.getBean(TestService.class);
        service.redirect();
    }

}
