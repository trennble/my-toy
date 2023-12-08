package com.trennble.leet.normal;

import java.util.*;

public class MinRecord_1446 {

    public static void main(String[] args) {
        MinRecord_1446 minRecord1446 = new MinRecord_1446();
        System.out.println(minRecord1446.minReorder(6, new int[][]{{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}}));
    }

    public int minReorder(int n, int[][] connections) {
        Queue<Integer> ready = new ArrayDeque<>();
        ready.add(0);
        Set<Integer>[] toMap = new HashSet[n];
        Set<Integer>[] fromMap = new HashSet[n];
        for (int[] c : connections) {
            int to = c[1];
            int from = c[0];
            if (toMap[to] == null) {
                toMap[to] = new HashSet<>();
            }
            toMap[to].add(from);
            if (fromMap[from] == null) {
                fromMap[from] = new HashSet<>();
            }
            fromMap[from].add(to);
        }

        int count = 0;
        while (true) {
            Integer poll = ready.poll();
            if (poll == null) {
                break;
            }
            Set<Integer> fromSet = toMap[poll];
            if (fromSet != null) {
                remove(fromMap, fromSet, poll);
                ready.addAll(fromSet);
            }
            Set<Integer> toSet = fromMap[poll];
            if (toSet == null) {
                continue;
            }
            remove(toMap, toSet, poll);
            count += toSet.size();
            ready.addAll(toSet);
        }
        return count;
    }

    private void remove(Set<Integer>[] map, Set<Integer> cur, int val){
        for (Integer i : cur) {
            Set<Integer> curSet = map[i];
            if (curSet == null) {
                continue;
            }
            curSet.remove(val);
        }
    }
}
