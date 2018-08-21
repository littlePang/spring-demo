package com.jaky.spring.cloud.feign.client;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author xiaomo.wj
 * @date 2018/7/28.
 */
public class RibbonTest {

    public static void main(String[] args) throws InterruptedException {

        //barrierTest();
        //latchTest();
        //deamonThreadTest();
        //findMissNumber();

        try {
           throwError();
        } catch (Throwable t) {
            System.out.println("catch");
            t.printStackTrace();
        }
    }

    private static void throwError(){
        throw new OutOfMemoryError("oom");
    }

    private static void findMissNumber() {
        //125,126,127   125,127
        byte[] nums = new byte[]{120,121,123,124,125};

        byte ret = 0;
        byte ret2 = 0;
        for (int i = 120,j=0; i <= 125; i++) {
            ret2^=i;
            if (j >= nums.length) {

                ret += i;
            }
            if (j<nums.length) {
                ret = (byte)(ret + i - nums[j++]);
            }
        }

        for (byte num : nums) {
            ret2 ^= num;
        }

        System.out.println(ret);
        System.out.println(ret2);
    }

    private static void deamonThreadTest() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                System.out.println("start t1 " + Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                System.out.println("thread t1 finally");
            }
        });
        t1.setDaemon(true);
        t1.start();

        TimeUnit.SECONDS.sleep(5);
        System.out.println("done");
    }

    private static void latchTest() throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        CountDownLatch latch = new CountDownLatch(1);

        executor.submit(() -> {
            try {
                latch.await();
                System.out.println("thread " + Thread.currentThread().getName() + " start");
            }catch (Exception e) {
                e.printStackTrace();
            }
        });

        executor.submit(() -> {
            try {
                System.out.println("thread " + Thread.currentThread().getName() + " start");
                TimeUnit.SECONDS.sleep(10);
            }catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        });

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.MINUTES);
        System.out.println("done");
    }

    private static void barrierTest() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(2);
        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(() -> {
            System.out.println("thread 1 wait");
            try {
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("thread 1 run");
        });

        executor.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("thread 2 wait");
                barrier.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("thread 2 run");
        });

        executor.awaitTermination(20, TimeUnit.MINUTES);
    }

}
