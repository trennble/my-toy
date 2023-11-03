package com.trennble.leet.normal;

/**
 * z字变换
 */
public class ZigZagConversion_6 {

    public static void main(String[] args) {
        ZigZagConversion_6 vipConfigItemService = new ZigZagConversion_6();
        System.out.println(vipConfigItemService.convert("PAYPALISHIRING",4));
    }

    public String convert(String s, int numRows) {
        int len = s.length();
        if(len < numRows || numRows == 1) {
            return s;
        }

        int initGap = (numRows - 1) * 2;
        int gap = initGap;
        StringBuilder ret = new StringBuilder();
        int pos = 0;
        int curRow = 0;
        while(curRow < numRows) {
            while (pos < len) {
                ret.append(s.charAt(pos));
                if (gap != initGap && pos >= numRows) {
                    gap = (initGap - gap);
                }
                pos += gap;
            }
            curRow++;
            gap = (numRows - curRow - 1) * 2;
            if (gap == 0) {
                gap = initGap;
            }
            pos = curRow;
        }
        return ret.toString();
    }
}
