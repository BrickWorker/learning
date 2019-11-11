package com.brickworkers.juc.mthread.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Author tongzhixiang
 * @create 2019-11-11 15:16
 */
public class CountDownLatchTest {

    static final CountDownLatch cdl = new CountDownLatch(3);

    public static void main(String[] args) {
        MockService mockService = new MockService();
        // 开启三个线程去请求Mock服务
        new Thread(() -> {
            mockService.doService();
            cdl.countDown();
        }, "T1").start();
        new Thread(() -> {
            mockService.doService();
            cdl.countDown();
        }, "T2").start();
        new Thread(() -> {
            mockService.doService();
            cdl.countDown();
        }, "T3").start();
        try {
            cdl.await();
            System.out.println("请求全部完成，剩余CountDown :" + cdl.getCount());
        }catch (Exception e){

        }

    }
}
