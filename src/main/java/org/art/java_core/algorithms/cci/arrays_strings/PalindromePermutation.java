package org.art.java_core.algorithms.cci.arrays_strings;

import java.util.Map;

import org.art.java_core.algorithms.common.Utils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Palindrome Permutation" solution from "Cracking the Coding Interview".
 */
public class PalindromePermutation {

    public boolean isPalindromePermutation(String str) {
        if (str == null) {
            throw new IllegalArgumentException("str cannot be null");
        }
        if (str.length() < 2) {
            return true;
        }
        Map<Character, Integer> charCountMap = Utils.toCharCountMap(str);
        charCountMap.remove(' ');

        int oddCountCharactersNumber = calculateOddCountCharactersNumber(charCountMap);

        int effectiveStrLength = str.replace(" ", "").length();
        if (effectiveStrLength % 2 == 0) {
            return oddCountCharactersNumber == 0;
        } else {
            return oddCountCharactersNumber == 1;
        }
    }

    private int calculateOddCountCharactersNumber(Map<Character, Integer> charCountMap) {
        return charCountMap.entrySet().stream()
            .filter(entry -> entry.getValue() % 2 == 1)
            .mapToInt(entry -> 1)
            .reduce(Integer::sum)
            .orElse(0);
    }

    @Test
    void test0() {
        String str = "vddvajaj";
        boolean result = isPalindromePermutation(str);
        assertTrue(result);
    }

    @Test
    void test1() {
        String str = "vddajajs";
        boolean result = isPalindromePermutation(str);
        assertFalse(result);
    }

    @Test
    void test2() {
        String str = "vddajv aj";
        boolean result = isPalindromePermutation(str);
        assertTrue(result);
    }

    @Test
    void test3() {
        String str = "a";
        boolean result = isPalindromePermutation(str);
        assertTrue(result);
    }

    @Test
    void test4() {
        String str = "a a";
        boolean result = isPalindromePermutation(str);
        assertTrue(result);
    }

    @Test
    void test5() {
        String str = "aa";
        boolean result = isPalindromePermutation(str);
        assertTrue(result);
    }

    @Test
    void test6() {
        String str = " aa";
        boolean result = isPalindromePermutation(str);
        assertTrue(result);
    }

    @Test
    void test7() {
        String str = " aac";
        boolean result = isPalindromePermutation(str);
        assertTrue(result);
    }
}
