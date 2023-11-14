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
        Map<Integer, Set<Integer>> map = new HashMap<>();
        Set<Integer> city = new HashSet<>();
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int from = edge[0];
            Set<Integer> fromSet = map.computeIfAbsent(from, key -> new HashSet<>());
            fromSet.add(i);
            int to = edge[1];
            Set<Integer> toSet = map.computeIfAbsent(to, key -> new HashSet<>());
            toSet.add(i);
            city.add(from);
            city.add(to);
        }
        for (int i = n-1; i >= 0; i--) {
            if (!city.contains(i)) {
                return i;
            }
        }

        int minCityNum = Integer.MAX_VALUE;
        int maxCity = Integer.MIN_VALUE;
        for (Integer i : city) {
            HashSet<Integer> visited = new HashSet<>();
            int cityNum = dfs(i, visited, edges, map, distanceThreshold, minCityNum);
            if (cityNum < minCityNum) {
                minCityNum = cityNum;
                maxCity = i;
            } else if (cityNum == minCityNum && i > maxCity) {
                maxCity = i;
            }
        }
        return maxCity;
    }

    public int dfs(int city, HashSet<Integer> visited, int[][] edges, Map<Integer, Set<Integer>> map, int distance, int minCityNum) {
        if (visited.contains(city) || distance < 0) {
            return 0;
        }
        visited.add(city);
        if (distance == 0) {
            return 1;
        }
        int cityNum = 1;
        visited.add(city);
        Set<Integer> neighbor = map.get(city);
        for (Integer i : neighbor) {
            int[] edge = edges[i];
            int weight = edge[2];
            int x = edge[0];
            int y = edge[1];
            int nextCity = x == city ? y : x;
            cityNum += dfs(nextCity, visited, edges, map, distance - weight, minCityNum);
        }
        return cityNum;
    }
}
