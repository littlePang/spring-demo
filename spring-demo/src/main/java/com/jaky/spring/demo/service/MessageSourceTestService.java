package com.jaky.spring.demo.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Locale;

/**
 * Created by jaky on 3/6/17.
 */
@Service
public class MessageSourceTestService {

    @Resource
    private MessageSource messageSource;

    public String doProcessMessage() {
        String msg = messageSource.getMessage("message.key", new Object[]{new Date()}, Locale.CHINESE);
        System.out.println(msg);
        return msg;
    }

}
