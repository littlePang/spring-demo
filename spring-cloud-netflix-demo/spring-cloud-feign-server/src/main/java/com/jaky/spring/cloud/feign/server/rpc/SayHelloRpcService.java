package com.jaky.spring.cloud.feign.server.rpc;

import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author xiaomo.wj
 * @date 2018/7/27.
 */
@RestController
public class SayHelloRpcService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SayHelloRpcService.class);

  @RequestMapping("hello")
  public String hello() {
    return "hello world!";
  }

  /**
   * 尝试 每次返回部分数据，且不超过 client 端的 read time out 的情况，
   * 结果：client 端永不超时
   */
  @RequestMapping("hello2")
  public void hello2() {
    try {
      PrintWriter writer = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse()
          .getWriter();
      int t = 0;
      while (t++ < 150) {
        LOGGER.info("send message {}", t);
        writer.print("a");
        writer.flush();
        TimeUnit.SECONDS.sleep(4);
      }

    } catch (Exception e) {
      LOGGER.error("err", e);
    }
  }

}
