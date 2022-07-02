package org.art.java_core.algorithms.cci.arrays_strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * "Zero Matrix" solution from "Cracking the Coding Interview".
 */
public class ZeroMatrix {

    public void toZeroMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        boolean[] zeroRows = new boolean[m];
        boolean[] zeroColumns = new boolean[n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    if (!zeroRows[i]) {
                        zeroRows[i] = true;
                    }
                    zeroColumns[j] = true;
                }
            }
        }

        for (int i = 0; i < zeroRows.length; i++) {
            if (zeroRows[i]) {
                setRowElementsToZero(i, matrix);
            } else {
                setColumnElementsToZero(i, zeroColumns, matrix);
            }
        }
    }

    private void setRowElementsToZero(int row, int[][] matrix) {
        for (int j = 0; j < matrix[0].length; j++) {
            matrix[row][j] = 0;
        }
    }

    private void setColumnElementsToZero(int row, boolean[] zeroColumns, int[][] matrix) {
        for (int j = 0; j < matrix[0].length; j++) {
            if (zeroColumns[j]) {
                matrix[row][j] = 0;
            }
        }
    }

    @Test
    void test0() {
        int[][] input = {
            {1,  2,  3,  0,  5},
            {6,  7,  0,  8,  9},
            {10, 1,  0,  2,  0},
            {1,  1,  3,  2,  3}
        };
        toZeroMatrix(input);
        int[][] expected = {
            {0,  0,  0,  0,  0},
            {0,  0,  0,  0,  0},
            {0,  0,  0,  0,  0},
            {1,  1,  0,  0,  0}
        };
        assertArrayEquals(expected, input);
    }

    @Test
    void test1() {
        int[][] input = {
            {1,  2,  3},
            {6,  0,  2},
            {10, 1,  4}
        };
        toZeroMatrix(input);
        int[][] expected = {
            {1,  0,  3},
            {0,  0,  0},
            {10, 0,  4}
        };
        assertArrayEquals(expected, input);
    }
}
