package com.trennble.leet.normal;

import com.google.common.collect.Lists;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MaximumSumOfHeights_2866 {

    public static void main(String[] args) {
        MaximumSumOfHeights_2866 maximumSumOfHeights2866 = new MaximumSumOfHeights_2866();
        System.out.println(maximumSumOfHeights2866.maximumSumOfHeights(Lists.newArrayList(5,3,4,1,1)));
        System.out.println(maximumSumOfHeights2866.maximumSumOfHeights(Lists.newArrayList(6,5,3,9,2,7)));
    }

    public long maximumSumOfHeights(List<Integer> maxHeights) {
        int n = maxHeights.size();
        long[] prefix = new long[n];
        long[] suffix = new long[n];
        Deque<Integer> s = new ArrayDeque<>();
        Deque<Integer> t = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!s.isEmpty() && maxHeights.get(s.peek()) > maxHeights.get(i)) {
                s.pop();
            }
            int j = n - 1 - i;
            while (!t.isEmpty() && maxHeights.get(t.peek()) > maxHeights.get(j)) {
                t.pop();
            }
            if (s.isEmpty()) {
                prefix[i] = (long) maxHeights.get(i) * (i + 1);
            } else {
                prefix[i] = (long) maxHeights.get(i) * (i - s.peek()) + prefix[s.peek()];
            }
            if (t.isEmpty()) {
                suffix[j] = ((long) maxHeights.get(j)) * (n-j);
            } else {
                suffix[j] = ((long) maxHeights.get(j)) * (t.peek() - j) + suffix[t.peek()];
            }
            s.push(i);
            t.push(j);
        }
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, prefix[i]+suffix[i]-maxHeights.get(i));
        }
        return ans;
    }
}
