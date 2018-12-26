package com.jaky.jdk.demo;

import java.util.Arrays;

/**
 * @author xiaomo.wj
 * @date 2018/11/21.
 */
public class ExtendTest {

  interface A {
    default void hello() {
      System.out.println("hello A");
    }
  }

  interface B extends A{
    default void hello() {
      System.out.println("hello B");
    }
  }

  static class D implements A {
    public void hello() {
      System.out.println("hello D");
    }
  }

  static class C extends D  {

  }

  public static void main(String[] args) {
    System.out.println(Arrays.toString(C.class.getInterfaces()));
  }
}
