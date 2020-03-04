package com.trennble.concurrent.aqs;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

/**
 * 简单的代码，用以跟踪AQS相关类的执行过程
 */
public class AQSTest {

    public void readWriteLock() throws InterruptedException {
        ReentrantLock lock=new ReentrantLock();


        lock.tryLock();
        lock.tryLock(1, TimeUnit.MILLISECONDS);
        lock.lock();
        lock.lockInterruptibly();
        lock.unlock();
        lock.newCondition();

        ReadWriteLock readWriteLock=new ReentrantReadWriteLock();

    }

    /**
     * 测试 AbstractQueuedSynchronizer#addWaiter 方法
     * 结果：
     *      pre指针没有变
     *      tail指针指向新的node
     */
    @Test
    public void testCompareAndSetTail(){

        ReentrantLock lock=new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.execute(()->{
            try {
                System.out.println("thread "+Thread.currentThread());
                lock.lock();
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        executorService.execute(()->{
            try {
                System.out.println("thread "+Thread.currentThread());
                lock.lock();
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
        executorService.execute(()->{
            try {
                System.out.println("thread "+Thread.currentThread());
                lock.lock();
                Thread.sleep(10000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        });
    }

    public static class MyAQS extends AbstractQueuedSynchronizer{
        public MyAQS(){
        }
    }

    /**
     * 测试 Unsafe 的 compareAndSwapObject 方法
     */
    private static final long nameOffset;
    private static final long preNameOffset;
    private String name="init-name";
    private String preName = name;
    static Unsafe unsafe = reflectGetUnsafe();
    static {
        try {
            nameOffset = unsafe.objectFieldOffset
                    (AQSTest.class.getDeclaredField("name"));
            preNameOffset = unsafe.objectFieldOffset
                    (AQSTest.class.getDeclaredField("preName"));
        } catch (Exception ex) { throw new Error(ex); }
    }
    private static Unsafe reflectGetUnsafe() {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            return (Unsafe) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }
    @Test
    public void testUnsafe() throws Exception {
        // String preName = name;
        System.out.println(name==preName);
        System.out.println("nameOffset addr:" + nameOffset);
        System.out.println("preNameOffset addr:" + preNameOffset);
        System.out.println("preName addr:" + addressOf(preName));
        System.out.println("name addr:" + addressOf(name));
        unsafe.compareAndSwapObject(this, nameOffset, preName, "new-name");
        System.out.println("preName:" + preName);
        System.out.println("name:" + name);
        System.out.println("preName addr:" + addressOf(preName));
        System.out.println("name addr:" + addressOf(name));
    }

    public static long addressOf(Object o) throws Exception {

        Object[] array = new Object[] { o };

        long baseOffset = unsafe.arrayBaseOffset(Object[].class);
        int addressSize = unsafe.addressSize();
        long objectAddress;
        switch (addressSize) {
            case 4:
                objectAddress = unsafe.getInt(array, baseOffset);
                break;
            case 8:
                objectAddress = unsafe.getLong(array, baseOffset);
                break;
            default:
                throw new Error("unsupported address size: " + addressSize);
        }
        return (objectAddress);
    }
}
