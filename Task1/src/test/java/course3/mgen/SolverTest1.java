package course3.mgen;

import net.omsu.imit.course3.Solver;
import net.omsu.imit.course3.SquareMatrix;
import net.omsu.imit.course3.analizer.Analyzer;
import net.omsu.imit.course3.mgen.Gen;
import org.junit.Test;

public class SolverTest1 {
    private int N = 3;
    private double ALPHA = 1.;
    private double BETA = 1.e+1;

    @Test
    public void test() {
        int n = N;
        double alpha =ALPHA;
        double beta = BETA;

        double[][] a = new double[n][];
        for (int i = 0; i < n; i++) a[i] = new double[n];

        double[][] a_inv = new double[n][];
        for (int i = 0; i < n; i++) a_inv[i] = new double[n];

        Gen g = new Gen();
        //g.mygen(a, a_inv, n, alpha, beta, 1, 2, 1, 1); //проостой структуры
        g.mygen(a, a_inv, n, alpha, beta, 0, 0, 2, 1); //жорданова клетка


        Double[] f = new Double[n];
        double[] f_copy = new double[n];
        double[] expectedAnswers = new double[n];

        for (int i = 0; i < n; i++) {
            expectedAnswers[i] = Double.valueOf((int) (Math.random() * 10.));
        }

        for (int i = 0; i < n; i++) {
            double t = 0;
            for (int j = 0; j < n; j++) {
                t += a[i][j] * expectedAnswers[j];
            }
            f[i] = t;
            f_copy[i] = t;
        }
        SquareMatrix matrix = new SquareMatrix(n, a);
        Solver solver = new Solver();
        Double[] answers = solver.solve(matrix, f);

        double[] answ_copy = new double[n];
        for (int i = 0; i < n; i++) {
            answ_copy[i] = answers[i];
        }

        //g.mygen(a, a_inv, n, alpha, beta, 1, 2, 1, 1); //проостой структуры
        g.mygen(a, a_inv, n, alpha, beta, 0, 0, 2, 1); //жорданова клетка
        Analyzer.analize(a, f_copy, expectedAnswers, answ_copy);
    }
}
