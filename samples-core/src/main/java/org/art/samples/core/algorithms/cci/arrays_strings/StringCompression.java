package main.java.org.art.samples.core.algorithms.cci.arrays_strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "String Compression" solution from "Cracking the Coding Interview".
 */
public class StringCompression {

    public String compress(String str) {
        if (str == null) {
            throw new IllegalArgumentException("str cannot be null");
        }
        if (str.isEmpty()) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        char currenChar = str.charAt(0);
        int charCounter = 1;
        for (int i = 1; i < str.length(); i++) {
            if (currenChar == str.charAt(i)) {
                charCounter++;
            } else {
                sb.append(currenChar).append(charCounter);
                currenChar = str.charAt(i);
                charCounter = 1;
            }
            if (i == str.length() - 1) {
                sb.append(currenChar).append(charCounter);
            }
        }
        return sb.toString();
    }

    @Test
    void test0() {
        String input = "aabcccccaaa";
        String result = compress(input);
        assertEquals("a2b1c5a3", result);
    }

    @Test
    void test1() {
        String input = "abc";
        String result = compress(input);
        assertEquals("a1b1c1", result);
    }

    @Test
    void test2() {
        String input = "aaa";
        String result = compress(input);
        assertEquals("a3", result);
    }
}
