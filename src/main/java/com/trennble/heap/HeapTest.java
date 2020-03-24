package com.trennble.heap;

import org.junit.Test;

/**
 * todo 堆排序，未完成。。。
 */
public class HeapTest {

    @Test
    public void testHeap(){
        int[] array = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 11, 9, 10, 100 };
        // Heap heap=new Heap
        Heap heap = new Heap(12);
        for (int value : array) {
            System.out.println(heap);
            heap.insert(value);
        }
        System.out.println(heap);
        for (int i = 0; i < array.length; i++) {
            System.out.println(heap.remove());
            System.out.println(heap);
        }
    }
}
