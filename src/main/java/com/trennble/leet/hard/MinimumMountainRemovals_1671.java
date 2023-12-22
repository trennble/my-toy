package com.trennble.leet.hard;

import java.util.Arrays;

public class MinimumMountainRemovals_1671 {

    public static void main(String[] args) {
        MinimumMountainRemovals_1671 minimumMountainRemovals1671 = new MinimumMountainRemovals_1671();
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovals(new int[]{1, 3, 1}));
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1}));
        System.out.println(minimumMountainRemovals1671.minimumMountainRemovals(new int[]{100, 92, 89, 77, 74, 66, 64, 66, 64}));
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
            if (asc[i]>1 && desc[i]>1){
                min = Math.min(min, n - asc[i] - desc[i] + 1);
            }
        }
        return min;
    }
}
