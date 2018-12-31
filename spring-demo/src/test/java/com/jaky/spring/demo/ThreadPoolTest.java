package com.jaky.spring.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by jaky on 1/20/17.
 */
public class ThreadPoolTest {

    private static Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

    /**
     * 验证：核心线程数全部正在执行任务，任务缓存队列未满的情况下，新任务会进入队列等待，而不会创建新的线程处理任务。
     */
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(1));

        threadPoolExecutor.submit(new Runnable() {
            public void run() {
                logger.info("run first task start");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    logger.error("run first task err");
                }
                logger.info("run first task end");
            }
        });

        threadPoolExecutor.submit(new Runnable() {
            public void run() {
                logger.info("run second task start");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    logger.error("run second task err");
                }
                logger.info("run second task end");
            }
        });



        TimeUnit.SECONDS.sleep(5);

    }

}
