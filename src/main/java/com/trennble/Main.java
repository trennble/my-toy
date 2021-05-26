package com.trennble;

import java.util.*;

/**
 * 2021年05月26日16:00:12 招商笔试
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(Integer.valueOf(sc.next()));
        }
        Set<List<Integer>> res = new HashSet<>();
        rec(nums, 24, res, new ArrayList<>());
        System.out.println(res.size());
    }

    public static void rec(List<Integer> nums, int target, Set<List<Integer>> res, List<Integer> curRes){
        for (int i = 0; i < nums.size(); i++) {
            // if (i > 0 && nums.get(i).equals(nums.get(i - 1))){
            //     continue;
            // }
            Integer curNum = nums.get(i);
            int nextTarget = target - curNum;
            if (nextTarget == 0){
                curRes.add(curNum);
                curRes.sort(Integer::compareTo);
                res.add(curRes);
                return;
            }else if (nextTarget < 0){
                return;
            }else{
                ArrayList<Integer> nextNums = new ArrayList<>(nums);
                nextNums.remove(curNum);
                ArrayList<Integer> nextCurRes = new ArrayList<>(curRes);
                nextCurRes.add(curNum);
                rec(nextNums, nextTarget, res, nextCurRes);
            }
        }
    }
}