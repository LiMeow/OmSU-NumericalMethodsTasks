#pragma once

void analize(double **mtrx, double *F, double *expectedX, double *actualX, int N);

double normaZ(double *expectedX, double *actualX,int N);

double normaR(double **mtrx, double *F, double *X,int N);

double normOfDiscrepancy(double * X, int N);