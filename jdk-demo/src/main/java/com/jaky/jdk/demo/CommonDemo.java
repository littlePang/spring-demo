package com.jaky.jdk.demo;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.StringJoiner;

/**
 * @author xiaomo.wj
 * @date 2018/9/1.
 */
public class CommonDemo {

  public static void main(String[] args) {

    B b = new B();
    Arrays.asList(b.getClass().getDeclaredFields()).forEach(System.out::println);

    A ba = new B();
    Arrays.asList(ba.getClass().getDeclaredFields()).forEach(System.out::println);

    System.out.println("getDeclaringClass");
    System.out.println(CommonDemo.class.getDeclaringClass());
    System.out.println("getEnclosingClass");
    System.out.println(CommonDemo.class.getEnclosingClass());
    System.out.println("classes");
    Arrays.asList(ba.getClass().getClasses()).forEach(System.out::println);
    System.out.println("getInterfaces");
    Arrays.asList(ba.getClass().getAnnotatedInterfaces()).forEach(System.out::println);


  }

  public static class A {
    private Date aDate;
  }

  public static class B extends A {
    private Date bDate;
  }

}
