package com.jaky.spring.cloud.feign.client;

import javax.net.ssl.SSLContext;

import feign.Client;
import feign.httpclient.ApacheHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.feign.ribbon.CachingSpringLoadBalancerFactory;
import org.springframework.cloud.netflix.feign.ribbon.LoadBalancerFeignClient;
import org.springframework.cloud.netflix.ribbon.SpringClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rx.Observable;
import rx.Observer;

/**
 * @author xiaomo.wj
 * @date 2019-01-11.
 */
@Configuration
public class AppConfiguration {
  @Bean
  public Client feignClient(CachingSpringLoadBalancerFactory cachingFactory,
                            SpringClientFactory clientFactory) {

    CloseableHttpClient client = HttpClients.custom()
        .setRedirectStrategy(new LaxRedirectStrategy())
        .setMaxConnPerRoute(300)
        .setMaxConnTotal(2000)
        .build();
    return new LoadBalancerFeignClient(new ApacheHttpClient(client),
        cachingFactory, clientFactory);

  }

  public static void main(String[] args) {
    Observable<String> observable = Observable.create(s -> {
      s.onNext("hello world");
      s.onNext("wang jie");
      throw new RuntimeException("i don't wanna success");
      //s.onCompleted();
    });

    Observer<String> observer = new Observer<String>() {
      @Override
      public void onCompleted() {
        System.out.println("onCompleted");
      }

      @Override
      public void onError(Throwable e) {
        System.out.println("onError:"+e);
      }

      @Override
      public void onNext(String s) {
        System.out.println("onNext:"+s);
      }
    };
    observable = observable.retry((r, t) -> {
      System.out.println("重试第" + r + "次");
      return r < 5;
    });


    observable.subscribe(observer);

  }
}
