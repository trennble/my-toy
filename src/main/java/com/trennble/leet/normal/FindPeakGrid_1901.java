package com.trennble.leet.normal;

import com.alibaba.fastjson.JSON;

public class FindPeakGrid_1901 {

    public static void main(String[] args) {
        FindPeakGrid_1901 findPeakGrid1901 = new FindPeakGrid_1901();

        System.out.println(JSON.toJSONString(findPeakGrid1901.findPeakGrid(new int[][]{{1,4},{3,2}})));
        System.out.println(JSON.toJSONString(findPeakGrid1901.findPeakGrid(new int[][]{{10,20,15},{21,30,14},{7,16,32}})));
    }

    int[][] dirs = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};

    public int[] findPeakGrid(int[][] mat) {
        return dfs(0,0,mat);
    }

    public int[] dfs(int x, int y, int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        for (int[] dir : dirs) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            int[] peak = null;
            if (nx >= 0 && nx < m && ny >= 0 && ny < n && mat[nx][ny] > mat[x][y]) {
                peak = dfs(nx, ny, mat);
            }

            if (peak != null) {
                return peak;
            }
        }
        return new int[]{x,y};
    }
}
