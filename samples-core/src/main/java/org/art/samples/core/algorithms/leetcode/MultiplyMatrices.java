package org.art.samples.core.algorithms.leetcode;

import java.util.Objects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class MultiplyMatrices {

    public int[][] multiply(int[][] a, int[][] b) {
        Objects.requireNonNull(a);
        Objects.requireNonNull(b);

        // Validations are skipped

        int aRows = a.length;
        int aColumns = a[0].length;

        int bRows = b.length;
        int bColumns = b[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("The matrices are not compatible");
        }

        int[][] result = new int[aRows][bColumns];

        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < a[i].length; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    @Test
    void test0() {
        int[][] a = {
            {1, 2, 3},
            {4, 5, 6}
        };
        int[][] b = {
            {10, 11},
            {20, 21},
            {30, 31}
        };
        int[][] expected = {
            {140, 146},
            {320, 335}
        };
        assertArrayEquals(expected, multiply(a, b));
    }
}
