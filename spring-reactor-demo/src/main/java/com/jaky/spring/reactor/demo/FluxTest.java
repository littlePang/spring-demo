package com.jaky.spring.reactor.demo;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Hooks;

/**
 * @author xiaomo.wj
 * @date 2018/8/7.
 */
public class FluxTest {
    public static void main(String[] args) throws InterruptedException {
        Hooks.onOperatorDebug();
        Flux.range(1, 100).buffer(20).subscribe(System.out::println);
        System.out.println("line");
        Flux.interval(Duration.of(100, ChronoUnit.MILLIS)).buffer(Duration.of(1001, ChronoUnit.MILLIS)).take(2).toStream().forEach(System.out::println);
        System.out.println("line");
        Flux.range(1, 10).bufferUntil(i -> i % 2 == 0).subscribe(System.out::println);
        System.out.println("line");
        Flux.range(1, 10).bufferWhile(i -> i % 2 == 0).subscribe(System.out::println);
        System.out.println("line");

        Flux.range(1,100).window(10,2).take(2).toStream().forEach(System.out::println);
        System.out.println("line");

        Flux.just("a", "b")
            .zipWith(Flux.just("c", "d"))
            .subscribe(System.out::println);
        Flux.just("a", "b")
            .zipWith(Flux.just("c", "d"), (s1, s2) -> String.format("%s-%s", s1, s2))
            .subscribe(System.out::println);
        System.out.println("line");

        final Flux<Long> source = Flux.interval(Duration.of(1, ChronoUnit.SECONDS))
            .take(10)
            .publish()
            .autoConnect();
        source.subscribe();
        TimeUnit.SECONDS.sleep(5);
        source
            .toStream()
            .forEach(System.out::println);
    }
}
