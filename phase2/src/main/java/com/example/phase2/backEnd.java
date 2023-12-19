package com.example.phase2;

import LinearSystem.*;
import NonLinearSystem.*;

import javax.swing.*;

import java.util.Locale;

import static com.example.phase2.GUI.isNumeric;

public class backEnd {


    static String[][] prepareEqn(String[] eq, int num){// method used to prepare equation by spliting right and left hand sides for each equation iterate over it
        String[][] eqns = new String[num][]; // OK
        for(int i = 0; i < eq.length; i++){
            eqns[i] = eq[i].split("=");
        }
        return eqns;
    }

    String[] solveLinear(String method, String[] param, String equations){

        String eqns = equations;
        eqns = eqns.replace("*", "");
        String[] s = eqns.split(",");
        int noeqn = s.length;
        String eqnss[][] = prepareEqn(s,noeqn);
        int num = noeqn;
        String[][] arr = eqnss;
        double[] b = new double[num]; // array to store free terms which are on the right hand side
        for (int i = 0; i < num; i++){
            b[i] = 0; //set default value for all = zero
        }
        double[][] a = new double[num][num]; // array to store coefficients of variables
        for (int i = 0; i < num; i++){
            for (int j = 0; j < num; j++){
                a[i][j] = 0;//set default value for coeffocients to be zero *when we don't add coeff which means no var found so the coeff of that var will be zero
            }
        }
        String[] x = new String[num]; //array of string to store variables
        String coef = ""; // string to store coeff from the equation and it is used as parameter to transform the string to int and add it to the array
        String var = "";// used when extracting varibles from the equation and add it to the array

        for(int i = 0; i < num; i++){ // that for loop is to iterate first over equations
            if(arr[i].length == 2 && isNumeric(arr[i][1])){ //if condition to check that the equation is right and has right and left hand sides and make sure that the free term is on the right hand side
                arr[i][0] = arr[i][0].replace("-", "+-");//first replace every - with +- which enables us to spit terms without losing the "-"
                arr[i][0] = arr[i][0].replace(" ", "");
                //split equations
                String[] tt = arr[i][0].split("\\+");// spliting terms by + and store each equation in the array tt so each term be accessed in the terms of that array
                //each eqn is on tt
                //   if(tt.length == num){
                if(tt[0].equals("")){// if the eqn started with - it will cause a problem when spliting that the first term will be "" so to avoid any error we will check that condition and start iterations from the next element
                    for(int j = 1; j < tt.length; j++){// for loop iterate over the terms of the equation stored in tt
                        for(int k = 0; k < tt[j].length(); k++){// for loop to iterate over the elements of each term
                            if(Character.isDigit(tt[j].charAt(k)) || tt[j].charAt(k) == '-' || tt[j].charAt(k) == '.'){//that condtion is to check if the element is a digit or - or . to be stored as coeff
                                coef += tt[j].charAt(k);//storing coeffs
                            }
                            else{
                                var += tt[j].substring(k);//reaching the else meaning that we reach to the variable then we consider the rest of the term as varible
                                break;//then break the loop, no need to check any more as we get the variable and the term ended
                            }//end of if condition
                        }//end the for loop that iterate over each term

                        // /           *>>>>>> then we should add what we had get from the term to the arrays<<<<<*    //


                        if((coef.equals("-") || coef.equals("")) ){//due to that form "x" or "-x" as the 1 coeff is not written beside the var we make that condition to check and add coeff = 1
                            for (int q = 0; q < num; q++){// iterate over the variables to compare and check if it exists or not
                                try{//used to try the following code
                                    if(var.equals( x[q])) {//if the variable exists in the the array of variables
                                        if(coef.equals("-"))//then we will add the coeff in the same index of that variable but in the coeff matrix
                                        {  a[i][q] += -1;}//incase that there is a - beside the var which means -1
                                        else if(coef.equals("")){
                                            a[i][q] += 1;
                                        }
                                        break;//break after finding the variable and adding the coeff to the matrix
                                    }
                                    else if (x[q] == null){//if we do not find the varible in the array of variables so it is a new one we check for empty place so we can add it
                                        if(coef.equals("-"))//storing coeff
                                        {  a[i][q] += -1;}
                                        else if(coef.equals("")){
                                            a[i][q] += 1;
                                        }
                                        x[q] = var;//storing the new variable in the array of variables
                                        break;//break after adding the variable and adding the coeff to the matrix
                                    }
                                    else if(x[q].equals(x[num - 1])) {// if it reaches to the end of the var array without finding the variable in the array
//                                        System.out.println("Error");
                                        JOptionPane.showMessageDialog(null, "Error");
                                        return new String[]{"null","null"};
                                    }

                                }

                                catch (Exception e){//to catch if the number of variables greater than the number of solutions
//                                    System.out.println("Error");
                                    JOptionPane.showMessageDialog(null, "Error");
                                    return new String[]{"null","null"};
                                }

                            }}
                        else if(!var.equals("")){//in case that there is actually a variable in the term
                            for (int q = 0; q < num; q++){
                                try {
                                    if(var.equals( x[q])) {
                                        a[i][q] = Double.parseDouble(coef);//then we can add its coeff to the array *after converting it to digits
                                        break;//end that loop
                                    }
                                    else if (x[q] == null){
                                        a[i][q] = Double.parseDouble(coef);//then we can add its coeff to the array *after converting it to digits
                                        x[q] = var;// adding that new var. to the array of variables
                                        break;//end that loop
                                    }
                                    else if(x[q].equals(x[num - 1])) {
//                                        System.out.println("Error");
                                        JOptionPane.showMessageDialog(null, "Error");
                                        return new String[]{"null","null"};
                                    }
                                }

                                catch (Exception e){
//                                    System.out.println("Error");
                                    JOptionPane.showMessageDialog(null, "Error");
                                    return new String[]{"null","null"};
                                }
                            }//end for loop
                        }
                        else {//if there is a variable beside the coeff so it is not coeff and it is a free term
                            if(coef.charAt(0) == '-')//check if it is a negative number
                            {    coef = coef.replace("-","");// then we convert its sign by just removing the - sign
                                b[i] += Double.parseDouble(coef);//then converting the string to double and add the value the vector of b
                            }
                            else {// else then it is a positive number
                                coef = "-" + coef;//we convert its sign by adding - to it
                                b[i] += Double.parseDouble(coef);//then converting the string to double and add the value the vector of b
                                // we add that term to the free term of that eqn
                            }
                        }
                        coef = "";// make the string that store coeffs empty again to restore coeff or the next term

                        var = "";//reset string var to store the next term
                    }//end of for loop that is concerned with each terem
                }
                else {
                    for(int j = 0; j < tt.length; j++){// for loop iterate over the terms of the equation stored in tt
                        for(int k = 0; k < tt[j].length(); k++){// for loop to iterate over the elements of each term
                            if(Character.isDigit(tt[j].charAt(k)) || tt[j].charAt(k) == '-' || tt[j].charAt(k) == '.'){//that condtion is to check if the element is a digit or - or . to be stored as coeff
                                coef += tt[j].charAt(k);//storing coeffs
                            }
                            else{
                                var += tt[j].substring(k);//reaching the else meaning that we reach to the variable then we consider the rest of the term as varible
                                break;//then break the loop, no need to check any more as we get the variable and the term ended
                            }//end of if condition
                        }//end the for loop that iterate over each term

                        // /           *>>>>>> then we should add what we had get from the term to the arrays<<<<<*    //



                        if((coef.equals("-") || coef.equals("")) ){//due to that form "x" or "-x" as the 1 coeff is not written beside the var we make that condition to check and add coeff = 1
                            for (int q = 0; q < num; q++){
                                try {  if(var.equals( x[q])) {
                                    if(coef.equals("-"))
                                    {  a[i][q] += -1;}
                                    else if(coef.equals("")){
                                        a[i][q] += 1;
                                    }
                                    break;
                                }
                                else if (x[q] == null){
                                    if(coef.equals("-"))
                                    {  a[i][q] += -1;}
                                    else if(coef.equals("")){
                                        a[i][q] += 1;
                                    }
                                    x[q] = var;
                                    break;
                                }
                                else if(q == num-1) {
//                                    System.out.println("Error");
                                    JOptionPane.showMessageDialog(null, "Error");
                                    return new String[]{"null","null"};
                                }
                                }
                                catch (Exception e){
//                                    System.out.println("Error");
                                    JOptionPane.showMessageDialog(null, "Error");
                                    return new String[]{"null","null"};
                                }
                            }
                        }
                        else if(!var.equals("")){//in case that there is actually a variable in the term
                            for (int q = 0; q < num; q++){
                                try{ if(var.equals( x[q])) {
                                    a[i][q] = Double.parseDouble(coef);//then we can add its coeff to the array *after converting it to digits
                                    break;
                                }
                                else if (x[q] == null){
                                    a[i][q] = Double.parseDouble(coef);//then we can add its coeff to the array *after converting it to digits
                                    x[q] = var;
                                    break;
                                }
                                else if(q == num- 1) {
//                                    System.out.println("Error");
                                    JOptionPane.showMessageDialog(null, "Error");
                                    return new String[]{"null","null"};
                                }
                                }
                                catch (Exception e){
//                                    System.out.println("Error");
                                    JOptionPane.showMessageDialog(null, "Error");
                                    return new String[]{"null","null"};
                                }}
                        }
                        else {//if there is a variable beside the coeff so it is not coeff and it is a free term
                            if(coef.charAt(0) == '-')//check if it is a negative number
                            {    coef = coef.replace("-","");// then we convert its sign by just removing the - sign
                                b[i] += Double.parseDouble(coef);//then converting the string to double and add the value the vector of b
                            }
                            else {// else then it is a positive number
                                coef = "-" + coef;//we convert its sign by adding - to it
                                b[i] += Double.parseDouble(coef);//then converting the string to double and add the value the vector of b
                                // we add that term to the free term of that eqn
                            }
                            // we add that term to the free term of that eqn
                        }
                        coef = "";// make the string that store coeffs empty again to restore coeff or the next term

                        var = "";//reset string var to store the next term
                    }//end of for loop that is concerned with each terem
                }
                b[i]+= Double.parseDouble(arr[i][1]);//after iterate over the left hand side of the eqn we take the numeric value of the right hand side and add it to the vector of free variables
            }
            else if(arr[i].length == 2 && !(isNumeric(arr[i][1]))){//if the right hand side is not numeric so it contains variables
                if(!(arr[i][1].contains("+") && ! (arr[i][1].charAt(0) == '+')|| (arr[i][1].contains("-")&& ! (arr[i][1].charAt(0) == '-')))){//check if the right hand side is only one term so that we can move it to the other side easily
                    if(arr[i][1].charAt(0) == '-')//if its sign is - then we convert it to + in the other side
                    {
                        arr[i][1] = arr[i][1].replace("-","");//remove the - sign
                        arr[i][0] = arr[i][0] + "+"  +arr[i][1];//adding + to the term and add it to the left hand side
                    }
                    else if(arr[i][1].charAt(0) == '+'){//if the sign is -
                        arr[i][1].replaceFirst("\\+", "-");//replace the + with - sign
                        arr[i][0] = arr[i][0]  +arr[i][1];// add the term to the left hand side
                    }
                    else {//else means it contains neither - or + which means it is positive
                        arr[i][0] = arr[i][0] + "-"  +arr[i][1];//so we add - sign to it and add the term to the left hand side
                    }
                }
                else {//else if there are more than one term in the right side
                    //so we need to convert each + with - and each - with + before adding it to the left side
                    arr[i][1] = arr[i][1].replaceAll("\\+", "*");
                    arr[i][1] = arr[i][1].replaceAll("-", "+");
                    arr[i][1] = arr[i][1].replaceAll("\\*", "-");
                    if(arr[i][1].charAt(0) =='+' || arr[i][1].charAt(0) == '-'){
                        arr[i][0] += arr[i][1];
                    }
                    else {
                        arr[i][0] = arr[i][0]+ "-" + arr[i][1];
                    }
                }arr[i][1] = "0";//then the right side will be zero
                i--;//reiterate over the eqn
            }
            else{//it is error when there is no right hand side
//                System.out.println("Error");
                JOptionPane.showMessageDialog(null, "Error");
                return new String[]{"null","null"};
            }
        }
        int precision = 5;
        String[] res = new String[2];
        System.out.println(method);
        System.out.println(param[0]);
        System.out.println();
        switch (method){
            case "Gauss Elimination":
                if((param.length == 1) && isNumeric(param[0])){
                    precision = Integer.parseInt(param[0]);
                    GaussianElimination.digit = precision;
                }
                double[][] y = GaussianElimination.Gauss(a, b);
                res = GaussianElimination.backSubstitution(y);
                break;

            case "Gauss-Jordan":
                if((param.length == 1) && isNumeric(param[0])){
                    precision = Integer.parseInt(param[0]);
                }
                res = GaussJordanElimination.jordan(a,b);
                break;

            case "LinearSystem.LU Decomposition":
                if((param.length == 2) && isNumeric(param[1])){
                    precision = Integer.parseInt(param[1]);
                }
                if(param[0].equals("Downlittle Form")){
                    res = LU.Doolittle(a,b);
                }
                else if(param[0].equals("Crout Form")){
                    res = croutLU.crout(a,b);
                }
                break;

            case "Gauss-Seidil":
                if(param.length == 4){
                    System.out.println("1");
                    System.out.println(param[0]);
                    System.out.println(param[1]);
                    System.out.println(param[2]);
                    System.out.println(param[3]);
                    if(isNumeric(param[3])){
                        System.out.println("2");
                        precision = Integer.parseInt(param[3]);
                        gaussSeidel.digit = Integer.parseInt(param[3]);
                        int iterations = Integer.parseInt(param[1]);
                        double eps = Double.parseDouble(param[2]);
                        param[0].replaceAll("\\[", "");
                        param[0].replaceAll("\\]", "");
                        String[] ini = param[0].split(",");
                        double[] initial = {0};
                        for(int i = 0; i < ini.length;i++){
                            initial[i] = Double.parseDouble(ini[i]);
                        }
                        System.out.println("c");
                        System.out.println(a[0][0]);
                        System.out.println("b");
                        System.out.println(b[0]);
                        System.out.println("d");
                        System.out.println(eps);
                        System.out.println(iterations);
                        System.out.println(initial[0]);
                        res = gaussSeidel.gauss_seidel(a,b,eps,iterations,initial);
//
//                        GaussSeidil t = new  GaussSeidil(a,b,initial,iterations,eps,precision);
//                            res = t.solve();
//                        System.out.println(res[0]);
                    }else {
                        System.out.println("2");
//                        precision = Integer.parseInt(param[3]);
//                        gaussSeidel.digit = Integer.parseInt(param[3]);
                        int iterations = Integer.parseInt(param[1]);
                        double eps = Double.parseDouble(param[2]);
                        param[0].replaceAll("\\[", "");
                        param[0].replaceAll("\\]", "");
                        String[] ini = param[0].split(",");
                        double[] initial = new double[ini.length];
//                        for (int i = 0; i < ini.length; i++){
//
//                        }
                        for(int i = 0; i < ini.length;i++){
                            initial[i] = Double.parseDouble(ini[i]);
                        }
                        res = gaussSeidel.gauss_seidel(a,b,eps,iterations,initial);
//
//                        GaussSeidil t = new  GaussSeidil(a,b,initial,iterations,eps,precision);
//                            res = t.solve();
//                        System.out.println(res[0]);
                    }
                }
                break;
            case "Jacobi-Iteration":
                //                if(param.length == 4){
//                    if(isNumeric(param[3])){
////                        gaussSeidel.digit = Integer.parseInt(param[3]);
//                        int iterations = Integer.parseInt(param[1]);
//                        double eps = Double.parseDouble(param[2]);
//                        param[0].replaceAll("\\[", "");
//                        param[0].replaceAll("\\]", "");
//                        String[] ini = param[0].split(",");
//                        double[] initial = {0};
//                        for(int i = 0; i < ini.length;i++){
//                            initial[i] = Double.parseDouble(ini[i]);
//                        }
//                        res = gaussSeidel.gauss_seidel(a,b,eps,iterations,initial);
//                    }
//                }
                if(param.length == 4){
                    if(isNumeric(param[3])){
                        precision = Integer.parseInt(param[3]);
                        gaussSeidel.digit = Integer.parseInt(param[3]);
                        int iterations = Integer.parseInt(param[1]);
                        double eps = Double.parseDouble(param[2]);
                        param[0].replaceAll("\\[", "");
                        param[0].replaceAll("\\]", "");
                        String[] ini = param[0].split(",");
                        double[] initial = {0};
                        for(int i = 0; i < ini.length;i++){
                            initial[i] = Double.parseDouble(ini[i]);
                        }
                        res = Jacobi.jacobi(a,b,initial,eps,iterations);
//
//                        GaussSeidil t = new  GaussSeidil(a,b,initial,iterations,eps,precision);
//                            res = t.solve();
//                        System.out.println(res[0]);
                    }else {
//                        precision = Integer.parseInt(param[3]);
//                        gaussSeidel.digit = Integer.parseInt(param[3]);
                        int iterations = Integer.parseInt(param[1]);
                        double eps = Double.parseDouble(param[2]);
                        param[0].replaceAll("\\[", "");
                        param[0].replaceAll("\\]", "");
                        String[] ini = param[0].split(",");
                        double[] initial = new double[ini.length];
//                        for (int i = 0; i < ini.length; i++){
//
//                        }
                        for(int i = 0; i < ini.length;i++){
                            initial[i] = Double.parseDouble(ini[i]);
                        }
                        res = Jacobi.jacobi(a,b,initial,eps,iterations);
//
//                        GaussSeidil t = new  GaussSeidil(a,b,initial,iterations,eps,precision);
//                            res = t.solve();
//                        System.out.println(res[0]);
                    }
                }
                break;

        }
//        if(!method.equals("Gauss-Seidil")) {
        System.out.println(res[0]);
        String[] finalform = res[0].split(",");
        res[0] = "";
        for (int i = 0; i < finalform.length; i++) {
            finalform[i] = x[i] + "=" + finalform[i];
        }
        for (int i = 0; i < finalform.length; i++) {
            res[0] += finalform[i];
        }
//        }

//        String[] finalform = res[0].split(",");
//        res[0] = "";
//        for(int i = 0; i < finalform.length; i++){
//            finalform[i] =  x[i]+"=" + finalform[i];
//        }
//        for(int i = 0; i < finalform.length; i++){
//            res[0] += finalform[i];
//        }
//        System.out.println(res[0]);
        return  res;
    }

    String[] solveNonLinear(String[] inputs) throws Exception {
        String method = inputs[0];
        String equation = inputs[1];
        double x0 = Double.parseDouble(inputs[2]);
        if(inputs[3].equals("null")){
            inputs[3] = "-1";
        }
        double x1 = Double.parseDouble(inputs[3]);
        int significat = -1;
        if (!inputs[4].equals("null"))
        {  significat = Integer.parseInt(inputs[4]);}
        int iter = Integer.parseInt(inputs[5]);
        double es = Double.parseDouble(inputs[6]);
//        method = method.toLowerCase(Locale.ROOT);
        String result[] = new String[3];
        switch (method){
            case "Bisection":
                BisectionMethod implement_bi = new BisectionMethod();
                result = implement_bi.rootByBisection(equation,x0,x1, es,iter,significat);
                break;

            case "False-Position":
                falsePosition implement_rf = new falsePosition();
                result = implement_rf.rootByFalsePosition(equation,x0,x1, es,iter,significat);
                break;
            case "Fixed point":
                fixedPoint implement_fp = new fixedPoint();
                result = implement_fp.Fixedpt(equation, x0, es, iter, significat);
                break;
            case "Newton-Raphson":
                NewtonRaphson implement_nr = new NewtonRaphson();
                if(significat == -1){
                    significat = 10;
                }
                result = implement_nr.newtonRaphson(equation, es, iter, significat);
                break;
            case "Secant Method":
                SecantMethod impelement_s = new SecantMethod();
                result = impelement_s.secantImplimentation(equation, x0, x1,es, iter, significat);
                break;


        }

        return result;
    }
}
