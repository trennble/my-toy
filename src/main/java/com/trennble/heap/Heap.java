package com.trennble.heap;

import java.util.Arrays;

public class Heap {

    private int[] arr;

    private int len;

    private int cap;

    public Heap(int cap) {
        this.arr = new int[cap];
        this.cap = cap;
        this.len = 0;
    }

    public void buildHeap(int[] array) {
        this.arr = array;
        // int index = arr.length - 1;
        for (int index = arr.length; index > 0; index--) {
            int pIndex = (index - 1) / 2;
            down(pIndex, index);
        }
    }

    public void down(int pIndex, int index) {
        if (index > len - 1)
            return;
        if (arr[index] > arr[pIndex]) {
            int temp = arr[index];
            arr[index] = arr[pIndex];
            arr[pIndex] = temp;
            int left = index * 2 + 1;
            int right = index * 2 + 2;
            down(index, left);
            down(index, right);
        }
    }

    public void up(int pIndex, int index) {
        if (pIndex < 0)
            return;
        if (arr[index] > arr[pIndex]) {
            int temp = arr[index];
            arr[index] = arr[pIndex];
            arr[pIndex] = temp;
            index = pIndex;
            pIndex = (index-1)/2;
            up(pIndex, index);
        }
    }

    public void insert(int value) {
        if (len == cap)
            return;
        arr[len] = value;
        int pIndex = (len - 1) / 2;
        // down(pIndex, len);
        up(pIndex, len);
        len++;
    }

    public boolean hasNext(){
        return len!=0;
    }

    public int remove() {
        if (len == 0)
            throw new RuntimeException("没有多余的元素");
        int headPos = 0;
        int value = arr[headPos];
        arr[headPos] = 0;
        int left = headPos * 2 + 1;
        int right = headPos * 2 + 2;
        down(headPos, left);
        down(headPos, right);
        return value;
    }

    @Override
    public String toString() {
        return "Heap{" +
                "arr=" + Arrays.toString(arr) +
                ", len=" + len +
                ", cap=" + cap +
                '}';
    }
}
