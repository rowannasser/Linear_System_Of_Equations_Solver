package NonLinearSystem;

import java.util.ResourceBundle;
import java.util.Scanner;

public class NewtonRaphson extends Derivative
{
  // function for rounding results
  public static double Round(double value,int digit) {
    double scale =  Math.pow(10, digit);
    return Math.round(value * scale) / scale;
  }

  public static String[] newtonRaphson(String temp,double y, int maxIteration, int digit)
  {
    String steps = "";
    temp = temp.replaceAll("x","\\x").replaceAll("\\+\\*","+")
            .replaceAll("\\-\\*","-").replaceAll("\\*\\*","");
    if(temp.charAt(0)=='*'){temp= temp.substring(1, temp.length());}

    function f,f_,x;
    x = new function("x");
    f = new function(temp);
    //derivative the input
    temp = der(temp);
    //replace some symbols to simplify the output
    temp  = temp .replaceAll(("x"),("*\\x")).replaceAll("x\\^0","1").replaceAll(("x\\^1"),("x"));
    if(temp.charAt(0)=='+' || temp.charAt(0)=='-'){ temp = temp.substring(1,temp.length());}
    f_ = new function(temp);
    //
    variable_table table = f.get_variables_table();
    table.add_variable("x",Double.toString(y));

    int iteration = 1;
    double dif = 1.f;
    while(dif!=0f && iteration<=maxIteration)
    { // print steps value of (x, f, f')
      steps+= "Iteration : "+iteration+"&";
      double f_x,f__x,x_x;
      f_x = Round(f.get_value(table),digit);
      f__x =Round(f.get_value(table),digit);
      x_x = Round(x.get_value(table), digit);
      steps+="x"+iteration+" = "+x_x + "&";
      steps+="f"+iteration+" = "+f_x + "&";
      steps+="f'"+iteration+" = "+f__x + "&";
      // ans of current iteration and x of the next iteration
      double ans =Round(x_x - Round((f_x /f__x ),digit), digit);
      steps+="x"+Integer.toString(iteration+1)+" = "+ans + "&";
      table.add_variable("x",Double.toString(ans));
      dif = Round((ans - y)/ans,digit);
      y = ans;
      iteration++;
    }
    y= Round(y,digit);
    double F= f.get_value(table);
    F = Round(F,digit);
    String res;
    res = "f(" +  y+ ") =" + f;///res
    String[] result = new String[3];
    result[0] = res;
    result[1] = steps;
//    result[2] = runtime;
    return result;
  }
  public static void main(String []args){
    String temp;
    //function should be 3*x not 3x
    System.out.println("Enter the function: ");
    Scanner input = new Scanner(System.in);
    temp = input.nextLine();
    System.out.println("Enter initial value for x : ");
    double y = input.nextFloat();

    newtonRaphson(temp,y,50,5);
  }
}