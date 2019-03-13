package net.omsu.imit.course3.analizer;

import java.util.Arrays;

public class Analyzer {

    public static void analize(double[][] mtrx, double[] f, double[] expectedAnswers, double[] answers) {
        double z_infinity = get_z_infinity(answers, expectedAnswers);
        double x_infinity = Arrays.stream(expectedAnswers).map(Math::abs).max().getAsDouble();
        double f_infinity = Arrays.stream(f).map(Math::abs).max().getAsDouble();
        double dzeta = z_infinity / x_infinity;
        double r_infinity = get_r_infinity(mtrx, f, answers);
        double ro =r_infinity/f_infinity;

        System.out.println("||z|| = " + z_infinity);
        System.out.println("dzeta = " + dzeta);
        System.out.println("||r|| = " + r_infinity);
        System.out.println("ro = " + ro);
    }

    private static double get_z_infinity(double[] answers, double[] expectedAnsvers) {
        int n = answers.length;
        double[] z = new double[n];

        for (int i = 0; i < n; i++) {
            z[i] = answers[i] - expectedAnsvers[i];
        }
        return Arrays.stream(z).map(Math::abs).max().getAsDouble();
    }

    private static double get_r_infinity(double[][] mtrx, double[] f, double[] answers) {
        int n = answers.length;
        double[]r=new double[n];
        for (int i = 0; i < n; i++) {
            double t = 0;
            for (int j = 0; j < n; j++) {
                t += mtrx[i][j] * answers[j];
            }
          r[i]=t-f[i];
        }
        return Arrays.stream(r).map(Math::abs).max().getAsDouble();
    }

}
