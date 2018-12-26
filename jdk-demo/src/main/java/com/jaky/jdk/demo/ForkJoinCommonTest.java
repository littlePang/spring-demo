package com.jaky.jdk.demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiaomo.wj
 * @date 2018/11/28.
 */
public class ForkJoinCommonTest {

  public static void main(String[] args) throws InterruptedException {
    ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
    System.out.println(forkJoinPool.getParallelism());
    System.out.println(Runtime.getRuntime().availableProcessors());
    System.out.println(Runtime.getRuntime().freeMemory());
    System.out.println(Runtime.getRuntime().maxMemory());

    SleepRecursiveAction a1 = new SleepRecursiveAction(1, 10);
    a1.fork();

    SleepRecursiveAction a2 = new SleepRecursiveAction(2, 10);
    a2.fork();

    SleepRecursiveAction a3 = new SleepRecursiveAction(3, 10);
    a3.fork();

    TimeUnit.SECONDS.sleep(2);

    SleepRecursiveAction a4 = new SleepRecursiveAction(4, 3);
    a4.fork();

    TimeUnit.SECONDS.sleep(20);


  }

  public static class SleepRecursiveAction extends RecursiveAction {
    private int count;
    private int sleep;
    public SleepRecursiveAction(int count, int sleep) {
      super();
      this.count = count;
      this.sleep = sleep;
    }

    @Override
    protected void compute() {
      try {
        System.out.println("execute task " + count + " start");
        TimeUnit.SECONDS.sleep(sleep);
        System.out.println("execute task " + count + " end");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
