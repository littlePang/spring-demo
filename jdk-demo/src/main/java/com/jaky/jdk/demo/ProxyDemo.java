package com.jaky.jdk.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author xiaomo.wj
 * @date 2018/8/21.
 */
public class ProxyDemo {

  public static void main(String[] args) {
    InvocationHandler handler = new MyInvocationHandler();
    MyInterface myInterface = (MyInterface)Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(),
        new Class<?>[] {MyInterface.class}, handler);
    System.out.println(myInterface.doProcess());
    System.out.println(myInterface.processType());
  }

  public interface MyInterface {

    String doProcess();

    ProcessType processType();
  }

  public static class MyInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      if ("doProcess".equalsIgnoreCase(method.getName())) {
        return "process by doProcess method";
      } else if ("processType".equalsIgnoreCase(method.getName())) {
        return ProcessType.INNER;
      }
      return null;
    }
  }

  public enum ProcessType {
    INNER
  }

}
