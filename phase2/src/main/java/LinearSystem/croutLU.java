package LinearSystem;

public class croutLU extends LU{

    public static String[] crout(double[][] A, double[] b) {
        singular(A);
        int n = A.length;
        double[][] lower = new double[n][n];
        double[][] upper = new double[n][n];
        // Decomposing matrix into Upper and Lower triangular matrix

        // set diagonal of upper matrix to 1
        for (int i = 0; i < n; i++) { upper[i][i] = 1;}
        for (int j = 0; j < n; j++) {
            // lower triangular matrix
            for (int i = j; i < n; i++) {
                double sum = 0;
                for (int k = 0; k < j; k++) {
                    sum =  Round( (sum + Round(lower[i][k] * upper[k][j],digit)), digit);
                }
                lower[i][j] = Round(A[i][j] - sum, digit);
            }
            // upper triangular matrix
            for (int i = j; i < n; i++) {
                double sum = 0;
                for(int k = 0; k < j; k++) {
                    sum = sum + lower[j][k] * upper[k][i];
                }
                upper[j][i] =  Round( (Round ((A[j][i] - sum),digit) / lower[j][j]), digit);
            }
        }
        // print upper and lower matrices
//        System.out.println("lower triangular matrix: ");
        steps += "lower triangular matrix: ";
        printSingle(lower);
//        System.out.println("upper triangular matrix: ");
        steps += "upper triangular matrix: ";
        printSingle(upper);

        double[] d = new double[n];
        for (int i = 0; i < n; i++) {
            double sum = 0.0;
            //sum of coefficients
            for (int j = 0; j < i; j++) { sum +=lower[i][j] * d[j]; }
            d[i] =  Round( (Round ((b[i] - sum),digit) / lower[i][i]), digit);}
        //print matrix d
//        System.out.println("matrix d:");
        steps += "matrix d:";
        for (int i = 0; i < n; i++) {
//            System.out.println(d[i]);
//            steps += d[i];
        }
        singular(upper);
        double [][] y =  new double[n][n+1];
        for(int r=0;r<n;r++){
            for(int c=0;c<n;c++){
                y[r][c]=upper[r][c];
                y[r][c+1]=d[r];
            }
        }
        // matrix of final solution after back substitution
        String[] x = backSubstitution(y);
//        for (int i = 0; i < n; i++) {
//            System.out.println(x[i]);
//        }
        String resl = "";
//            for ( i = 0; i < n; i++) { System.out.println(x[i]); }
//            }
//        for (int w = 0; w < x.length; w++){
//            resl = resl +  x[w];
//            if(w != x.length - 1){
//                resl = resl + ",";}
//        }
        String[] result = new String[2];
        result[0] = x[0];
        result[1] = steps;
        return result;
    }


}