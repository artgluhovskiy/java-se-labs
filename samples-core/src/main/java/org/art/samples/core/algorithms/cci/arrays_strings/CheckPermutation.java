package org.art.samples.core.algorithms.cci.arrays_strings;

import java.util.Map;

import org.art.samples.core.algorithms.utils.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Check Permutation" solution from "Cracking the Coding Interview".
 */
public class CheckPermutation {

    public boolean checkPermutation(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("str1, str2 cannot be null");
        }
        if (str1.length() != str2.length()) {
            return false;
        }
        Map<Character, Integer> charCounts1 = Utils.toCharCountMap(str1);
        Map<Character, Integer> charCounts2 = Utils.toCharCountMap(str2);
        return charCounts1.equals(charCounts2);
    }

    @Test
    void test0() {
        String s1 = "abc";
        String s2 = "bca";
        boolean result = checkPermutation(s1, s2);
        assertTrue(result);
    }

    @Test
    void test1() {
        String s1 = "abca";
        String s2 = "bca";
        boolean result = checkPermutation(s1, s2);
        assertFalse(result);
    }

    @Test
    void test2() {
        String s1 = "a";
        String s2 = "a";
        boolean result = checkPermutation(s1, s2);
        assertTrue(result);
    }

    @Test
    void test3() {
        String s1 = "c";
        String s2 = "a";
        boolean result = checkPermutation(s1, s2);
        assertFalse(result);
    }

    @Test
    void test4() {
        String s1 = "cccc";
        String s2 = "cccc";
        boolean result = checkPermutation(s1, s2);
        assertTrue(result);
    }

    @Test
    void test5() {
        String s1 = "cccc";
        String s2 = "cvcc";
        boolean result = checkPermutation(s1, s2);
        assertFalse(result);
    }

    @Test
    void test6() {
        String s1 = "ccbb";
        String s2 = "bcbc";
        boolean result = checkPermutation(s1, s2);
        assertTrue(result);
    }

    @Test
    void test7() {
        String s1 = "ccbb";
        String s2 = "bccc";
        boolean result = checkPermutation(s1, s2);
        assertFalse(result);
    }
}
