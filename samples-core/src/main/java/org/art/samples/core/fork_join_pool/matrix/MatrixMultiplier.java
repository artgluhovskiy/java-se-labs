package org.art.samples.core.fork_join_pool.matrix;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

@Slf4j
public class MatrixMultiplier {

    @SneakyThrows
    public int[][] multiply(int[][] a, int[][] b) {
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
                    int multi = a[i][k] * b[k][j];

                    //Some heavy work load here
                    TimeUnit.MILLISECONDS.sleep(multi);

                    result[i][j] += multi;
                }
            }
        }

        return result;
    }

    @SneakyThrows
    public int[][] multiplyParallel(int[][] a, int[][] b) {
        int aRows = a.length;
        int aColumns = a[0].length;

        int bRows = b.length;
        int bColumns = b[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("The matrices are not compatible");
        }

        int[][] result = new int[aRows][bColumns];

        MatrixMultiplierAction action = new MatrixMultiplierAction(a, b, 0, aRows - 1, 0,
            bColumns - 1, result);

        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        forkJoinPool.execute(action);
        action.join();

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

        long start = System.nanoTime();
        int[][] result1 = multiply(a, b);
        long elapsedTime = System.nanoTime() - start;
        log.info("Single threaded multiplication elapsed time: {}", elapsedTime / 1000);
        assertArrayEquals(expected, result1);

        start = System.nanoTime();
        int[][] result2 = multiplyParallel(a, b);
        elapsedTime = System.nanoTime() - start;
        log.info("Multi threaded multiplication elapsed time: {}", elapsedTime / 1000);
        assertArrayEquals(expected, result2);
    }
}
