package com.trennble.leet.normal;

import java.util.Arrays;

public class MinPathCostInGrid_2304 {

    public static void main(String[] args) {
        MinPathCostInGrid_2304 minPathCostInGrid2304 = new MinPathCostInGrid_2304();
        int[][] cost = new int[][]{{9, 8}, {1, 5}, {10, 12}, {18, 6}, {2, 4}, {14, 3}};
        int[][] grid = new int[][]{{5, 3}, {4, 0}, {2, 1}};
        int i = minPathCostInGrid2304.minPathCost(grid, cost);
        System.out.println(i);


    }


    public int minPathCost(int[][] grid, int[][] moveCost) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];
        for (int[] d : dp) {
            Arrays.fill(d, Integer.MAX_VALUE);
        }
        for (int i = 0; i < n; i++) {
            dp[0][i] = grid[0][i];
        }
        for (int x = 1; x < m; x++) {
            for (int y = 0; y < n; y++) {
                for (int z = 0; z < n; z++) {
                    int cost = moveCost[grid[x - 1][z]][y];
                    dp[x][y] = Math.min(dp[x][y], dp[x - 1][z] + cost);
                }
                dp[x][y] += grid[x][y];
            }
        }
        int min = Integer.MAX_VALUE;
        for (int d : dp[m - 1]) {
            min = Math.min(min, d);
        }
        return min;
    }
}
