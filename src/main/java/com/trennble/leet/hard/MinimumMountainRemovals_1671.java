package com.trennble.leet.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinimumMountainRemovals_1671 {

    public static void main(String[] args) {
        MinimumMountainRemovals_1671 minimumMountainRemovals1671 = new MinimumMountainRemovals_1671();
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovals(new int[]{1, 3, 1}));
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1}));
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovals(new int[]{100, 92, 89, 77, 74, 66, 64, 66, 64}));
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovalsBinary(new int[]{1, 3, 1}));
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovalsBinary(new int[]{2, 1, 1, 5, 6, 2, 3, 1}));
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovalsBinary(new int[]{100, 92, 89, 77, 74, 66, 64, 66, 64}));
    }


    public int minimumMountainRemovals(int[] nums) {
        int n = nums.length;
        int[] asc = new int[n];
        int[] desc = new int[n];
        Arrays.fill(asc, 0);
        Arrays.fill(desc, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    asc[i] = Math.max(asc[i], asc[j]);
                }
            }
            asc[i]++;
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 1; j < n; j++) {
                if (nums[j] < nums[i]) {
                    desc[i] = Math.max(desc[i], desc[j]);
                }
            }
            desc[i]++;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (asc[i] > 1 && desc[i] > 1) {
                min = Math.min(min, n - asc[i] - desc[i] + 1);
            }
        }
        return min;
    }

    public int minimumMountainRemovalsBinary(int[] nums) {
        int n = nums.length;
        int[] suf = new int[n];
        List<Integer> g = new ArrayList<>();
        for (int i = n - 1; i > 0; i--) {
            int x = nums[i];
            int j = lowerBound(g, x);
            if (j == g.size()) {
                g.add(x);
            } else {
                g.set(j, x);
            }
            suf[i] = j + 1; // 从 nums[i] 开始的最长严格递减子序列的长度
        }

        int mx = 0;
        g.clear();
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            int j = lowerBound(g, x);
            if (j == g.size()) {
                g.add(x);
            } else {
                g.set(j, x);
            }
            int pre = j + 1; // 在 nums[i] 结束的最长严格递增子序列的长度
            if (pre >= 2 && suf[i] >= 2) {
                mx = Math.max(mx, pre + suf[i] - 1); // 减去重复的 nums[i]
            }
        }
        return n - mx;
    }

    // 请看 https://www.bilibili.com/video/BV1AP41137w7/
    private int lowerBound(List<Integer> g, int target) {
        int left = -1, right = g.size(); // 开区间 (left, right)
        while (left + 1 < right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = (left + right) >>> 1;
            if (g.get(mid) < target) {
                left = mid; // 范围缩小到 (mid, right)
            } else {
                right = mid; // 范围缩小到 (left, mid)
            }
        }
        return right; // 或者 left+1
    }
}
