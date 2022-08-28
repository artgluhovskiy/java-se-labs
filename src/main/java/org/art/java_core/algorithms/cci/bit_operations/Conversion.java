package org.art.java_core.algorithms.cci.bit_operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Conversion" solution from "Cracking the Coding Interview".
 *
 * Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
 * EXAMPLE
 * Input: 29 (or: 11101), 15 (or: 01111) Output: 2
 */
public class Conversion {

    public int bitSwapRequiredV1(int a, int b) {
        int count = 0;
        for (int c = a ^ b; c != 0; c = c >> 1) {
            count = count + (c & 1);
        }
        return count;
    }

    // Interesting optimization here!
    public int bitSwapRequiredV2(int a, int b) {
        int count = 0;
        for (int c = a ^ b; c != 0; c = c & (c - 1)) {
            count++;
        }
        return count;
    }

    @Test
    void test0() {
        int a = 199;        // 0000 0000 0000 0000 0000 0000 1100 0111
        int b = 151;        // 0000 0000 0000 0000 0000 0000 1001 0111
        int expected = 2;
        assertEquals(expected, bitSwapRequiredV1(a, b));
    }

    @Test
    void test1() {
        int a = 199;        // 0000 0000 0000 0000 0000 0000 1100 0111
        int b = 151;        // 0000 0000 0000 0000 0000 0000 1001 0111
        int expected = 2;
        assertEquals(expected, bitSwapRequiredV2(a, b));
    }
}
