#pragma once

double* createVector(int n);

void multiplication(double ** mtrx, double *&X, int N);

void multiplyByNumber(double*&X,double tau,int N);

void subtraction(double* F, double* &X, int N);

void addition(double*&oldX, double*&newX, int N);

void printVector(double*X, int N);

double* copyVector(double*X, int N);