package com.trennble.leet.easy;

import java.util.Arrays;

public class RingsAndRods_2103 {
    public static void main(String[] args) {

        System.out.printf(new RingsAndRods_2103().countPoints("B0R0G0R9R0B0G0") + "");
    }


    public int countPoints(String rings) {
        int[] rods = new int[10];
        Arrays.fill(rods, 0);
        for (int i = 0; i < rings.length(); i +=2) {
            int pos = rings.charAt(i + 1)-'0';
            int val = 0;
            switch (rings.charAt(i)) {
                case 'R':
                    val = 1;
                    break;
                case 'G':
                    val = 2;
                    break;
                case 'B':
                    val = 4;
                    break;
            }
            rods[pos] = rods[pos] | val;
        }
        int total = 0;
        for (int rod : rods) {
            if (rod == 7) {
                total++;
            }
        }
        return total;
    }
}