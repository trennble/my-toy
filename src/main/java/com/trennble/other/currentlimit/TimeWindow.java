package com.trennble.other.currentlimit;

import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.IntStream;

/**
 * 代码来自https://github.com/doocs/advanced-java/blob/master/docs/high-concurrency/huifer-how-to-limit-current.md
 * 这里对{@link TimeWindow#clean()}的调用时机进行了测试，把clean阶段放到{@link TimeWindow#sizeOfValid()}中，
 * “for clean”注释即为测试增加或者注释掉的代码
 * 猜测：
 *      1.会出现多线程问题
 * 结果：
 *      出现多线程问题，132行打印时间戳一致但是数量不同的信息
 */
public class TimeWindow {
    private final ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<>();

    /**
     * 间隔秒数
     */
    private int seconds;

    /**
     * 最大限流
     */
    private int max;

    public TimeWindow(int max, int seconds) {
        this.seconds = seconds;
        this.max = max;

        /**
         * 永续线程执行清理queue 任务
         */
        new Thread(() -> {
            while (true) {
                try {
                    // 等待 间隔秒数-1 执行清理操作
                    long sleepTime = (seconds - 1) * 1000L;
                    // System.out.println("sleep " + sleepTime + " ms");
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // for clean
                // clean();
            }
        }).start();

    }

    public static void main(String[] args) {

        final TimeWindow timeWindow = new TimeWindow(10, 1);

        // 测试3个线程
        IntStream.range(0, 3).forEach((i) -> {
            new Thread(() -> {

                while (true) {

                    try {
                        Thread.sleep(new Random().nextInt(10) * 100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    timeWindow.take();
                }

            }).start();

        });

    }

    /**
     * 获取令牌，并且添加时间
     */
    public void take() {
        try {
            int size = sizeOfValid();
            if (size > max) {
                System.err.println("超限");

            }
            synchronized (queue) {
                if (sizeOfValid() > max) {
                    System.err.println("超限");
                    System.err.println("queue中有 " + queue.size() + " 最大数量 " + max);
                }
                this.queue.offer(System.currentTimeMillis());
            }
            System.out.println("queue中有 " + queue.size() + " 最大数量 " + max);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public int sizeOfValid() throws InterruptedException {
        // for clean
        clean();
        Iterator<Long> it = queue.iterator();
        long ms = System.currentTimeMillis() - seconds * 1000;
        int count = 0;
        while (it.hasNext()) {
            long t = it.next();
            if (t > ms) {
                // 在当前的统计时间范围内
                count++;
            }
        }

        return count;
    }


    /**
     * 清理过期的时间
     */
    public void clean() throws InterruptedException {
        long c = System.currentTimeMillis() - seconds * 1000;

        Long tl;
        while ((tl = queue.peek()) != null && tl < c) {
            // for clean
            Thread.sleep(1000);
            queue.poll();
            System.out.println(String.format("已经清理数据[%d],剩余[%d]", tl, queue.size()));

        }
    }

}