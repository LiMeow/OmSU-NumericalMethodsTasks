package net.omsu.imit.course3;

public class VectorOperation {

    public static double[] multiplication(SquareMatrix mtrx,double[] x){
        int N=mtrx.getSize();
        double[] res=new double[N];
        double t;

        for(int i=0;i<N;i++){
            t=0;
            for(int j=0;j<N;j++){
                t+=mtrx.getElem(i,j)*x[j];
            }
            res[i]=t;
        }
        return res;
    }

    public static double[] subtraction(double[]x,double[] y){
        int N=x.length;
        double[] res=new double[N];

        for(int i=0;i<N;i++){
            res[i]=x[i]-y[i];
        }
        return res;
    }

    public static double[] addition(double[]x,double[] y){
        int N=x.length;
        double[] res=new double[N];

        for(int i=0;i<N;i++){
            res[i]=x[i]+y[i];
        }
        return res;
    }

    public static double[] multiplyByNumber(double[] x,double t){
        double[] res=new double[x.length];

        for(int i=0;i<x.length;i++){
           res[i]=x[i]*t;
        }
        return res;
    }

    public static void print(double[]x){
        for (int i = 0; i < x.length; i++) {
            System.out.print(x[i] + " ");
        }
    }
}
