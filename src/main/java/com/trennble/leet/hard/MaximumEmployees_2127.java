package com.trennble.leet.hard;

import java.util.*;

/**
 * 参加会议的最多员工数
 */
public class MaximumEmployees_2127 {

    public static void main(String[] args) {
        MaximumEmployees_2127 main = new MaximumEmployees_2127();
        int[] param = {1,0,3,2,5,6,7,4,9,8,11,10,11,12,10};
        main.maximumInvitations(param);
        System.out.println();
    }

    public int maximumInvitations(int[] favorite) {
        int len = favorite.length;
        // 记录每个节点的入度
        int[] inCount = new int[len];
        for (int j : favorite) {
            inCount[j]++;
        }

        // 记录入度为0的节点
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            if (inCount[i] == 0) {
                deque.add(i);
            }
        }

        // 从入度为0的节点开始剪枝并记录反图
        List<Integer>[] revers = new List[len];
        Arrays.setAll(revers, i->new ArrayList<>());
        Integer x = deque.poll();
        while (Objects.nonNull(x)) {
            inCount[favorite[x]]--;
            revers[favorite[x]].add(x);
            if (inCount[favorite[x]] == 0) {
                deque.add(favorite[x]);
            }
            x = deque.poll();
        }

        int maxRingSize = 0;
        int sumChainSize = 0;
        for (int i = 0; i < len; i++) {
            if (inCount[i] == 0) {
                continue;
            }

            int ringSize = 1;
            inCount[i] = 0;
            for (int m = favorite[i]; i != m; m = favorite[m]) {
                ringSize++;
                inCount[m] = 0;
            }

            if (ringSize == 2) {
                sumChainSize = dfs(revers, i) + dfs(revers, favorite[i]);
            } else {
                maxRingSize = Integer.max(maxRingSize, ringSize);
            }
        }
        return Integer.max(maxRingSize, sumChainSize);
    }

    private int dfs(List<Integer>[] revers, Integer x) {
        List<Integer> reversList = revers[x];
        if (reversList.isEmpty()) {
            return 1;
        }
        int deep = 1;
        for (Integer back : reversList) {
            deep = Integer.max(deep, dfs(revers, back) + 1);
        }
        return deep;
    }

}
