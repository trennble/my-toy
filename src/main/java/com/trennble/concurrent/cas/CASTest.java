package com.trennble.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.IntBinaryOperator;

/**
 * @ClasssName CASTest
 * @Description TODO
 * @Author sycamore
 * @Date 2020-1-16 10:28
 * @Version 1.0
 **/
public class CASTest {

    AtomicInteger atomicInteger =new AtomicInteger();

    LongAdder longAdder =new LongAdder();

    // LongAccumulator longAccumulator = new LongAccumulator();

    public void test(){
        atomicInteger.incrementAndGet();
        atomicInteger.accumulateAndGet(1, new IntBinaryOperator() {
            @Override
            public int applyAsInt(int left, int right) {
                return 0;
            }
        });
    }

    public void testLongAdder(){
        longAdder.increment();
    }
}
