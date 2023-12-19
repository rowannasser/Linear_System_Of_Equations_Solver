package LinearSystem;

public class gaussSeidel extends GaussianElimination {


    public static String[] gauss_seidel(double[][]A,double[]b, double eps, int maxiterations,double[] initial) {

        int n = A.length;  int iterations = 0;
        // convert matrix of coefficient and result to aug matrix
        double[][] y = new double[n][n + 1];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                y[r][c] = A[r][c];
                y[r][c + 1] = b[r];
            }
        }
        // matrix of previous results
        double[] previous = new double[n];
        // initial gauss (zero matrix)
        for(int i=0;i<n;i++){previous[i] = initial[i];}
        // matrix of current results
        double[] current = new double[n]; double error;

        while (iterations != maxiterations) {
            iterations++;

            for (int i = 0; i < n; i++) {
                current[i] = y[i][n];

                for (int j = 0; j < n; j++) {
                    // case of values of coefficients
                    if (i > j) { current[i] = Round(current[i] - Round(y[i][j] * current[j] , digit), digit);}
                    // case of values of results
                    if (i < j) { current[i] = Round(current[i]- Round(y[i][j] * previous[j], digit), digit);}
                }
                //divide on y[i][i] value of result
                current[i] =  Round(current[i] / y[i][i], digit);
            }

            error = 0;

            for (double[] Matrix : y ) {
                double Error = 0;
                for (int j = 0; j < n; j++) {
                    Error=   Round(Error +Matrix[j] * current[j], digit);
                }
                error = Round(error + Math.abs(Error - Matrix[n]), digit);
            }
            if (error < eps) {break;}
            previous = current;

            for(int i=0; i<n; i++){
                steps += previous[i]+ "  ";
            }
//            System.out.print("\n");
            steps += "&";
        }

        steps +="& number of iterations: ";
        steps += iterations;
//        System.out.print("\n number of iterations: ");
//        System.out.println(iterations);
//        System.out.println("final solution is :");

        String rr="";
        for (int i = 0; i < n; i++){
            rr = rr +  previous[i];
            if(i != n - 1)
                rr = rr + ",";
        }
        String res[] = new String[2];
        System.out.println(rr);
        res[0] = rr;
        res[1] = steps;
        return res;
    }

}