package com.trennble.leet.normal;

import javax.validation.constraints.Min;

public class MinDeletions_2216 {

    public static void main(String[] args) {
        MinDeletions_2216 minDeletions2216 = new MinDeletions_2216();
        minDeletions2216.minDeletion(new int[]{1,1,2,2,3,3});
    }


    public int minDeletion(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for(int i=0; i < n;) {
            int j = i + 1;
            while(j < n && nums[i] == nums[j]){
                ans++;
                j++;
            }
            if (j >= n) {
                ans++;
            }
            i = j + 1;
        }
        return ans;
    }
}
