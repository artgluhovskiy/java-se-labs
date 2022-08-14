package org.art.java_core.fork_join_pool.matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import lombok.SneakyThrows;

public class MatrixMultiplierAction extends RecursiveAction {

    private static final int ROWS_PER_TASK_THRESHOLD = 1;

    private static final int COLUMNS_PER_TASK_THRESHOLD = 1;

    private final int[][] a;

    private final int[][] b;

    private final int rowAStartNumber;

    private final int rowAEndNumber;

    private final int columnBStartNumber;

    private final int columnBEndNumber;

    private final int[][] result;

    public MatrixMultiplierAction(int[][] a, int[][] b, int rowAStartNumber, int rowAEndNumber, int columnBStartNumber, int columnBEndNumber,
                                  int[][] result) {
        this.a = a;
        this.b = b;
        this.rowAStartNumber = rowAStartNumber;
        this.rowAEndNumber = rowAEndNumber;
        this.columnBStartNumber = columnBStartNumber;
        this.columnBEndNumber = columnBEndNumber;
        this.result = result;
    }

    @Override
    protected void compute() {
        if ((rowAEndNumber - rowAStartNumber + 1 <= ROWS_PER_TASK_THRESHOLD)
            && (columnBEndNumber - columnBStartNumber + 1 <= COLUMNS_PER_TASK_THRESHOLD)) {
            multiply(a, b, rowAStartNumber, rowAEndNumber, columnBStartNumber, columnBEndNumber, result);
        } else {
            ForkJoinTask.invokeAll(createSubActions());
        }
    }

    private List<MatrixMultiplierAction> createSubActions() {
        List<MatrixMultiplierAction> actions = new ArrayList<>();

        if (rowAEndNumber - rowAStartNumber + 1 > ROWS_PER_TASK_THRESHOLD) {
            int newRowAEndNumber = (rowAStartNumber + rowAEndNumber) / 2;

            actions.add(new MatrixMultiplierAction(a, b, rowAStartNumber, newRowAEndNumber,
                columnBStartNumber, columnBEndNumber, result));
            actions.add(new MatrixMultiplierAction(a, b, newRowAEndNumber + 1, rowAEndNumber,
                columnBStartNumber, columnBEndNumber, result));
            return actions;
        }

        if (columnBEndNumber - columnBStartNumber + 1 > COLUMNS_PER_TASK_THRESHOLD) {
            int newColumnBEndNumber = (columnBStartNumber + columnBEndNumber) / 2;

            actions.add(new MatrixMultiplierAction(a, b, rowAStartNumber, rowAEndNumber,
                columnBStartNumber, newColumnBEndNumber, result));
            actions.add(new MatrixMultiplierAction(a, b, rowAStartNumber, rowAEndNumber,
                newColumnBEndNumber + 1, columnBEndNumber, result));
            return actions;
        }

        return actions;
    }

    @SneakyThrows
    public int[][] multiply(int[][] a, int[][] b, int rowAStartNumber, int rowAEndNumber, int columnBStartNumber, int columnBEndNumber,
                            int[][] result) {
        int aColumns = a[0].length;
        int bRows = b.length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("The matrices are not compatible");
        }

        for (int i = rowAStartNumber; i <= rowAEndNumber; i++) {
            for (int j = columnBStartNumber; j <= columnBEndNumber; j++) {
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
}
