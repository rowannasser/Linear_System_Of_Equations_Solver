package NonLinearSystem;

import com.example.phase2.Phase2Application;

import java.util.Random;

public class falsePosition {

    public static double Round(double value,int digit) {
        double scale =  Math.pow(10, digit);
        return Math.round(value * scale) / scale;
    }
    public String[] rootByFalsePosition(String equation,double xl,double xu,double es,int imax, int sign) throws Exception {
        //Method can't be applied "between the two points there is no root"
        double runtime = System.currentTimeMillis();
        if(sign == -1){
        String steps = "";

        Phase2Application evaluator = new Phase2Application();
//        if(xl == 0 && xu == 0){
//            Random rand = new Random();
////            int upper = (int) x+10;
//            xu = rand.nextInt();
//            xl = rand.nextInt();
//            while (((evaluator.evaluateFunction(equation,xl)*evaluator.evaluateFunction(equation,xu)) > 0)){
//                xu = rand.nextInt();
//                xl = rand.nextInt((int) xu+1);
//            }
//            steps += "xl = "+ xl + "&" + "xu = " + xu + "&";
//        }
        if((evaluator.evaluateFunction(equation,xl)*evaluator.evaluateFunction(equation,xu)) > 0){
            throw new Exception("No bracket");
        }
//        double xr = (xl*function(xu))-(xu*function(xl))/(function(xu)-function(xl));
        double xr = 0;
        double ea;
        //compute the iterative method until you get the answer or reached to max number of iterations
        for (int i = 0; i < imax; i++){

            xr = ((xl*evaluator.evaluateFunction(equation,xu))-(xu*evaluator.evaluateFunction(equation,xl)))/
                    (evaluator.evaluateFunction(equation,xu)-evaluator.evaluateFunction(equation,xl));
            steps += "xr = " + xr + "&";
            ea = Math.abs((xu-xl)/xl);
            steps += "ea = |" + "(" + xu + " - " + xl + ") / " + xl + "|&";
            //to choose the correct half to compute the method across
            double test = evaluator.evaluateFunction(equation,xr);
            steps += "f(xr) =  " + test + "&";
            if (test < 0){
                xl = xr;
                steps+= "xl = xr = " +xr + "&";
            }
            else if(test > 0){
                xu = xr;
                steps+= "xu = xr = " +xu + "&";
            }
            else {
                ea = 0;
                break;
            }
            if (ea < es){
                break;
            }
        }
        String res = "X = " + xr;
        String[] result = new String[3];
        result[0]  =res;
        result[1] = steps;
        runtime = System.currentTimeMillis() - runtime;
        result[2] = "R.T = " + runtime;
        return result;
    }
    else{
            String steps = "";
            Phase2Application evaluator = new Phase2Application();
//            if(xl == 0 && xu == 0){
//                Random rand = new Random();
////            int upper = (int) x+10;
//                xu = rand.nextInt();
//                xl = rand.nextInt();
//                while (((evaluator.evaluateFunction(equation,xl)*evaluator.evaluateFunction(equation,xu)) > 0)){
//                    xu = rand.nextInt();
//                    xl = rand.nextInt((int) xu+1);
//                }
//                steps += "xl = "+ xl + "&" + "xu = " + xu + "&";
//            }
            if((evaluator.evaluateFunction(equation,xl)*evaluator.evaluateFunction(equation,xu)) > 0){
                throw new Exception("No bracket");
            }
//        double xr = (xl*function(xu))-(xu*function(xl))/(function(xu)-function(xl));
            double xr = 0;
            double ea;
            //compute the iterative method until you get the answer or reached to max number of iterations
            for (int i = 0; i < imax; i++){

                xr = ((xl*evaluator.evaluateFunction(equation,xu))-(xu*evaluator.evaluateFunction(equation,xl)))/
                        (evaluator.evaluateFunction(equation,xu)-evaluator.evaluateFunction(equation,xl));
                xr = Round(xr, sign);
                steps += "xr = " + xr + "&";
                ea = Math.abs((xu-xl)/xl);
//                ea = Round(ea,sign);
                steps += "ea = |" + "(" + xu + " - " + xl + ") / " + xl + "|&";
                //to choose the correct half to compute the method across
                double test = evaluator.evaluateFunction(equation,xr);
                steps += "f(xr) =  " + test + "&";
                if (test < 0){
                    xl = xr;
                    steps+= "xl = xr = " +xr + "&";
                }
                else if(test > 0){
                    xu = xr;
                    steps+= "xu = xr = " +xu + "&";
                }
                else {
                    ea = 0;
                    break;
                }
                if (ea < es){
                    break;
                }
            }
            String res = "X = " + xr;
            String[] result = new String[3];
            result[0]  =res;
            result[1] = steps;
            runtime = System.currentTimeMillis() - runtime;
            result[2] = "R.T = " + runtime;
            return result;
    }

    }


}
