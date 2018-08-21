package com.jaky.spring.reactor.demo.controller;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author xiaomo.wj
 * @date 2018/8/7.
 */
@RestController
public class DataController {

    @GetMapping("/data")
    public Mono<String> data() {
        return Mono.<String>create(c -> c.success("hello reactor")).delaySubscription(
            Duration.of(10, ChronoUnit.SECONDS));
    }

}
