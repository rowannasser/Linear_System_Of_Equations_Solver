package NonLinearSystem;

import com.example.phase2.Phase2Application;

import java.util.Scanner;

public class SecantMethod {
	public static double Round(double value,int digit) {
		double scale =  Math.pow(10, digit);
		return Math.round(value * scale) / scale;
	}

	static double f(String eqn,double x) {
		Phase2Application evaluator = new Phase2Application();
        
        double f = evaluator.evaluateFunction(eqn,x);
        return f;
    }
	

				
			public static String[] secantImplimentation(String eqn,double x0, double x1, double e,int itr, int sign) {
				long start = System.currentTimeMillis();
				long end = System.currentTimeMillis();
				if (sign == -1) {
					double xm, x2;
					String steps = "";
					String res = "";

					if (f(eqn, x0) * f(eqn, x1) < 0) {
						int i = 0;
						do {

							// calculate x2

//		            	x2 = (x0 * f(x1) - x1 * f(x0)) / (f(x1) - f(x0));
							x2 = x1 - ((f(eqn, x1) * (x0 - x1)) / (f(eqn, x0) - f(eqn, x1)));
//		            	System.out.println("Xi+1 = " + x1 + " - ((( " + x0 + "-" + x1 +" ) * "+ f(x1) + ")/( "+ f(x0) + "-" +f(x1) + "))" );
							steps += "Xi+1 = " + x1 + " - ((( " + x0 + "-" + x1 + " ) * " + f(eqn, x1) + ")/( " + f(eqn, x0) + "-" + f(eqn, x1) + ")) &";

							// check if x0 is root of equation or not

							if ((f(eqn, x0) * f(eqn, x2)) == 0)
								break;
							else {

								x0 = x1;
								x1 = x2;

								System.out.printf("update the value of interval \nx0 = %f \nx1 %f\n", x1, x2);
								steps += "update the value of interval &Xi-1 = " + x1 + " &Xi = " + x2 + "&";

//			                xm = (x0 * f(x1) - x1 * f(x0)) / (f(x1) - f(x0));
							}

						} while ((Math.abs(x1 - x0) / x1) >= e && i < itr); //here it is converge

						System.out.println("-------------------------------------------------------------------------");
						System.out.println("The final result of x = " + x2);
						steps += "&The final result of x = " + x2;
						res = "X = " + x2;


					} else
						steps += "Can not find a root in this interval";

					String[] result = new String[3];
					result[0] = res;
					result[1] = steps;
					end = System.currentTimeMillis();
					long elapsedTime = end - start;
					result[2] = "R.T = " + elapsedTime;


					return result;
				}else {
					double xm, x2;
					String steps = "";
					String res = "";

					if (f(eqn, x0) * f(eqn, x1) < 0) {
						int i = 0;
						do {

							// calculate x2

//		            	x2 = (x0 * f(x1) - x1 * f(x0)) / (f(x1) - f(x0));
							x2 = x1 - ((f(eqn, x1) * (x0 - x1)) / (f(eqn, x0) - f(eqn, x1)));
							x2 = Round(x2, sign);
//		            	System.out.println("Xi+1 = " + x1 + " - ((( " + x0 + "-" + x1 +" ) * "+ f(x1) + ")/( "+ f(x0) + "-" +f(x1) + "))" );
							steps += "Xi+1 = " + x1 + " - ((( " + x0 + "-" + x1 + " ) * " + f(eqn, x1) + ")/( " + f(eqn, x0) + "-" + f(eqn, x1) + ")) &";

							// check if x0 is root of equation or not

							if ((f(eqn, x0) * f(eqn, x2)) == 0)
								break;
							else {

								x0 = x1;
								x1 = x2;

								System.out.printf("update the value of interval \nx0 = %f \nx1 %f\n", x1, x2);
								steps += "update the value of interval &Xi-1 = " + x1 + " &Xi = " + x2 + "&";

//			                xm = (x0 * f(x1) - x1 * f(x0)) / (f(x1) - f(x0));
							}

						} while ((Math.abs(x1 - x0) / x1) >= e && i < itr); //here it is converge

						System.out.println("-------------------------------------------------------------------------");
						System.out.println("The final result of x = " + x2);
						steps += "&The final result of x = " + x2;
						res = "X = " + x2;


					} else
						steps += "Can not find a root in this interval";

					String[] result = new String[3];
					result[0] = res;
					result[1] = steps;
//		        result[2] = runtime;
					end = System.currentTimeMillis();
					long elapsedTime = end - start;
					result[2] = "R.T = " + elapsedTime;

					return result;
				}}
	public static void main(String[] args){
		String temp;
		System.out.println("Enter the function: ");
		Scanner input = new Scanner(System.in);
		temp = input.nextLine();
		System.out.println("Enter initial value for x : ");
		double y = input.nextDouble();
        double y1 = input.nextDouble();
		secantImplimentation(temp,y,y1,.0001,50,-1);

	}
			}
	





