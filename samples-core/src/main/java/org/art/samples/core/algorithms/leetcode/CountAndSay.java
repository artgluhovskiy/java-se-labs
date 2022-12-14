package main.java.org.art.samples.core.algorithms.leetcode;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Count-and-say" quiz solution from LeetCode.
 */
public class CountAndSay {

    public String countAndSay(int n) {
        return countAndSayHelper(n - 1);
    }

    private String countAndSayHelper(int n) {
        if (n == 0) {
            return "1";
        } else {
            String result = countAndSayHelper(n - 1);
            return say(result);
        }
    }

    private String say(String digits) {
        StringBuilder result = new StringBuilder();
        if (digits == null || digits.isEmpty()) {
            return result.toString();
        }
        char currentGroupChar = digits.charAt(0);
        int currentGroupCharCount = 1;
        for (int i = 1; i < digits.length(); i++) {
            char ch = digits.charAt(i);
            if (currentGroupChar != ch) {
                result.append(currentGroupCharCount).append(currentGroupChar);
                currentGroupChar = ch;
                currentGroupCharCount = 1;
            } else {
                currentGroupCharCount++;
            }
        }
        result.append(currentGroupCharCount).append(currentGroupChar);
        return result.toString();
    }

    @Test
    void testGroup0() {
        String input = "3322251";
        String expected = "23321511";
        assertEquals(expected, say(input));
    }

    @Test
    void test0() {
        int input = 1;
        String expectedResult = "1";
        assertEquals(expectedResult, countAndSay(input));
    }

    @Test
    void test1() {
        int input = 2;
        String expectedResult = "11";
        assertEquals(expectedResult, countAndSay(input));
    }

    @Test
    void test2() {
        int input = 3;
        String expectedResult = "21";
        assertEquals(expectedResult, countAndSay(input));
    }

    @Test
    void test3() {
        int input = 4;
        String expectedResult = "1211";
        assertEquals(expectedResult, countAndSay(input));
    }

    @Test
    void test4() {
        int input = 5;
        String expectedResult = "111221";
        assertEquals(expectedResult, countAndSay(input));
    }
}
