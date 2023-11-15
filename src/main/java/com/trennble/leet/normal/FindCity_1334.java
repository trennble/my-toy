package com.trennble.leet.normal;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.*;

public class FindCity_1334 {

    public static void main(String[] args) {

        int[][] edges = {{0, 1, 3},
                {1, 2, 1},
                {1, 3, 4},
                {2, 3, 1}};

        int[][] edges2 = {{0, 1, 2},
                {0, 4, 8},
                {1, 2, 3},
                {1, 4, 2},
                {2, 3, 1},
                {3, 4, 1}};

        int[][] edges3 = {{0, 3, 7},
                {2, 4, 1},
                {0, 1, 5},
                {2, 3, 10},
                {1, 3, 6},
                {1, 2, 1}};

        FindCity_1334 main = new FindCity_1334();
        System.out.println(main.findTheCity(4, edges, 4));
        System.out.println(main.findTheCity(5, edges2, 2));
        System.out.println(main.findTheCity(6, edges3, 417));
    }

    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] map = new int[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(map[i], Integer.MAX_VALUE/2);
        }
        for(int[] edge : edges) {
            map[edge[0]][edge[1]] = edge[2];
            map[edge[1]][edge[0]] = edge[2];
        }
        for(int x = 0; x < n; x++) {
            map[x][x] = 0;
            for(int i = 0; i < n; i++) {
                for(int j = 0; j < n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][x] + map[x][j]);
                }
            }
        }

        int minCount = Integer.MAX_VALUE;
        int maxCity = 0;
        for(int i = 0; i < n; i++) {
            int cnt = 0;
            for(int j = 0; j < n; j++) {
                if(map[i][j] <= distanceThreshold) {
                    cnt ++;
                }
            }
            if(cnt <= minCount) {
                minCount = cnt;
                maxCity = i;
            }
        }
        return maxCity;
    }
}
