package com.trennble.leet.hard;

public class MaxStudents_1349 {

    int[][] dirs = new int[][]{ {-1, -1}, {0, -1}, {1, -1}, {-1, 1}, {0, 1}, {1, 1}};

    public int maxStudents(char[][] seats) {
        int m = seats.length;
        int n = seats[0].length;
        int[][] dp = new int[m][2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

            }
        }
        return 1;
    }

    public boolean isConflict(int[][] person, int i, int j) {
        for (int[] dir : dirs) {
            int nx = i + dir[0];
            int ny = j + dir[1];
            if (nx < 0 || nx >= person.length || ny < 0 || ny >= person[0].length) {
                continue;
            }
            if (person[nx][ny] == 1) {
                return true;
            }
        }
        return false;
    }
}
