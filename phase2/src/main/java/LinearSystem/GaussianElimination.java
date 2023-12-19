package LinearSystem;
import javax.swing.*;

public class GaussianElimination {
    public  static String steps = "";
    public  static String result = "";
    public static final double EPSILON = 1e-7;
    public  static  int digit = 5;

    // back substitution function :
    public static String[] backSubstitution( double[][] y){
        // array of result
        int n= y.length;double [][]A=new double[n][n];double[]b =new double[n];
        scaling(y);
        for(int r=0;r<n;r++){
            for(int c=0;c<n;c++){
                A[r][c]=  y[r][c];
                b[r]= y[r][c+1];
            }
        }
        //print the matrix after scaling
        steps += "the matrix after scaling";
        Print(A,b,n);///////////////////////////////
        // x:matrix of solution //back substitution
        double[] x = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0F;
            //sum of coefficients
            for (int j = i + 1; j < n; j++) {
                sum =  Round( (Round(sum,digit) + Round( A[i][j] * x[j], digit)), digit);}
            x[i] =  Round( (Round((b[i] - sum), digit) / A[i][i]), digit);}
        for (int i = 0; i < x.length; i++){
            result = result +  x[i];
            if(i != x.length - 1)
                result = result + ",";
        }
        String sent[] = new String[2];
        sent[0] = result;
        sent[1] = steps;
        return sent;//////////////res
    }


    public static void singular(double[][]A){ int i,j; int n=A.length;int check=0;
        for ( i = 0; i< n; i++) {
            for ( j = 0; j < n; j++) {
                if(A[i][j]==0){ check++;
                    if (check==A.length){JOptionPane.showMessageDialog(null, "Error");;}}
            } check=0;
        }
    }

    // print the matrix (step by step)
    public static void Print(double[][]A,double[]b, int n){
        if (steps != "")
        {steps+= "&";}
        for(int r=0;r<n;r++){
            steps += "{";
            // System.out.print("{");
            for(int c=0;c<n;c++){
                steps += A[r][c];
                //System.out.print(A[r][c]);
                if(c!=n-1){
                    steps += ",";
                    // System.out.print(",");
                }
            }
            steps += "}";
//            System.out.print("}");
            steps += "{" + b[r] + "}";
            steps+= "&";
//            steps += "\n";
//            System.out.println(" {"+b[r]+"}");
        }

//        System.out.println();
    }
    // function for rounding results
    public static double Round(double value,int digit) {
        double scale =  Math.pow(10, digit);
        return Math.round(value * scale) / scale;
    }

    // function for scaling the matrix
    public static double[][] scaling(double[][]y){ int n =y.length;
        for(int i=0;i<n;i++){ double temp = Round(y[i][i], digit);
            for(int j=0; j<=n;j++){
                if(y[i][j]!=0){
                    y[i][j]= Round(y[i][j]/temp , digit);}
            }
        }
        return y;
    }

    // function for gauss elimination
    public static double [][] Gauss(double[][] A, double[] b) {
        steps = "";
        result = "";
        int n = b.length;
        int j,i;
        singular(A);
        for ( j = 0; j < n; j++) {
            // find maximum pivot
            int max = j;
            for ( i = j + 1; i < n; i++) {
                if (Math.abs(A[i][j]) > Math.abs(A[max][j])) {
                    max = i;
                }
            }
            //swap row of max pivot with previous row and print the matrix
            if(max!=j){
                double[] temp = A[j]; A[j] = A[max]; A[max] = temp;
                double   t    = b[j]; b[j] = b[max]; b[max] = t;
                Print(A,b,n);}

            singular(A);
            // singularity of matrix// has no solution or has infinite number of solution
         /*   if (Math.abs(A[j][j]) <= EPSILON) {
                throw new ArithmeticException("Matrix is singular");
            }*/

            // Gauss elimination
            for ( i = j + 1; i < n; i++) {
                if(A[i][j]!=0){
                    double alpha = Round(A[i][j] / A[j][j], digit);
                    b[i] =  Round( (b[i]- Round(alpha * b[j], digit)), digit);
                    for (int m = j; m < n; m++) {
                        A[i][m] = Round( (A[i][m] -Round(alpha * A[j][m], digit)), digit);
                    }
                    steps+="forward elimination";
                    Print(A,b,n);}
            }
        }
        // matrix contain A and b
        double [][] y =  new double[n][n+1];
        for(int r=0;r<n;r++){
            for(int c=0;c<n;c++){
                y[r][c]=A[r][c];
                y[r][c+1]=b[r];
            }
        }
        scaling(y);
        return y;
    }
}

















//package LinearSystem;
//import javax.swing.*;
//
//public class GaussianElimination {
//    public  static String steps = "";
//    public  static String result = "";
//    public static final double EPSILON = 1e-7;
//    public  static  int digit = 5;
//
//    // back substitution function :
//    public static String[] backSubstitution( double[][] y){
//        // array of result
//        int n= y.length;double [][]A=new double[n][n];double[]b =new double[n];
//        scaling(y);
//        for(int r=0;r<n;r++){
//            for(int c=0;c<n;c++){
//                A[r][c]=  y[r][c];
//                b[r]= y[r][c+1];
//            }
//        }
//        //print the matrix after scaling
//        steps += "the matrix after scaling";
//        Print(A,b,n);///////////////////////////////
//        // x:matrix of solution //back substitution
//        double[] x = new double[n];
//        for (int i = n - 1; i >= 0; i--) {
//            double sum = 0.0F;
//            //sum of coefficients
//            for (int j = i + 1; j < n; j++) {
//                sum =  Round( (Round(sum,digit) + Round( A[i][j] * x[j], digit)), digit);}
//            x[i] =  Round( (Round((b[i] - sum), digit) / A[i][i]), digit);}
//        for (int i = 0; i < x.length; i++){
//            result = result +  x[i];
//            if(i != x.length - 1)
//                result = result + ",";
//        }
//        String sent[] = new String[2];
//        sent[0] = result;
//        sent[1] = steps;
//        return sent;//////////////res
//    }
//
//
//    public static void singular(double[][]A){ int i,j; int n=A.length;int check=0;
//        for ( i = 0; i< n; i++) {
//            for ( j = 0; j < n; j++) {
//                if(A[i][j]==0){ check++;
//                    if (check==A.length){JOptionPane.showMessageDialog(null, "Error");;}}
//            } check=0;
//        }
//    }
//
//    // print the matrix (step by step)
//    public static void Print(double[][]A,double[]b, int n){
//        if (steps != "")
//        {steps+= "&";}
//        for(int r=0;r<n;r++){
//            steps += "{";
//            // System.out.print("{");
//            for(int c=0;c<n;c++){
//                steps += A[r][c];
//                //System.out.print(A[r][c]);
//                if(c!=n-1){
//                    steps += ",";
//                    // System.out.print(",");
//                }
//            }
//            steps += "}";
////            System.out.print("}");
//            steps += "{" + b[r] + "}";
//            steps+= "&";
////            steps += "\n";
////            System.out.println(" {"+b[r]+"}");
//        }
//
////        System.out.println();
//    }
//    // function for rounding results
//    public static double Round(double value,int digit) {
//        double scale =  Math.pow(10, digit);
//        return Math.round(value * scale) / scale;
//    }
//
//    // function for scaling the matrix
//    public static double[][] scaling(double[][]y){ int n =y.length;
//        for(int i=0;i<n;i++){ double temp = Round(y[i][i], digit);
//            for(int j=0; j<=n;j++){
//                if(y[i][j]!=0){
//                    y[i][j]= Round(y[i][j]/temp , digit);}
//            }
//        }
//        return y;
//    }
//
//    // function for gauss elimination
//    public static double [][] Gauss(double[][] A, double[] b) {
//        int n = b.length;
//        int j,i;
//        singular(A);
//        for ( j = 0; j < n; j++) {
//            // find maximum pivot
//            int max = j;
//            for ( i = j + 1; i < n; i++) {
//                if (Math.abs(A[i][j]) > Math.abs(A[max][j])) {
//                    max = i;
//                }
//            }
//            //swap row of max pivot with previous row and print the matrix
//            if(max!=j){
//                double[] temp = A[j]; A[j] = A[max]; A[max] = temp;
//                double   t    = b[j]; b[j] = b[max]; b[max] = t;
//                Print(A,b,n);}
//
//            singular(A);
//            // singularity of matrix// has no solution or has infinite number of solution
//         /*   if (Math.abs(A[j][j]) <= EPSILON) {
//                throw new ArithmeticException("Matrix is singular");
//            }*/
//
//            // Gauss elimination
//            for ( i = j + 1; i < n; i++) {
//                if(A[i][j]!=0){
//                    double alpha = Round(A[i][j] / A[j][j], digit);
//                    b[i] =  Round( (b[i]- Round(alpha * b[j], digit)), digit);
//                    for (int m = j; m < n; m++) {
//                        A[i][m] = Round( (A[i][m] -Round(alpha * A[j][m], digit)), digit);
//                    }
//                    steps+="forward elimination";
//                    Print(A,b,n);}
//            }
//        }
//        // matrix contain A and b
//        double [][] y =  new double[n][n+1];
//        for(int r=0;r<n;r++){
//            for(int c=0;c<n;c++){
//                y[r][c]=A[r][c];
//                y[r][c+1]=b[r];
//            }
//        }
//        scaling(y);
//        return y;
//    }
//}
//
