package com.brickworkers.juc.mthread.countdownlatch;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 模拟服务，每此请求，返回的时间不确定
 * @Author tongzhixiang
 * @create 2019-11-11 15:11
 */
public class MockService {
    final static Random random = new Random();

    public void doService(){
        int delay =((Double)(10 * random.nextDouble())).intValue();
        try {
            TimeUnit.SECONDS.sleep(delay);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
