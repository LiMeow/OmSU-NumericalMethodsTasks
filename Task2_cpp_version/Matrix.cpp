#include <iostream>
#include "Matrix.h"
using namespace std;

double** create(int n) {
	double** matrix = new double*[n];
	for (int i = 0; i < n; i++) {
		matrix[i] = new double[n];
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			matrix[i][j] = 0; 
		}
	}
	return matrix;
}

void destroy(double** mtrx, int n) {
	for (int i = 0; i < n; i++) {
		delete[] mtrx[i]; 
	}
	delete[] mtrx;
	mtrx = 0;
}

void print(double** mtrx, int n) {
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) { 
			printf("% .6f\t", mtrx[i][j]);
		}
		cout << endl;
	}
	cout << endl;
}

