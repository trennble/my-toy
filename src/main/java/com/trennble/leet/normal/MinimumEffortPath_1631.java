package com.trennble.leet.normal;

import java.util.Arrays;

public class MinimumEffortPath_1631 {

    public static void main(String[] args) {
        MinimumEffortPath_1631 minimumEffortPath1631 = new MinimumEffortPath_1631();

        int val0 = minimumEffortPath1631.minimumEffortPath(new int[][]{{1, 2, 2}, {3, 8, 2}, {5, 3, 5}});
        System.out.println(val0);
        int val1 = minimumEffortPath1631.minimumEffortPath(new int[][]{{1, 2, 3}, {3, 8, 4}, {5, 3, 5}});
        System.out.println(val1);
        int val2 = minimumEffortPath1631.minimumEffortPath(new int[][]{{1,2,1,1,1},{1,2,1,2,1},{1,2,1,2,1},{1,2,1,2,1},{1,1,1,2,1}});
        System.out.println(val2);
    }

    int m;
    int n;
    int[][][] visited;

    public int minimumEffortPath(int[][] heights) {
        m = heights.length;
        n = heights[0].length;
        visited = new int[m][n][3];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                visited[i][j][0] = 0;
                visited[i][j][1] = heights[i][j];
            }
        }
        return dfs(visited[0][0][1], 0, 0);
    }

    private Integer dfs(int pre, int x, int y) {
        if (x < 0 || x >= m || y < 0 || y >= n || visited[x][y][0] == 1) {
            return Integer.MAX_VALUE;
        }
        int minVal = Integer.MAX_VALUE;
        int val = visited[x][y][1];
        int abs = Math.abs(val - pre);
        // max = Math.max(max, visited[x][y][1]);
        if (x == m - 1 && y == n - 1) {
            return abs;
        }
        visited[x][y][0] = 1;
        minVal = Math.min(dfs(val, x - 1, y), minVal);
        minVal = Math.min(dfs(val, x, y - 1), minVal);
        minVal = Math.min(dfs(val, x + 1, y), minVal);
        minVal = Math.min(dfs(val, x, y + 1), minVal);
        visited[x][y][0] = 0;
        return Math.max(minVal, abs);
    }
}
