package org.art.samples.core.algorithms.cci.bit_operations;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Pairwise Swap" solution from "Cracking the Coding Interview".
 *
 * Write a program to swap odd and even bits in an integer with as few instructions as possible
 * (e.g., bit O and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
 */
public class PairwiseSwap {

    public int swapOddEvenBits(int a) {
        return ((a & 0b01010101010101010101010101010101) << 1) | ((a & 0b10101010101010101010101010101010) >>> 1);
    }

    @Test
    void test0() {
        int a = 167;        // 0000 0000 0000 0000 0000 0000 1010 0111
        int expected = 91;  // 0000 0000 0000 0000 0000 0000 0101 1011
        assertEquals(expected, swapOddEvenBits(a));
    }
}
