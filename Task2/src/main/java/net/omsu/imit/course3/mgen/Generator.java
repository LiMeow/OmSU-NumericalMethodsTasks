package net.omsu.imit.course3.mgen;

import net.omsu.imit.course3.SquareMatrix;

public class Generator {

    public static SquareMatrix generateMatrix(double alpha,double beta,int N,int type){
        double[][] a = new double[N][];
        for (int i = 0; i < N; i++) a[i] = new double[N];

        double[][] a_inv = new double[N][];
        for (int i = 0; i < N; i++) a_inv[i] = new double[N];

        Gen g = new Gen();
        switch (type){
            case (0):
                g.mygen ( a, a_inv, N, alpha, beta, 1, 2, 0, 1 ); // симметричная
                break;
            case (1):
                g.mygen(a, a_inv, N, alpha, beta, 1, 2, 1, 1); //проостой структуры
                break;
            case(2):
                g.mygen ( a, a_inv, N, alpha, beta, 0, 0, 2, 1 ); //жорданова клетка
                break;
        }
        SquareMatrix matrix = new SquareMatrix(N, a);
        return matrix;
    }

    public static double[] generateX(int N){
        double[] X = new double[N];

        for (int i = 0; i < N; i++) {
            X[i] = Double.valueOf((int) (Math.random() * 100.));
        }
        return X;
    }

    public static double[] generateX0(int N){
        double[] X = new double[N];

        for (int i = 0; i < N; i++) {
            X[i] =0;
        }
        return X;
    }

    public static double[] generateF(SquareMatrix matrix,double []X,int N){
        double[] F = new double[N];

        for (int i = 0; i < N; i++) {
            double t = 0;
            for (int j = 0; j < N; j++) {
                t += matrix.getElem(i,j) * X[j];
            }
            F[i] = t;
        }
        return F;
    }
}
