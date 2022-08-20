package org.art.java_core.algorithms.cci.bit_operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Binary to String" solution from "Cracking the Coding Interview".
 *
 * Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double,
 * print the binary representation. If the number cannot be represented accurately
 * in binary with at most 32 characters, print"ERROR:'
 */
public class BinaryToString {

    public String binaryToString(double num) {
        if (num <= 0 || num >= 1) {
            return "ERROR";
        }
        StringBuilder result = new StringBuilder();
        result.append(".");

        while (num > 0) {
            if (result.length() >= 32) {
                return "ERROR";
            }

            double r = num * 2;
            if (r >= 1) {
                result.append("1");
                num = r - 1;
            } else {
                result.append("0");
                num = r;
            }
        }

        return result.toString();
    }

    @Test
    void test0() {
        double input = 0.75;
        String result = binaryToString(input);
        assertEquals(".11", result);
    }
}
