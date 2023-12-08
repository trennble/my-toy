package com.trennble.leet.normal;

import java.util.*;

public class MinRecord_1446 {

    public static void main(String[] args) {
        MinRecord_1446 minRecord1446 = new MinRecord_1446();
        System.out.println(minRecord1446.minReorder(6, new int[][]{{0, 1}, {1, 3}, {2, 3}, {4, 0}, {4, 5}}));
    }

    public int minReorder(int n, int[][] connections) {
        List<int[]>[] path = new ArrayList[n];
        for (int i = 0; i < path.length; i++) {
            path[i] = new ArrayList<>();
        }
        for (int[] con : connections) {
            int start = con[0], end = con[1];
            path[start].add(new int[]{end, 1});
            path[end].add(new int[]{start, 0});
        }

        return dfs(0, -1, path);
    }

    private int dfs(int cur, int parent, List<int[]>[] path){
        int ret = 0;
        for (int[] edge: path[cur]) {
            if (edge[0] == parent) {
                continue;
            }
            ret += edge[1] + dfs(edge[0], cur, path);
        }
        return ret;
    }
}
