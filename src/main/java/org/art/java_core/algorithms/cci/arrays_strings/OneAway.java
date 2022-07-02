package org.art.java_core.algorithms.cci.arrays_strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "One Away" solution from "Cracking the Coding Interview".
 */
public class OneAway {

    public boolean isOneAway(String s1, String s2) {
        if (s1 == null || s2 == null) {
            throw new IllegalArgumentException("s1 or s2 cannot be null");
        }
        if (s1.length() - s2.length() == 0) {
            return isOneReplacementAway(s1, s2);
        } else if (s1.length() - s2.length() == 1) {
            return isOneInsertionAway(s2, s1);
        } else if (s2.length() - s1.length() == 1) {
            return isOneInsertionAway(s1, s2);
        } else {
            return false;
        }
    }

    private boolean isOneReplacementAway(String s1, String s2) {
        boolean isCharReplaced = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (isCharReplaced) {
                    return false;
                } else {
                    isCharReplaced = true;
                }
            }
        }
        return true;
    }

    private boolean isOneInsertionAway(String shortStr, String longStr) {
        int shortIndex = 0;
        int longIndex = 0;
        boolean charInserted = false;
        while (shortIndex < shortStr.length() && longIndex < longStr.length()) {
            if (shortStr.charAt(shortIndex) != longStr.charAt(longIndex)) {
                if (charInserted) {
                    return false;
                }
                longIndex++;
                charInserted = true;
            } else {
                shortIndex++;
                longIndex++;
            }
        }
        return true;
    }

    @Test
    void test0() {
        String s1 = "pale";
        String s2 = "ple";
        boolean result = isOneAway(s1, s2);
        assertTrue(result);
    }

    @Test
    void test1() {
        String s1 = "pales";
        String s2 = "pale";
        boolean result = isOneAway(s1, s2);
        assertTrue(result);
    }

    @Test
    void test2() {
        String s1 = "pale";
        String s2 = "bale";
        boolean result = isOneAway(s1, s2);
        assertTrue(result);
    }

    @Test
    void test3() {
        String s1 = "pale";
        String s2 = "bake";
        boolean result = isOneAway(s1, s2);
        assertFalse(result);
    }
}
