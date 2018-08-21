package com.jaky.spring.reactor.demo;

import java.io.File;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.IntegrationFlows;
//import org.springframework.integration.dsl.channel.MessageChannels;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;

/**
 * @author xiaomo.wj
 * @date 2018/8/7.
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).run(args);
    }

    //@GetMapping(value="files/{name}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    //Flux<String> files(@PathVariable String name) {
    //    return Flux.create((FluxSink<String> sink) -> {
    //        FluxSink<String> serialize = sink;
    //        MessageHandler handler = msg -> serialize.next(String.class.cast(msg.getPayload()));
    //        serialize.onCancel(() -> filesChannel().unsubscribe(handler));
    //        filesChannel().subscribe(handler);
    //    });
    //}
    //
    //@Bean
    //public IntegrationFlow inboundFlow(@Value("${input-dir:file://${HOME}/Desktop/in}") File in) {
    //    System.out.println(in.getAbsolutePath());
    //    return IntegrationFlows.from(Files.newInputStream(in.toPath()).autoCreateDirectory(true), poller -> poller.poller(spec -> spec.fixedRate(1000L)))
    //        .transform(File.class, File::getAbsolutePath)
    //        .channel(filesChannel())
    //        .get();
    //}
    //
    //@Bean
    //SubscribableChannel filesChannel() {
    //    return MessageChannels.publishSubscribe().get();
    //}

}
