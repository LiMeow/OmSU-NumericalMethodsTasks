package net.omsu.imit.course3;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Objects;

import static java.math.BigDecimal.ROUND_CEILING;

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

    public SquareMatrix(SquareMatrix mtrx) {
        if (mtrx.size != size) throw new IllegalArgumentException();
        size = mtrx.size;
        System.arraycopy(mtrx, 0, matrix, 0, size * size);
    }

    public double getElem(int i, int j) {
        if (i < 0 || j < 0 || i >= size || j >= size) throw new ArrayIndexOutOfBoundsException();
        return matrix[i][j];
    }

    public int getSize() {
        return size;
    }

    public double[] getRow(int index) {
        double[] row = new double[size];
        for (int k = 0; k < size; k++) {
            row[k] = matrix[index][k];
        }
        return row;
    }

    public void setRow(int index, double[] row) {
        for (int k = 0; k < size; k++) {
            matrix[index][k] = row[k];
        }
        detIsFound = false;
    }

    public void setElem(int i, int j, double elem) {
        if (i < 0 || j < 0 || i >= size || j >= size) throw new ArrayIndexOutOfBoundsException();
        matrix[i][j] = elem;
        detIsFound = false;
    }

    public double det() {
        if (detIsFound) return det;
        else {
            SquareMatrix mtrx = new SquareMatrix(this);
            int eps=100000000;
            double det = 1;
            double r;
            int i = 0, j = 0, k, l = 0;
            while (i < size && j < size) {
                r = 0.0;
                for (k = i; k < size; k++) {
                    if (Math.abs(mtrx.getElem(j, k)) > r) {
                        l = k;
                        r = Math.abs(mtrx.getElem(j, k));
                    }
                }
                if (r <= 0.000000000001) {
                    for (k = i; k < size; k++) {
                        mtrx.setElem(j, k, 0.0);
                    }
                    j++;
                    continue;
                }
                if (l != i) {
                    for (k = j; k < size; k++) {
                        r = mtrx.getElem(k, i);
                        mtrx.setElem(k, i, mtrx.getElem(k, l));
                        mtrx.setElem(k, l, -r);
                    }
                }
                for (k = i + 1; k < size; k++) {
                    r = (-mtrx.getElem(j, k) / mtrx.getElem(j, i));
                    for (l = j; l < size; l++) {
                        mtrx.setElem(l, k, mtrx.getElem(l, k) + r * mtrx.getElem(l, i));
                    }
                }

                i++;
                j++;
            }
            for (i = 0; i < size; i++) {
                det *= mtrx.getElem(i, i);
            }
            det= Math.round(det*eps)/eps;
            detIsFound = true;
            this.det = det;
            return det;
        }
    }

    public double squareOfVectorLength(int i) {
        double res = 0.0;
        for (int j = 0; j < size; j++) {
            res+=matrix[j][i]*matrix[j][i];
        }
        return res;
    }

    public void swapСolumns(int i, int j) {
        for (int k = 0; k < size; k++) {
            double t = matrix[k][i];
            matrix[k][i] = matrix[k][j];
            matrix[k][j] = t;
        }
    }

    public void swapRows(int i, int j) {
        for (int k = 0; k < size; k++) {
            double t = matrix[i][k];
            matrix[i][k] = matrix[j][k];
            matrix[j][k] = t;
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
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
