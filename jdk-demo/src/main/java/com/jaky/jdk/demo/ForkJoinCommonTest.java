package com.jaky.jdk.demo;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

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

    /**
     * 当前计算机8核，8个逻辑CPU，默认common线程池的并发度为7，所以第8个任务会在前7个任务中有处理完成之后，才会继续执行。
     * 即：在使用 Stream.parallel() 并发执行任务时，
     * 可能会出现 其他线程执行的 Stream.parallel() 影响到当前线程的并发处理，从而导致阻塞发生。
     */
    for (int i = 0; i < 8; i++) {
      new SleepRecursiveAction(i+1, 5).fork();
    }


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
