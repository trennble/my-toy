package com.trennble.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // 新建一个FutureTask
        FutureTask<Integer> futureTask = new FutureTask<>(() -> {
            int result = 0;
            System.out.println("other task start");
            for (int i = 0; i < 100; i++) {
                Thread.sleep(50);
                result += i;
            }
            System.out.println("other task end");
            return result;
        });

        Thread computeThread = new Thread(futureTask);
        computeThread.start();

        System.out.println("main thread is running...");
        Thread.sleep(1000);

        // 阻塞获取结果
        System.out.println(futureTask.get());
    }
}