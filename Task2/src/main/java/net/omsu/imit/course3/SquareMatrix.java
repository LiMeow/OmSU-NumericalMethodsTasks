package net.omsu.imit.course3;

import java.util.Arrays;
import java.util.Objects;

public class SquareMatrix {
    private int size;
    private double[][] matrix;
    private double det;
    private boolean detIsFound;

    public SquareMatrix(int size) {
        if (size < 1) throw new IllegalArgumentException();

        this.size = size;
        matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = 0.0;
            }
        }
        det = 0.0;
        detIsFound = false;
    }

    public SquareMatrix(int size, double[][] mtrxArray) {
        if (size < 1) throw new IllegalArgumentException();

        this.size = size;
        matrix = new double[size][size];
        matrix = mtrxArray.clone();
        det = 0.0;
        detIsFound = false;
    }

    public double getElem(int i, int j) {
        if (i < 0 || j < 0 || i >= size || j >= size) throw new ArrayIndexOutOfBoundsException();
        return matrix[i][j];
    }

    public int getSize() {
        return size;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SquareMatrix that = (SquareMatrix) o;
        return getSize() == that.getSize() &&
                detIsFound == that.detIsFound &&
                Arrays.equals(matrix, that.matrix) &&
                Objects.equals(det, that.det);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(getSize(), det, detIsFound);
        result = 31 * result + Arrays.hashCode(matrix);
        return result;
    }
}
