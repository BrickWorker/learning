package com.brickworkers.juc.mthread.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 有一个承重桥，每次只能过5个人
 * @Author tongzhixiang
 * @create 2019-11-11 16:20
 */
public class SemaphoreTest {

    static final Semaphore SEMAPHORE = new Semaphore(5);


    public static void main(String[] args) {
        AtomicInteger a = new AtomicInteger(1);
        // 有10个人要过桥
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "我想要过桥");
                try {
                    // 查询一下许可证数量
                    System.out.println("[start]现在还有许可证" + SEMAPHORE.availablePermits());
                    SEMAPHORE.acquire();
                    System.out.println(Thread.currentThread().getName() + "拿到了许可证，正在过桥");
                    TimeUnit.SECONDS.sleep(1);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    SEMAPHORE.release();
                    System.out.println(Thread.currentThread().getName() + "过桥结束，归还许可证");
                    // 查询一下许可证数量
                    System.out.println("[end]现在还有许可证" + SEMAPHORE.availablePermits());
                }
            }, "T" + a.getAndIncrement()).start();
        }
    }
}
