package com.trennble.leet.hard;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class MaxSumQuery_2736 {


    public static void main(String[] args) {
        MaxSumQuery_2736 maxSumQuery2736 = new MaxSumQuery_2736();
        int[] ans1 = maxSumQuery2736.maximumSumQueries(new int[]{4, 3, 1, 2}, new int[]{2, 4, 9, 5}, new int[][]{{4, 1}, {1, 3}, {2, 5}});
        System.out.println(JSON.toJSONString(ans1));
    }

    int[] tr;
    int len;

    public int lowbit(int i) {
        return i & -i;
    }

    public void add(int i, int val) {
        while (i <= len) {
            tr[i] = Math.max(tr[i], val);
            i += lowbit(i);
        }
    }

    public int query(int i) {
        int max = -1;
        while (i > 0) {
            max = Integer.max(tr[i], max);
            i -= lowbit(i);
        }
        return max;
    }

    public int[] maximumSumQueries(int[] nums1, int[] nums2, int[][] queries) {
        int m = nums1.length;
        int n = queries.length;
        int[][] nums = new int[m][2];
        int[][] nq = new int[n][3];
        for (int i = 0; i < m; i++) {
            nums[i] = new int[]{nums1[i], nums2[i]};
        }
        for (int i = 0; i < n; i++) {
            nq[i] = new int[]{queries[i][0], queries[i][1], i};
        }

        Arrays.sort(nq, (a, b) -> b[0] - a[0]);
        Arrays.sort(nums, (a, b) -> b[0] - a[0]);

        Set<Integer> set = new HashSet<>();
        for (int i : nums2) {
            set.add(i);
        }
        for (int[] query : queries) {
            set.add(query[1]);
        }
        List<Integer> list = new ArrayList<>(set);
        list.sort((a, b) -> b - a);
        Map<Integer, Integer> rankMap = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            rankMap.put(list.get(i), i + 1);
        }

        len = list.size();
        tr = new int[len+1];
        Arrays.fill(tr, -1);
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        int idx = 0;
        for (int i = 0; i < nq.length; i++) {
            int[] query = nq[i];
            int x = query[0];
            int y = query[1];
            int pos = query[2];
            while (idx < m && nums[idx][0] >= x) {
                add(rankMap.get(nums[idx][1]), nums[idx][1] + nums[idx][0]);
                idx++;
            }
            ans[pos] = query(rankMap.get(y));
        }
        return ans;
    }

}
