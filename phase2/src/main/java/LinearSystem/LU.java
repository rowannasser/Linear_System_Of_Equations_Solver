package LinearSystem;
public class LU extends GaussianElimination{

    // function for Doolittle decomposition
    public static String[] Doolittle(double[][] A, double[] b) {

        int n = A.length;
        double[][] lower = new double[n][n];
        double[][] upper = new double[n][n];
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
                steps+="after pivoting";
                Print(A,b,n);
            }
        }


        // Decomposing matrix into Upper and Lower matrices

        for ( i = 0; i < n; i++) {
            // upper triangular matrix
            for (int k = i; k < n; k++) {
                // sum of lower[i,j] * upper[j,k]
                double sum = 0;
                for ( j = 0; j < i; j++) {  sum = Round( (sum + Round((lower[i][j] * upper[j][k]),digit)), digit);}
                // set value of upper[i,k]
                upper[i][k] = Round((A[i][k] - sum), digit);
            }

            // lower triangular matrix
            for (int k = i; k < n; k++) {
                // set diagonal to 1
                if (i == k){ lower[i][i] = 1; }
                else {
                    // sum of lower[k,j] * upper[j,i]
                    double sum = 0;
                    for ( j = 0; j < i; j++){ sum =  Round( (sum + Round((lower[k][j] * upper[j][i]), digit)), digit); }
                    // set value of lower[k,i]
                    lower[k][i] =  Round( (Round(A[k][i] - sum, digit) / upper[i][i]), digit);
                }
            }

        }
        // print upper and lower matrices
//            System.out.println("lower triangular matrix: ");
        steps += "lower triangular matrix: ";
        printSingle(lower);
//            System.out.println("upper triangular matrix: ");
        steps += "upper triangular matrix:";
        printSingle(upper);

        double[] d = new double[n];
        for ( i = 0; i < n; i++) {
            double sum = 0.0F;
            //sum of coefficients of lower matrix : lower[i][j]*d[j]
            for (j = 0; j < i; j++) { sum = sum + lower[i][j] * d[j]; }
            //substitution to know the value of d[i]
            d[i] = Round( (Round(b[i] - sum, digit) / lower[i][i]),digit);}
        //print matrix d
//            System.out.println("");
        steps += "matrix d:";
        for ( i = 0; i < n; i++) {
            // steps += d[i];
//                System.out.println(d[i]);
        }
        singular(upper);
        // matrix contain upper and d matrices
        double [][] y =  new double[n][n+1];
        for(int r=0;r<n;r++){
            for(int c=0;c<n;c++){
                y[r][c]=upper[r][c];
                y[r][c+1]=d[r];
            }
        }
        //back substitution to know the final result
        String resl = "";
        String[] x = backSubstitution(y);
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


    //function to print one matrix only
    public static void printSingle(double [][]A){
        int n = A.length;
        if (steps != ""){
            steps+= "&";}
        for(int r=0;r<n;r++){
            steps += "{";
//            System.out.print("{");
            for(int c=0;c<n;c++){
                steps += A[r][c];
//                System.out.print(A[r][c]);
                if(c!=n-1){
                    steps += ",";
//                    System.out.print(",");
                }
            }
            steps += "}";
            steps += "&";
//            System.out.println("}");
        }
//        System.out.println();
    }
    // function to print 2 matrices (matrix of coefficients and matrix of solution

    public static void Print(double[][]A,double[]b, int n){
        if (steps != "")
        {
            steps+= "&";
        }
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
//            steps += "\n";
            steps+="&";
//            System.out.println(" {"+b[r]+"}");
        }

//        System.out.println();
    }
}



