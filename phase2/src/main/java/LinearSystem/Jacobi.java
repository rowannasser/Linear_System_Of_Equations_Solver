package LinearSystem;

import java.io.IOException;
import java.util.Arrays;

public class Jacobi extends GaussianElimination{

    ///function for link all function to perform jacobi
    public static String[] jacobi(double [][]A, double []b,double []initial,double abs, int maxiterations) {
        int n = A.length;
        double [][] y =  new double[n][n+1];
        for(int r=0;r<n;r++){
            for(int c=0;c<n;c++){
                y[r][c]=A[r][c];
                y[r][c+1]=b[r];
            }
        }
        if (!checkDominant(y)){
//            System.out.println("The system isn't diagonally dominant. so the solution will not converge.");
            steps += "The system isn't diagonally dominant. so the solution will not converge.&";
            maxiterations=10;
        }
        String[] result = solve(y,initial,maxiterations);
        return result;
    }

    public static boolean Dominant(double[][]y,int r, boolean[] V, int[] R){
        int n = y.length;
        if (r == y.length) {
            double[][] dominant = new double[n][n+1];
            for (int i = 0; i < R.length; i++) {
                for (int j = 0; j < n + 1; j++)
                    dominant[i][j] =y[R[i]][j];
            }
            y = dominant;
            return true;
        }

        for (int i = 0; i < n; i++) {
            if (V[i]) continue;
            double sum = 0;
            for (int j = 0; j < n; j++)
                sum =Round(sum +Math.abs(y[i][j]),digit);

            if (2 * Math.abs(y[i][r]) > sum) { // diagonally dominant
                V[i] = true;
                R[r] = i;
                if (Dominant(y,r + 1, V, R))
                    return true;
                V[i] = false;
            }
        }
        return false;
    }

    // function to check dominant
    public static boolean checkDominant(double[][]A)
    {
        boolean[] visited = new boolean[A.length];
        int[] rows = new int[A.length];
        Arrays.fill(visited, false);
        return Dominant(A,0, visited, rows);
    }


    public static String[] solve(double[][]y,double[] initial, int maxiterations)
    {
        int iterations = 0;
        int n = y.length;
        double epsilon = 1e-15;
        double[] x = new double[n]; // Approximations
        double[] previous = new double[n];
        previous=initial; // Prev
        Arrays.fill(x, 0);
        while (true) {
            for (int i = 0; i < n; i++) {
                double sum = Round(y[i][n], digit);
                for (int j = 0; j < n; j++)
                    if (j != i)
                        sum = Round(sum-y[i][j] * previous[j], digit);

                x[i] =Round( 1/y[i][i] * sum, digit);
            }

            // print solution of iteration:
//            System.out.print("iteration " + iterations + " = {");
            steps += "iteration " + iterations + " = {";
            for (int i = 0; i < n; i++)
                steps += x[i] + " ";
//                System.out.print(x[i] + " ");
//            System.out.println("}");
            steps += "}&";

            iterations++;
            if (iterations == 1) continue;

            boolean stop = true;
            for (int i = 0; i < n && stop; i++)
                if (Math.abs(x[i] - previous[i]) > epsilon)
                    stop = false;

            if (stop || iterations == maxiterations) break;
            previous = (double[])x.clone();
        }
        //print final solution:
//        System.out.println("final solution is : ");
//        for(int k=0;k<n;k++){
//            System.out.print(x[k]+"  ");
//        }

        String rr="";
        for (int i = 0; i < n; i++){
            rr = rr +  x[i];
            if(i != n - 1)
                rr = rr + ",";
        }
        String res[] = new String[2];
//        System.out.println(rr);
        res[0] = rr;
        res[1] = steps;
        return res;
    }
}