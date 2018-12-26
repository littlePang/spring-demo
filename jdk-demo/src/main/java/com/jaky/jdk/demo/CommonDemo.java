package com.jaky.jdk.demo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Spliterators;
import java.util.StringJoiner;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author xiaomo.wj
 * @date 2018/9/1.
 */
public class CommonDemo {

  public static void main(String[] args) {

    //test1();

    System.out.println((1<<16) -1 );
    System.out.println(0xffffL );
    System.out.println(0x7fffL );

    int parallelism = 2;
    long np = (long)(-parallelism); // offset ctl counts
    int AC_SHIFT = 48;
    long AC_MASK    = 0xffffL << AC_SHIFT;
    int  TC_SHIFT   = 32;
    long TC_MASK    = 0xffffL << TC_SHIFT;
    long ctl = ((np << AC_SHIFT) & AC_MASK) | ((np << TC_SHIFT) & TC_MASK);
    System.out.println(toBinaryString(ctl));

    long AC_UNIT    = 0x0001L << AC_SHIFT;
    long TC_UNIT    = 0x0001L << TC_SHIFT;
    long c = ctl;
    long nc = ((AC_MASK & (c + AC_UNIT)) |
        (TC_MASK & (c + TC_UNIT)));
    System.out.println(toBinaryString(nc));

    long ADD_WORKER = 0x0001L << (TC_SHIFT + 15); // sign
    long l = nc & ADD_WORKER;
    System.out.println(toBinaryString(l));

    nc = (AC_MASK & (nc + AC_UNIT)) |
        (TC_MASK & (nc + TC_UNIT));
    System.out.println(nc);

    System.out.println(nc & ADD_WORKER);

  }

  public static String toBinaryString(long l) {
    int i = 64;
    ArrayList<Long> ret = new ArrayList<>(64);
    while (i-- > 0) {
      ret.add(Math.abs(l%2));
      l = l>>1;
    }
    return ret.stream().sorted(Comparator.reverseOrder()).map(e->e.toString()).collect(Collectors.joining(""));
  }

  private static void test1() {
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
