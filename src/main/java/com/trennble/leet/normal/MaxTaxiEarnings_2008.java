package com.trennble.leet.normal;

import java.util.*;

public class MaxTaxiEarnings_2008 {


    public long maxTaxiEarnings(int n, int[][] rides) {
        List<int[]>[] map = new ArrayList[n + 1];
        for (int[] ride : rides) {
            int start = ride[0];
            int end = ride[1];
            int tip = ride[2];
            if (map[end] == null) {
                map[end] = new ArrayList<>();
            }
            map[end].add(new int[]{start, end - start + tip});
        }
        long[] dp = new long[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1];
            List<int[]> pres = map[i];
            if (pres == null) {
                continue;
            }
            for (int[] pre : pres) {
                int start = pre[0];
                int score = pre[1];
                dp[i] = Long.max(dp[i], dp[start] + score);
            }
        }
        return dp[n];
    }
}
