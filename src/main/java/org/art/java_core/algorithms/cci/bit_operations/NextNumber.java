package org.art.java_core.algorithms.cci.bit_operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Next Number" solution from "Cracking the Coding Interview".
 *
 * Given a positive integer, print the next smallest and the next largest number
 * that have the same number of 1 bits in their binary representation.
 */
public class NextNumber {

    public int nextLargest(int num) {
        //                                p
        // 0000 0000 0000 0000 0000 0110 0011 0000

        int c = num;
        int c0 = 0;     // number of zeros to the right of p
        int c1 = 0;     // number of ones to the right of p
        while ((c & 1) == 0 && (c != 0)) {
            c0++;
            c = c >> 1;
        }
        while ((c & 1) == 1) {
            c1++;
            c = c >> 1;
        }

        if (c0 + c1 == 32 || c0 + c1 == 0) {
            return -1;
        }

        int p = c0 + c1;

        num = num | (1 << p);               // Flip rightmost trailing zero
        num = num & ~((1 << p) - 1);        // Clear all bits to the right of p
        num = num | (1 << (c1 - 1)) - 1;    // Insert (c1 - 1) ones on the right
        return num;
    }

    public int nextSmallest(int num) {
        //                                  p
        // 0000 0000 0000 0000 0000 0110 0011 0011

        int c = num;
        int c0 = 0;
        int c1 = 0;
        while ((c & 1) == 1) {
            c1++;
            c = c >> 1;
        }
        while ((c & 1) == 0 && c != 0) {
            c0++;
            c = c >> 1;
        }

        int p = c0 + c1;
        num = num & ((~0) << (p + 1));                  // Clears from bit p onwards
        int mask = (1 << (c1 + 1)) - 1;                 // Sequence of (c1+1) ones
        num = num | (mask << (c0 - 1));
        return num;
    }

    @Test
    void test0() {
        int input = 224;        // 0000 0000 0000 0000 0000 0000 1110 0000
        int expected = 259;     // 0000 0000 0000 0000 0000 0001 0000 0011
        assertEquals(expected, nextLargest(input));
    }

    @Test
    void test1() {
        int input = 227;        // 0000 0000 0000 0000 0000 0000 1110 0011
        int expected = 220;     // 0000 0000 0000 0000 0000 0000 1101 1100
        assertEquals(expected, nextSmallest(input));
    }
}
