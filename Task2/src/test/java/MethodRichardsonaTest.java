import net.omsu.imit.course3.Analyzer;
import net.omsu.imit.course3.SquareMatrix;
import org.junit.Test;

import java.util.Map;

import static net.omsu.imit.course3.Solver.methodRichardsona;
import static net.omsu.imit.course3.VectorOperation.print;
import static net.omsu.imit.course3.mgen.Generator.*;

public class MethodRichardsonaTest {
    private int N = 3;
    private double EPS=1.e-15;
    private double ALPHA = 1.;
    private double BETA = 1.;

    @Test
    public void test() {
            double alpha = ALPHA;
            double beta = BETA;
            int type = 1;//тип матрицы
            /*
             * 0 :симметричная
             * 1 :проостой структуры
             * 2 :жорданова клетка
             */
            SquareMatrix matrix = generateMatrix(alpha, beta, N, type);
            double[] expectedX = generateX(N);
            double[] F = generateF(matrix, expectedX, N);
            double[] X0 = generateX0(N);

            System.out.print("expectedX: ");
            print(expectedX);

            Map<String,Object> answer=methodRichardsona(matrix, X0, F, alpha, beta, EPS);
            int iteration= (int) answer.get("iteration");
            double[] actualX = (double[]) answer.get("X");

            System.out.print("\niteration: "+iteration);
            System.out.print("\nactualX: ");
            print(actualX);

            Analyzer.analize(matrix, F, expectedX, actualX);
    }
}
