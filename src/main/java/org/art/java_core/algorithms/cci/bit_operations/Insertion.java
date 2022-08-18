package org.art.java_core.algorithms.cci.bit_operations;

import org.art.java_core.algorithms.utils.bits.BitOperations;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * "Insertion" solution from "Cracking the Coding Interview".
 */
public class Insertion {

    public int insert(int n, int m, int i, int j) {
        int result = n;
        for (int k = i; k <= j; k++) {
            result = BitOperations.clearBit(n, k);
        }
        return result | (m << i);
    }

    @Test
    void test0() {
        int n = 0b10000000000;
        int m = 0b10011;
        int i = 2;
        int j = 6;
        int result = insert(n, m, i, j);
        int expected = 0b10001001100;
        assertEquals(expected, result);
    }
}
