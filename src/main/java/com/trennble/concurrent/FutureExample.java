package com.trennble.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.locks.ReentrantLock;

public class FutureExample {

    /**
     * 类继承结构
     * FutureTask -> RunnableFuture --> Runnable
     *                              --> Future
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<?> submit = executorService.submit(() -> {
            System.out.println(1);
        });
        Object o = submit.get();
        System.out.println(o);

        Thread thread = new Thread();
        thread.interrupt();

        ReentrantLock lock = new ReentrantLock();
        lock.lockInterruptibly();
        lock.lock();
    }
}