#include <iostream>
#include "Analizer.h"
#include "Matrix.h"
#include "VectorOperation.h"
#include<cmath>
using namespace std;

void analize(double ** mtrx, double * F, double * expectedX, double * actualX, int N)
{
	double norma_Z = normaZ(expectedX, actualX,N);
	double norma_X = normOfDiscrepancy(expectedX,N);
	double norma_F = normOfDiscrepancy(F, N);
	double dzeta = norma_Z / norma_X;
	double norma_R = normaR(mtrx, F, actualX, N);
	double ro = norma_R / norma_F;

	cout << "\n||z|| = " << norma_Z << endl;
	cout << "dzeta = " << dzeta << endl;
	cout << "||r|| = " << norma_R << endl;
	cout << "ro = " << ro << endl;
}

double normaZ(double* expectedX, double * actualX, int N)
{
	double*t = copyVector(actualX,N);
	subtraction(expectedX, t,N);
	return normOfDiscrepancy(t, N);
}

double normaR(double ** mtrx, double * F, double * X, int N)
{
	double*r = new double[N];
	for (int i = 0; i < N; i++) {
		double t = 0;
		for (int j = 0; j < N; j++) {
			t += mtrx[i][j]* X[j];
		}
		r[i] = t - F[i];
	}
	return normOfDiscrepancy(r, N);
}

double normOfDiscrepancy(double * X, int N)
{
	double max = 0.;
	for (int i = 0; i < N; i++) {
		if (max < abs(X[i])) {
			max = abs(X[i]);
		}
	}
	return max;
}
