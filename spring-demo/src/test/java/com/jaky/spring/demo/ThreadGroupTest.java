package com.jaky.spring.demo;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 * Created by jaky.wang on 2017/5/12.
 */
public class ThreadGroupTest {

    private Logger logger = LoggerFactory.getLogger(ThreadGroupTest.class);

    @Test
    public void thread_modify_other_thread_group_test() throws InterruptedException {
        final ThreadGroup tg1 = new ThreadGroup("thread_group_one");
        final ThreadGroup tg2 = new ThreadGroup("thread_group_two");

        final Thread t1 = new Thread(tg1, new Runnable() {
            public void run() {
                try {
                    logger.info("线程一开始执行");
                    TimeUnit.SECONDS.sleep(20);
                    logger.info("线程一执行完毕");
                } catch (InterruptedException e) {
                    logger.error("线程 one 被中断");
                }
            }
        });


        Thread t2 = new Thread(tg2, new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(5);
                    logger.info("线程二开始执行");
                    ThreadGroup t1tg = t1.getThreadGroup();
                    logger.info("t1gq的相关信息 {}", t1tg.activeGroupCount());
                    logger.info("t1gq的相关信息 {}", t1tg.getName());
                    logger.info("t1gq的相关信息 {}", t1tg.getParent());

                    logger.info("线程二执行中断线程一结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        // 中断线程组中的所有线程
        tg1.interrupt();

        // 获取线程组以及其所有子线程组的活动线程数的预估值
        tg1.activeCount();

        // 设置为守护线程
        tg1.setDaemon(true);

        // 获取线程组中的所有线程（也可同时获取所有子线程组的所有线程）
        tg1.enumerate(new Thread[100]);


        TimeUnit.SECONDS.sleep(30);
    }

}
