package com.trennble.concurrent.lock;

import org.junit.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClasssName LockTest
 * @Description TODO
 * @Author sycamore
 * @Date 2019-9-25 9:52
 * @Version 1.0
 **/
public class LockTest {

    private Lock lock=new ReentrantLock();

    @Test
    public void testDoubleUnlock(){
        lock.lock();
        lock.unlock();
        if (lock.tryLock()){
            lock.unlock();
        }

    }
}
