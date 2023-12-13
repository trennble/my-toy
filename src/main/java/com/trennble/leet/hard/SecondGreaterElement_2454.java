package com.trennble.leet.hard;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class SecondGreaterElement_2454 {

    public static void main(String[] args) {
        SecondGreaterElement_2454 secondGreaterElement2454 = new SecondGreaterElement_2454();
        System.out.println(JSON.toJSONString(secondGreaterElement2454.secondGreaterElement(new int[]{2, 4, 0, 9, 6})));
        ;
        System.out.println(JSON.toJSONString(secondGreaterElement2454.secondGreaterElement(new int[]{3, 3})));
        ;
        System.out.println(JSON.toJSONString(secondGreaterElement2454.secondGreaterElement(new int[]{11, 13, 15, 12, 0, 15, 12, 11, 9})));
        ;
    }

    public int[] secondGreaterElement(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        List<int[]> s = new ArrayList<>();
        List<int[]> t = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            while (!t.isEmpty() && t.get(t.size()-1)[0] < x) {
                int[] val = t.remove(t.size() - 1);
                ans[val[1]] = x;
            }
            int j = s.size();
            while (j > 0 && s.get(j - 1)[0] < x) {
                j--;
            }
            List<int[]> pop = s.subList(j, s.size());
            t.addAll(pop);
            pop.clear();
            s.add(new int[]{x, i});
            // System.out.println(JSON.toJSONString(s)+"==="+JSON.toJSONString(t));
        }
        return ans;
    }
}
