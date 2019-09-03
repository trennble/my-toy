package com.trennble.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountdownLatchExample {

    public static void main(String[] args) throws InterruptedException {
        final int totalThread = 10;
        CountDownLatch countDownLatch = new CountDownLatch(totalThread);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < totalThread; i++) {
            int finalI = i;
            executorService.execute(() -> {
                System.out.println("run.."+Thread.currentThread().getName());
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("after.."+Thread.currentThread().getName());
            });
        }
        Thread.sleep(1000);
        for (int i = 0; i < totalThread; i++) {
            System.out.println(countDownLatch.getCount());
            countDownLatch.countDown();
        }
        System.out.println("main..");
        executorService.shutdown();
    }
}