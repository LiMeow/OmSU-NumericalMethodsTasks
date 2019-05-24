#include <iostream>
#include "VectorOperation.h"
#include "Matrix.h"
#include "Method.h"
#include "Generator.h"
#include "Analizer.h"
using namespace std;

#define EPS 1.e-15
#define N 100
#define ALPHA 1.e-5
#define BETA 1.

int main() {
	double alpha = ALPHA;
	double beta = BETA;
	double eps = EPS;
	
	double **a = new double*[N];
	for (int i = 0; i < N; i++)
		a[i] = new double[N];

	double **a_inv = new double*[N];
	for (int i = 0; i < N; i++)
		a_inv[i] = new double[N];

	//mygen(a, a_inv, N, ALPHA, BETA, 1, 2, 0, 1);	  // симметричная
	mygen ( a, a_inv, N, alpha, beta, 1, 2, 1, 1 ); //проостой структуры

	double* expectedX = createVector(N);
	double* actualX = createVector(N);
	double* F = createVector(N);


	for (int i = 0; i <N; i++) {
		expectedX[i] = rand() % 10;
	}

	for (int i = 0; i < N; i++) {
		double t = 0;
		for (int j = 0; j < N; j++) {
			t += a[i][j] * expectedX[j];
		}
		F[i] = t;
	}


	printVector(expectedX, N);
	int countIterations = methodRichardsona(a,actualX,F,ALPHA,BETA,eps,N);
	printVector(actualX, N);
	analize(a, F, expectedX, actualX, N);

	cout << "\nIteration: " << countIterations << endl;
	destroy(a,N);
	destroy(a_inv,N);

	delete[]	expectedX;
	delete[]	actualX;
	delete[]	F;
	system("pause");
	return 0;
}
