package com.trennble.leet.normal;

import java.util.Arrays;

public class NumberOfClosedIslands_1254 {
    public static void main(String[] args) {

        NumberOfClosedIslands_1254 main = new NumberOfClosedIslands_1254();
        int[][] grid = {{1, 1, 1, 1, 1, 1, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 1, 0, 1, 0, 1},
                {1, 0, 1, 1, 1, 0, 1},
                {1, 0, 0, 0, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1}};
        int[][] grid2 = {{0, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {0, 1, 1, 1, 0}};
        int[][] grid3 = {   {0, 0, 1, 1, 0, 1, 0, 0, 1, 0},
                            {1, 1, 0, 1, 1, 0, 1, 1, 1, 0},
                            {1, 0, 1, 1, 1, 0, 0, 1, 1, 0},
                            {0, 1, 1, 0, 0, 0, 0, 1, 0, 1},
                            {0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
                            {0, 1, 0, 1, 0, 1, 0, 1, 1, 1},
                            {1, 0, 1, 0, 1, 1, 0, 0, 0, 1},
                            {1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
                            {1, 1, 1, 0, 0, 1, 0, 1, 0, 1},
                            {1, 1, 1, 0, 1, 1, 0, 1, 1, 0}};
        System.out.println(main.closedIsland(grid3));
    }

    public int closedIsland(int[][] grid) {
        int len = grid.length;
        int row = grid[0].length;
        int ret = 0;
        for (int i = 1; i < len - 1; i++) {
            for (int j = 1; j < row - 1; j++) {
                int val = grid[i][j];
                if (val == 0) {
                    boolean closed = dfs(j, i, grid);
                    if (closed) {
                        ret++;
                    }
                }
            }
        }
        return ret;
    }

    public boolean dfs(int x, int y, int[][] grid) {
        int val = grid[y][x];
        if (val == 1 || val == -1) {
            return true;
        }
        grid[y][x] = -1;
        int len = grid.length;
        int row = grid[0].length;
        if (x == 0 || y == 0 || x == row - 1 || y == len - 1) {
            return false;
        }
        boolean val1 = dfs(x + 1, y, grid);
        boolean val2 = dfs(x, y + 1, grid);
        boolean val3 = dfs(x - 1, y, grid);
        boolean val4 = dfs(x, y - 1, grid);
        return val1 && val2 && val3 && val4;
    }
}
