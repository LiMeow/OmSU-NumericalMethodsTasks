#include <iostream>
#include <cmath>
#include <ctime>
#include "Method.h"
#include "Matrix.h"
#include "VectorOperation.h"
#include "Analizer.h"

#define tauNumbers 10
#define MAX 1.e+20
#define K   10000000	//MAX-количество итераций
#define PI 3.141592653589793238462643383279502884

/*
 *r=max|f-Ax| -норма невязки
 *matrix-матрица СЛАУ
 *X-начальное приближение
 *f-правая часть СЛАУ
 *K-число итераций
 */
int methodRichardsona(double ** matrix,double*& oldX, double * F,double lambda_MIN, double lambda_MAX, double eps,int N)
{
	srand(time(0));
	int iteration = 0;

	double t;
	double* tau = createVector(tauNumbers);
	for (int i = 0; i < tauNumbers; i++) {
		t = cos(((2 * i - 1)*PI) / (2 * tauNumbers));
		tau[i] = 2 / ((lambda_MAX + lambda_MIN) + (lambda_MAX - lambda_MIN) * t);	//tau(k)
	}

	double feps = eps * normOfDiscrepancy(F, N);
	double normOfDiscrepansy;
	double* newX = copyVector(oldX,N);

	do {
		multiplication(matrix, newX, N);									//newX = А*x(k)
		subtraction(F, newX, N);											//newX = F-A*x(k)
		normOfDiscrepansy = normOfDiscrepancy(newX, N);						//||r|| : считаем норму невязки
		multiplyByNumber(newX,tau[rand()%(tauNumbers-1)],N);										//newX = (f-Ax(k))*tau(k)
		addition(oldX,newX,N);												//oldX = x(k+1)=f-Ax(k))*tau(k)+x(k)
		iteration++;
		if (iteration > K) {
			return -iteration;
		}

	} while (normOfDiscrepansy > feps);
	return iteration;
}
