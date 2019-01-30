package com.jaky.jdk.demo;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
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
    public String name = "D";
     String getName() {
      return name;
    }
    public void hello() {
      System.out.println("hello D");
    }
  }

  static class C extends D  {
    //private String name = "C";

  }

  public static void main(String[] args) throws  Exception{
    Field name = C.class.getField("name");
    name.setAccessible(true);
    System.out.println(name.get(new C()));
  }
}
