package com.trennble.leet.hard;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinimumMountainRemovals_1671 {

    public static void main(String[] args) {
        MinimumMountainRemovals_1671 minimumMountainRemovals1671 = new MinimumMountainRemovals_1671();
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovals(new int[]{1, 3, 1}));
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1}));
    }


    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] pre = new int[n];
        int[] next = new int[n];
        Arrays.fill(pre, 0);
        Arrays.fill(next, 0);
        Deque<Integer> s = new ArrayDeque<>();
        Deque<Integer> t = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                pre[i] = pre[i - 1];
            }
            while (!s.isEmpty() && nums[s.peek()] >= nums[i]) {
                s.pop();
                pre[i]++;
            }
            int j = n - 1 - i;
            if (j < n - 1) {
                next[j] = next[j + 1];
            }
            while (!t.isEmpty() && nums[t.peek()] >= nums[j]) {
                t.pop();
                next[j]++;
            }
            s.push(i);
            t.push(j);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < n-1; i++) {
            min = Math.min(min, pre[i] + next[i]);
        }
        return min;
    }
}
