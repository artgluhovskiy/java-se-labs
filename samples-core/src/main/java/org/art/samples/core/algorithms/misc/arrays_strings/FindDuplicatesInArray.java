package org.art.samples.core.algorithms.misc.arrays_strings;

import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Problem from Medium article.
 * Given an array of N+1 integers containing elements in range [1, N]. Find any one duplicate number.
 */
public class FindDuplicatesInArray {

    /**
     * Version 1. We use indexes in order to mark the visited element. Time complexity: O(N).
     * We consider that an array is mutable.
     */
    public int findAnyDuplicateV1(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int index = Math.abs(arr[i]);
            if (arr[index] < 0) {
                return index;
            }
            arr[index] = -arr[index];
        }
        throw new IllegalArgumentException("Invalid input array it should have elements in range [1, arr.length-1]");
    }

    /**
     * Version 2. We use binary search in the range of 1...N. Time complexity: O(NlogN).
     * We consider that an array is IMMUTABLE.
     */
    public int findAnyDuplicateV2(int[] arr) {
        // Range [1, N]
        int low = 1;
        int high = arr.length - 1;
        int duplicate = -1;
        while (low <= high) {
            int cur = (low + high) / 2;
            int count = countLessThatOrEqual(cur, arr);
            if (count > cur) {
                duplicate = cur;
                high = cur - 1;
            } else {
                low = cur + 1;
            }
        }
        if (duplicate == -1) {
            throw new IllegalArgumentException("Invalid input array it should have elements in range [1, arr.length-1]");
        }
        return duplicate;
    }

    private int countLessThatOrEqual(int target, int[] arr) {
        int count = 0;
        for (int val : arr) {
            if (val <= target) {
                count++;
            }
        }
        return count;
    }

    /**
     * Version 3. We use 2-pointers approach (trying to find cycle in the array). Time complexity: O(N).
     * We consider that an array is IMMUTABLE. And only ONE duplicated element exists in
     * the array.
     */
    public int findAnyDuplicateV3(int[] arr) {
        int slow = arr[0];
        int fast = arr[0];
        do {
            slow = arr[slow];
            fast = arr[arr[fast]];
        } while (slow != fast);

        slow = arr[0];
        while (slow != fast) {
            slow = arr[slow];
            fast = arr[fast];
        }
        return fast;
    }

    @Test
    void test0() {
        int[] input = {3, 5, 2, 6, 1, 1, 4, 7, 6};
        int result = findAnyDuplicateV1(input);
        assertTrue(Set.of(1, 6).contains(result));
    }

    @Test
    void test1() {
        int[] input = {3, 5, 2, 6, 1, 1, 4, 7, 6};
        int result = findAnyDuplicateV2(input);
        assertTrue(Set.of(1, 6).contains(result));
    }

    @Test
    void test2() {
        int[] input = {3, 5, 2, 1, 1, 4, 7, 6};
        int result = findAnyDuplicateV3(input);
        assertEquals(1, result);
    }
}
