package com.trennble.leet.normal;

import java.util.*;

public class MinCost_2735 {

    public static void main(String[] args) {

        MinCost_2735 minCost_2735 = new MinCost_2735();
        System.out.println(minCost_2735.minCost(new int[]{31,25,18,59}, 27));
        System.out.println(minCost_2735.minCost(new int[]{20, 1, 15}, 5));
        System.out.println(minCost_2735.minCost(new int[]{1, 3, 5, 2}, 1));
        System.out.println(minCost_2735.minCost(new int[]{2, 2, 2, 2, 2}, 2));
    }

    public long minCost(int[] nums, int x) {
        int n = nums.length;
        long[] min = new long[n];
        Arrays.fill(min, Long.MAX_VALUE);
        long minCost = Long.MAX_VALUE;
        // 操作次数
        for (int i = 0; i < n; i++) {
            // 巧克力类型
            for (int j = 0; j < n; j++) {
                min[j] = Math.min(min[j], nums[(j + i) % n]);
            }
            long cost = (long) i * x;
            cost += Arrays.stream(min).sum();
            minCost = Math.min(cost, minCost);
        }
        return minCost;
    }
}
