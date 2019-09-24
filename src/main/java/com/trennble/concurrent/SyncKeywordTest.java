package com.trennble.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClasssName synchronized关键字是对对象进行加锁，判断同一个锁的条件是判断他们的地址是否相等
 * 输出结果：
 *          ==:false
 *          equals:true
 *          enter thread1
 *          enter thread2
 *          exit thread2
 *          exit thread1
 *          enter thread3
 *          exit thread3
 * @Description TODO
 * @Author sycamore
 * @Date 2019-9-24 10:56
 * @Version 1.0
 **/
public class SyncKeywordTest {

    @Test
    public void test() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        String str1 = new String("123");
        String str2 = new String("123");
        boolean res1 = str1 == str2;
        boolean res2 = str1.equals(str2);
        System.out.println("==:"+ res1);
        System.out.println("equals:"+ res2);
        assert !res1;
        assert res2;

        executorService.execute(() -> {
            synchronized (str1){
                System.out.println("enter thread1");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("exit thread1");
            }
        });

        executorService.execute(() -> {
            synchronized (str2){
                System.out.println("enter thread2");
                System.out.println("exit thread2");
            }
        });

        executorService.execute(() -> {
            synchronized (str1){
                System.out.println("enter thread3");
                System.out.println("exit thread3");
            }
        });

        Thread.sleep(10000);
    }
}
