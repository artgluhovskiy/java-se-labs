package org.art.java_core.algorithms.cci.arrays_strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

/**
 * "Rotate Matrix" solution from "Cracking the Coding Interview" (in-place rotation).
 */
public class RotateMatrix {

    /*
     * Schema of rotation:
     *   .   v1  .   .
     *   .   .   .  v2
     *   v4  .   .   .
     *   .   .   v3  .
     */
    public void rotate(int[][] matrix) {
        int size = matrix.length;
        for (int i = 0; i < size / 2; i++) {
            for (int j = 0; j < size / 2; j++) {
                int temp = matrix[j][size - i - 1];                             //store v2 to temp
                matrix[j][size - i - 1] = matrix[i][j];                         //v1 -> v2
                matrix[i][j] = matrix[size - j - 1][i];                         //v4 -> v1
                matrix[size - j - 1][i] = matrix[size - i - 1][size - j - 1];   //v3 -> v4
                matrix[size - i - 1][size - j - 1] = temp;                      //temp -> v3
            }
        }
    }

    @Test
    void test0() {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16}
        };
        int[][] expected = {
            {13, 9, 5, 1},
            {14, 10, 6, 2},
            {15, 11, 7, 3},
            {16, 12, 8, 4}
        };
        rotate(matrix);
        assertArrayEquals(matrix, expected);
    }
}
