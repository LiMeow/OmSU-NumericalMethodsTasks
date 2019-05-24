#include <iostream>
#include "VectorOperation.h"
#include <cmath>
using namespace std;
#define PI 3.141592653589793238462643383279502884

double* createVector(int n) {
	double* vector = new double[n];
	for (int i = 0; i < n; i++) {
		vector[i] = 0;
	}
	return vector;
}

void multiplication(double **mtrx, double *&X, int N) {
	double* t=createVector(N);
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			t[i]+=mtrx[i][j]*X[j];
		}
	}
	X = t;
}

void multiplyByNumber(double *& X, double tau,int N)
{
	for (int i = 0; i < N; i++) {
		X[i] *= tau;
	}
}

void subtraction(double* F, double *& X, int N)
{
	for (int i = 0; i < N; i++) {
			X[i] = F[i]-X[i];
	}
}

void addition(double *& oldX, double*& newX, int N)
{
	for (int i = 0; i < N; i++) {
		newX[i] += oldX[i];
		oldX[i] = newX[i];
	}
}

void printVector(double*X,int N) {
	for (int i = 0; i < N; i++) {
		cout<<X[i]<<" ";
	}
	cout << endl;
}

double* copyVector(double*X,int N) {
	double*t =createVector(N);
	for (int i = 0; i < N; i++) {
		t[i] += X[i];
	}
	return t;
}


