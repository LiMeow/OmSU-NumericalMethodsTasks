package net.omsu.imit.course3;

import java.util.Arrays;

import static net.omsu.imit.course3.VectorOperation.subtraction;

public class Analyzer {

    public static void analize(SquareMatrix matrix, double[] F, double[] expectedX, double[] actualX) {
        double z_infinity = get_z_infinity(actualX, expectedX);
        double x_infinity = Arrays.stream(expectedX).map(Math::abs).max().getAsDouble();
        double f_infinity = Arrays.stream(F).map(Math::abs).max().getAsDouble();
        double dzeta = z_infinity / x_infinity;
        double r_infinity = get_r_infinity(matrix, F, actualX);
        double ro =r_infinity/f_infinity;

        System.out.println("\n||z|| = " + z_infinity);
        System.out.println("dzeta = " + dzeta);
        System.out.println("||r|| = " + r_infinity);
        System.out.println("ro = " + ro);
    }

    private static double get_z_infinity(double[] answers, double[] expectedAnsvers) {
        return Arrays.stream(subtraction(expectedAnsvers,answers)).map(Math::abs).max().getAsDouble();
    }

    private static double get_r_infinity(SquareMatrix mtrx, double[] f, double[] answers) {
        int n = answers.length;
        double[]r=new double[n];
        for (int i = 0; i < n; i++) {
            double t = 0;
            for (int j = 0; j < n; j++) {
                t += mtrx.getElem(i,j) * answers[j];
            }
          r[i]=t-f[i];
        }
        return Arrays.stream(r).map(Math::abs).max().getAsDouble();
    }

    public static double normOfDiscrepancy(double []x) {
        return Arrays.stream(x).map(Math::abs).max().getAsDouble();
    }

}
