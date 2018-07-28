package com.jaky.spring.cloud.feign.server.rpc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiaomo.wj
 * @date 2018/7/27.
 */
@RestController
public class SayHelloRpcService {

  @RequestMapping("hello")
  public String hello() {
    return "hello world!";
  }

}
