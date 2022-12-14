package main.java.org.art.samples.core.algorithms.cci.arrays_strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * "Is Unique" solution from "Cracking the Coding Interview" (without using additional data structures).
 */
public class IsUnique {

    public boolean isUnique(String str) {
        if (str == null) {
            throw new IllegalArgumentException("str cannot be null!");
        }
        if (str.length() < 2) {
            return true;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            char targetChar = str.charAt(i);
            for (int j = i + 1; j < str.length(); j++) {
                char currentChar = str.charAt(j);
                if (targetChar == currentChar) {
                    return false;
                }
            }
        }
        return true;
    }

    @Test
    void test0() {
        String str = "";
        boolean result = isUnique(str);
        assertTrue(result);
    }

    @Test
    void test1() {
        String str = "a";
        boolean result = isUnique(str);
        assertTrue(result);
    }

    @Test
    void test2() {
        String str = "abcda";
        boolean result = isUnique(str);
        assertFalse(result);
    }

    @Test
    void test3() {
        String str = "abcdc";
        boolean result = isUnique(str);
        assertFalse(result);
    }

    @Test
    void test4() {
        String str = "aa";
        boolean result = isUnique(str);
        assertFalse(result);
    }
}
