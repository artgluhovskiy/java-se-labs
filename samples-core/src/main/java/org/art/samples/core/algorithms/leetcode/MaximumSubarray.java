package org.art.samples.core.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Maximum Subarray" quiz solution from LeetCode.
 *
 * Dynamic programming approach is used.
 * Explanation: https://medium.com/tech-life-fun/leet-code-53-maximum-subarray-detailed-explained-python3-solution-d91c7affc02a
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int[] maxPrefixes = new int[nums.length];
        maxPrefixes[0] = nums[0];
        int maxSum = maxPrefixes[0];
        for (int i = 1; i < nums.length; i++) {
            maxPrefixes[i] = Math.max(maxPrefixes[i - 1] + nums[i], nums[i]);
            if (maxPrefixes[i] > maxSum) {
                maxSum = maxPrefixes[i];
            }
        }
        return maxSum;
    }

    @Test
    void test0() {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int expected = 6;
        int result = maxSubArray(arr);
        assertEquals(expected, result);
    }

    @Test
    void test1() {
        int[] arr = {1};
        int expected = 1;
        int result = maxSubArray(arr);
        assertEquals(expected, result);
    }

    @Test
    void test2() {
        int[] arr = {5, 4, -1, 7, 8};
        int expected = 23;
        int result = maxSubArray(arr);
        assertEquals(expected, result);
    }
}
