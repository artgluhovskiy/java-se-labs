package org.art.java_core.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * "Combination Sum" quiz solution from LeetCode (medium).
 * Backtracking recursive method is used.
 */
public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        backtracking(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void backtracking(int[] candidates, int start, int target, List<Integer> current, List<List<Integer>> result) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            backtracking(candidates, i, target - candidates[i], current, result);
            current.remove(current.size() - 1);
        }
    }

    @Test
    void test0() {
        int[] candidates = new int[]{2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> result = combinationSum(candidates, target);
        System.out.println(result);
    }

    @Test
    void test1() {
        int[] candidates = new int[]{2, 3, 5};
        int target = 8;
        List<List<Integer>> result = combinationSum(candidates, target);
        System.out.println(result);
    }

    @Test
    void test2() {
        int[] candidates = new int[]{2};
        int target = 1;
        List<List<Integer>> result = combinationSum(candidates, target);
        System.out.println(result);
    }
}
