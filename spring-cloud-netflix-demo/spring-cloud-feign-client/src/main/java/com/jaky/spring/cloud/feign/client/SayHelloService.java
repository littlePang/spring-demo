package com.jaky.spring.cloud.feign.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author xiaomo.wj
 * @date 2018/7/27.
 */
@FeignClient("feign-rpc-server")
public interface SayHelloService {

  @RequestMapping("hello")
  String say();

}
