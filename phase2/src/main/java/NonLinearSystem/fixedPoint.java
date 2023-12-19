package NonLinearSystem;

import com.example.phase2.Phase2Application;
import java.util.Scanner;

public class fixedPoint {
    public static double Round(double value,int digit) {
        double scale =  Math.pow(10, digit);
        return Math.round(value * scale) / scale;
    }

    public static String[] Fixedpt(String eqn, double x0, double es, int imax, int sign){
        long start = System.currentTimeMillis();
        if(sign == -1){
        Phase2Application evaluator = new Phase2Application();
        double xr = x0;
        double xr_old;
        double ea = 1; /////???????
        String gx = eqn + "+x";
        String steps = gx + "&";
        int i = 0;
        do {
            steps = steps+"iteration" + i + "&";
            xr_old = xr;
            steps += "Xi = " + xr_old + "&";
            xr = evaluator.evaluateFunction(gx,xr_old);
            steps += "Xi+1 = " + xr + "&";
            if (xr != 0){
                ea = Math.abs((xr-xr_old)/xr);
                steps += "ea = |(" + xr +" - " + xr_old + "/)" + xr + "|&";
                i++;
            }
        }while (ea > es && i < imax);
        String res = "X = " + xr;
        System.out.println(xr);

            String[] result = new String[3];
        result[0]  =res;
        result[1] = steps;

            long end = System.currentTimeMillis();
          //  end = System.currentTimeMillis();
            long elapsedTime = end - start;
        result[2] = "R.T = " + elapsedTime;
        return result;
    }
    else {
            Phase2Application evaluator = new Phase2Application();
            double xr = x0;
            double xr_old;
            double ea = 1; /////???????
            String gx = eqn + "+x";
            String steps = gx + "&";
            int i = 0;
            do {
                steps += "iteration" + i + "&";
                xr_old = xr;
                steps += "Xi = " + xr_old + "&";
                xr = evaluator.evaluateFunction(gx,xr_old);
                xr = Round(xr,sign);
                steps += "Xi+1 = " + xr + "&";
                if (xr != 0){
                    ea = Math.abs((xr-xr_old)/xr);
                    steps += "ea = |(" + xr +" - " + xr_old + "/)" + xr + "|&";
                    i++;
                }
            }while (ea > es && i < imax);

            System.out.println(i);
            String res = "X = " + xr;
            System.out.println(xr);
            String[] result = new String[3];
            result[0]  =res;
            result[1] = steps;

            long end = System.currentTimeMillis();
           // end = System.currentTimeMillis();
            long elapsedTime = end - start;
            result[2] = "R.T = " + elapsedTime;
            return result;
        }
    }

    public static void main(String[] args){
        String temp;
        System.out.println("Enter the function: ");
        Scanner input = new Scanner(System.in);
        temp = input.nextLine();
        System.out.println("Enter initial value for x : ");
       // double y = Double.parseDouble(input.next());

        Fixedpt(temp,-3,.0001,50,5);

    }
}
