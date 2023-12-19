package NonLinearSystem;

import com.example.phase2.Phase2Application;

import java.util.Random;

public class BisectionMethod {

    public static double Round(double value,int digit) {
        double scale =  Math.pow(10, digit);
        return Math.round(value * scale) / scale;
    }
    public String[] rootByBisection(String function,double xl,double xu,double es,int imax, int sign) throws Exception {
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
if(sign == -1){
        String steps = "";
        Phase2Application evaluator = new Phase2Application();
        //Method can't be applied "between the two points there is no root"
        if((evaluator.evaluateFunction(function,xl)*evaluator.evaluateFunction(function,xu)) > 0){
            throw new Exception("No bracket");
        }
        double xr = (xu+xl)/2;
        double ea;
        //compute the iterative method until you get the answer or reached to max number of iterations
        for (int i = 0; i < imax; i++){
            steps += "iteration" + i + "&";
            xr = (xu+xl)/2;
            steps +="xr = " + "(" + xr + "+" + xl + ")" + "/2 &";
            ea = Math.abs((xu-xl)/xl);
            steps += "ea = " + "|(" + xu + "-" + xl + ")"+"/" + xl+ "|&";
            //to choose the correct half to compute the method across
            double test = (evaluator.evaluateFunction(function,xu)*evaluator.evaluateFunction(function,xr));
            steps += "f(xr) = " + test + "&";
            if (test < 0){
                xl = xr;
                steps+= "xl = xr = " +xr + "&";
            }
            else if(test > 0){
                xu = xr;
                steps+= "xu = xr = " +xr + "&";
            }
            else {
                ea = 0;
                break;
            }
            if (ea < es){

                break;
            }
        }
     end = System.currentTimeMillis();
    long elapsedTime = end - start;
        String res = "X = " + xr;
        String[] result = new String[3];
        result[0]  =res;
        result[1] = steps;
        result[2] = "R.T = "+ elapsedTime;
        return result;
    }
    else {
    String steps = "";
    Phase2Application evaluator = new Phase2Application();
    //Method can't be applied "between the two points there is no root"
    if((evaluator.evaluateFunction(function,xl)*evaluator.evaluateFunction(function,xu)) > 0){
        throw new Exception("No bracket");
    }
    double xr = (xu+xl)/2;
//    double xr;
    double ea;
    //compute the iterative method until you get the answer or reached to max number of iterations
    for (int i = 0; i < imax; i++){
        steps += "iteration" + i + "&";
        xr = (xu+xl)/2;
        xr = Round(xr, sign);
        steps +="xr = " + "(" + xr + "+" + xl + ")" + "/2 &";
        ea = Math.abs((xu-xl)/xl);
//        ea = Round(ea, sign);
        steps += "ea = " + "|(" + xu + "-" + xl + ")"+"/" + xl+ "|&";
        //to choose the correct half to compute the method across
        double test = (evaluator.evaluateFunction(function,xu)*evaluator.evaluateFunction(function,xr));
        steps += "f(xr) = " + test + "&";
        if (test < 0){
            xl = xr;
            steps+= "xl = xr = " +xr + "&";
        }
        else if(test > 0){
            xu = xr;
            steps+= "xu = xr = " +xr + "&";
        }
        else {
            ea = 0;
            break;
        }
        if (ea < es){

            break;
        }
    }
     end = System.currentTimeMillis();
    long elapsedTime = end - start;
    String res = "X = " + xr;
    String[] result = new String[3];
    result[0]  =res;
    result[1] = steps;
    result[2] = "R.T = "+ elapsedTime;
    return result;

    }
    }

}