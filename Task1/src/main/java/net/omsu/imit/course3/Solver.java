package net.omsu.imit.course3;

import java.util.Arrays;

public class Solver {

    private <T extends Number> void swap(T[] elements, int i, int j) {
        T element = elements[i];
        elements[i] = elements[j];
        elements[j] = element;
    }

    private int findMaxColumn(Double[] vectorsLengthSquares, int index) {
        int max = index;
        for (int i = index; i < vectorsLengthSquares.length; i++) {
            if (vectorsLengthSquares[i] > vectorsLengthSquares[max])
                max = i;
        }
        return max;
    }

    private int findMaxRow(SquareMatrix matrix, int index) {
        int max = index;
        for (int i = index; i < matrix.getSize(); i++) {
            if (matrix.getElem(i, index - 1) > matrix.getElem(max, index - 1)) {
                max = i;
            }
        }
        return max;
    }

    private void makeTransposition(SquareMatrix matrix, Double[] f, Integer[] col_transposition, Double[] vectorsLengthSquares, int step) {
        int maxColumn = findMaxColumn(vectorsLengthSquares, step);
        matrix.swapСolumns(step, maxColumn);

        swap(col_transposition, step, maxColumn);
        swap(vectorsLengthSquares, step, maxColumn);

        int maxRow = findMaxRow(matrix, step + 1);
        matrix.swapRows(step + 1, maxRow);
        swap(f, step + 1, maxRow);

    }

    private void recalculationOfLengthSquares(SquareMatrix matrix, Double[] vectorsLengthSquares, int step) {
        int size = vectorsLengthSquares.length;
        for (int j = step; j < size - 1; j++) {
            for (int k = j; k < size; k++) {
                vectorsLengthSquares[k] -= matrix.getElem(j - 1, k)*matrix.getElem(j - 1, k);
            }
        }
    }

    private void rotate(SquareMatrix matrix, Double[] f, int firstRow, int secondRow) {
        int size = matrix.getSize();

        double[] firstRowCopy = matrix.getRow(firstRow);
        double[] secondRowCopy = matrix.getRow(secondRow);

        double firstElem = firstRowCopy[firstRow];
        double secondElem = secondRowCopy[firstRow];

        double z = Math.max(Math.abs(firstElem), Math.abs(secondElem));
        double l = Math.min(Math.abs(firstElem), Math.abs(secondElem));

        double aj = firstElem / z;
        double ai = secondElem / z;
        double al = l / z;

        double denominator = Math.sqrt(1.0 + al*al);
        double sin = ai / denominator;
        double cos = aj / denominator;

        double[] firstBuffer = new double[size];
        double[] secondBuffer = new double[size];

        for (int j = 0; j < size; j++) {
            firstBuffer[j] = firstRowCopy[j] * cos + secondRowCopy[j] * sin;
            secondBuffer[j] = firstRowCopy[j] * (-sin) + secondRowCopy[j]*cos;
        }
        firstElem = f[firstRow] * cos + f[secondRow] * sin;
        secondElem = f[firstRow] * (-sin) + f[secondRow] * cos;

        matrix.setRow(firstRow, firstBuffer);
        matrix.setRow(secondRow, secondBuffer);
        f[firstRow] = firstElem;
        f[secondRow] = secondElem;
    }

    public Double[] solve(SquareMatrix matrix, Double[] f) {
        int size = matrix.getSize();
        int step = 0;

        Integer[] col_transposition = new Integer[size];
        Double[] vectorsLengthSquares = new Double[size];

        for (int k = 0; k < size; k++) {
            col_transposition[k] = k;
            vectorsLengthSquares[k] = matrix.squareOfVectorLength(k);
        }

        for (int j = 0; j < size - 1; j++) {
            makeTransposition(matrix, f, col_transposition, vectorsLengthSquares, j);
            for (int i = step + 1; i <size; i++) {
                rotate(matrix, f, step, i);
            }
            step++;
            recalculationOfLengthSquares(matrix, vectorsLengthSquares, step);
        }
        return findX(matrix, f, col_transposition, vectorsLengthSquares);
    }

    private Double[] findX(SquareMatrix matrix, Double[] f, Integer[] col_transposition, Double[] vectorsLengthSquares) {
        int size = matrix.getSize();
        Double[] x = new Double[size];

        for (int i = size - 1; i > -1; i--) {
            x[i] = f[i];
            for (int j = i; j < size - 1; j++) {
                x[i] -= x[j + 1] * matrix.getElem(i, j + 1);
            }
            x[i] /= matrix.getElem(i, i);
        }

        for (int i = 0; i < size; i++) {
            if (col_transposition[i] != i) {
                swap(x,col_transposition[i],i);
                swap(col_transposition,col_transposition[i],i);
                i--;
            }
        }
        return x;
    }
}
