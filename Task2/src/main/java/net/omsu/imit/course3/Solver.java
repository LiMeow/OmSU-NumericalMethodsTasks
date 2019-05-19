package net.omsu.imit.course3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static net.omsu.imit.course3.Analyzer.normOfDiscrepancy;
import static net.omsu.imit.course3.VectorOperation.*;

/*
 *r=max|f-Ax| -норма невязки
 *matrix-матрица СЛАУ
 *X-начальное приближение
 *f-правая часть СЛАУ
 *K-число итераций
 *t(k)=cos(2k-1)*PI/2K, k=0,...,K-1 -корни полинома Чебышева К-й степени
 *tau(k)=2/((lambda_MAX+lambda_MIN)+(lambda_MAX-lambda_MIN)*t(k+1))
 */
public class Solver {
private static final int K=35000000;//MAX-количество итераций

    public static Map<String, Object> methodRichardsona(SquareMatrix matrix, double[] X, double[] f, double lambda_MIN, double lambda_MAX, double eps) {
        int iteration = 0;
        double[] newX;
        double t, tau;
        double feps = eps * Arrays.stream(f).map(Math::abs).max().getAsDouble();
        double normOfDiscrepancy;
        do {
            t = Math.cos(((2 * (iteration + 1) - 1) * Math.PI) / (2 * K));//t(k+1)
            tau = 2 / ((lambda_MAX + lambda_MIN) + (lambda_MAX - lambda_MIN) * t);//tau(k)
            newX = multiplication(matrix, X);//А*x(k)
            newX = subtraction(f, newX);//(f-A*x(k))
            normOfDiscrepancy = normOfDiscrepancy(newX);//||r||
            newX = multiplyByNumber(newX, tau);//(f-Ax(k))*tau(k)
            newX = addition(newX, X);//x(k+1)=f-Ax(k))*tau(k)+x(k)
            iteration++;
            X = newX;
        } while (normOfDiscrepancy > feps && iteration < K);
        Map<String, Object> answer = new HashMap<>();
        answer.put("iteration", iteration);
        answer.put("X", newX);
        return answer;
    }
}
//убрать постоянное выделение памяти
//брать рандомное tau (где то его выдергивать постоянно)
//поставить К в сам метод
//пересчитать таблицу для разных значений eps
