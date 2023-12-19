package LinearSystem;

public class GaussJordanElimination extends GaussianElimination {

    //gauss Jordan elimination
    public static String[] jordan(double[][]A,double[]b){

        double [][]y = Gauss(A,b);
        int n= y.length;

        // convert matrix y to A and b
        for(int r=0;r<n;r++){
            for(int c=0;c<n;c++){
                A[r][c]=  y[r][c];
                b[r]= y[r][c+1];
            }
        }

        // more steps for elimination(gauss jordan elimination)
        for ( int j = n-1; j >0; j--) {
            for ( int i = 0; i < n-1; i++) {
                if(A[i][j] != 0 && i != j){
                    double alpha =  Round(A[i][j] / A[j][j], digit);
                    b[i] =  Round( (b[i] - Round(alpha * b[j], digit)), digit);
                    A[i][j] =  Round( (A[i][j] - Round(alpha * A[j][j], digit)), digit);
                    steps+= "back elimination";
                    Print(A,b,n);}
            }
        }
        singular(A);

        // return matrix of final solution
        String res = "";
        for (int i = 0; i < b.length; i++){
            res = res +  b[i];
            if(i != b.length - 1)
                res = res + ",";
        }
        String[] result = new String[2];
        result[0] = res;
        result[1] = steps;
        return result;
    }


}