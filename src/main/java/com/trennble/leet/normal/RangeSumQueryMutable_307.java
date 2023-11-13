package com.trennble.leet.normal;

public class RangeSumQueryMutable_307 {

    public static void main(String[] args) {
        int[] param = {1, 3, 5};
        NumArray numArray = new NumArray(param);
        System.out.println(numArray.sumRange(0,2));
        numArray.update(1,2);
        System.out.println(numArray.sumRange(0,2));
    }

    static class NumArray {

        int[] nums;

        public NumArray(int[] nums) {
            this.nums = nums;
        }

        public void update(int index, int val) {
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            int sum = 0;
            for (int i = left; i <= right; i++) {
                sum += nums[i];
            }
            return sum;
        }
    }
}

