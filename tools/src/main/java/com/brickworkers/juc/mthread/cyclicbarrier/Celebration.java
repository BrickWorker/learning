package com.brickworkers.juc.mthread.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 学校举行校庆
 * 1、等10个领导上台，做自我介绍
 * 2、等人到齐后，主持人介绍
 * @Author tongzhixiang
 * @create 2019-11-11 16:03
 */
public class Celebration implements Runnable{
    final CyclicBarrier cb = new CyclicBarrier(10, this);




    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        System.out.println("搭嘎好，我是这届庆典的主持人，欢迎大家来参加，从左边开始依次是：XXX、XXX、XXX");
    }

    private void perform(){
        AtomicInteger a = new AtomicInteger(1);

        // 等待10个人到齐
        for(int i = 0; i < 10; i++){
            new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    cb.await();
                    System.out.println("大家好，我是来宾" + a.getAndIncrement() + "号");
                }catch (Exception e){
                    e.printStackTrace();
                }
            }, "T" + i).start();
        }

    }


    public static void main(String[] args) {
        Celebration celebration = new Celebration();
        celebration.perform();
    }
}
