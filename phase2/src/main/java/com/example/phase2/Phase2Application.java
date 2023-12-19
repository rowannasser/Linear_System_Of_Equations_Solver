package com.example.phase2;
import NonLinearSystem.BisectionMethod;
import NonLinearSystem.falsePosition;
import NonLinearSystem.validation;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import org.assertj.core.api.Assertions;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;
import java.util.Scanner;

import static com.example.phase2.GUI.createAndShowGUI;

@SpringBootApplication
public class Phase2Application {

	public double evaluateFunction(String eqn, double x) {
		validation eq = new validation();
		if(eq.validate(eqn)){
		if(eqn.contains("=")){
			String[] equation = eqn.split("=");
			if (equation.length != 2){
				System.out.println("Error");
				System.exit(1);
			}
			double result= 0;
			try {

				Expression expression = new ExpressionBuilder(equation[0])
						.variables("x")
						.build()
						.setVariable("x", x);
				 result = expression.evaluate();
			}
			catch (Exception e){
				System.out.println("Invvalid equation");
			}
			try {


			Expression expression2 = new ExpressionBuilder(equation[1])
					.variables("x")
					.build()
					.setVariable("x", x);

			 result -= expression2.evaluate();}
			catch (Exception e){
				System.out.println("Invvalid equation");
			}

			return result;
		}else { double result = 0;
try {
		Expression expression = new ExpressionBuilder(eqn)
				.variables("x")
				.build()
				.setVariable("x", x);

		 result = expression.evaluate();}
catch (Exception e){
	System.out.println("Invvalid equation");
}
		return result;
		}}
		return 0;
	}
	public static void main(String[] args) throws Exception {
//		SpringApplication.run(Phase2Application.class, args);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			public void run() {

				createAndShowGUI();

			}

		});



//		Phase2Application eva = new Phase2Application();
//		Scanner sc = new Scanner(System.in);
////		String eqution = sc.nextLine();
////		double xl = sc.nextDouble();
////		double xu = sc.nextDouble();
////		double es = sc.nextDouble();
////		int im = sc.nextInt();
////		BisectionMethod d = new BisectionMethod();
////		falsePosition g = new falsePosition();
////		double res = g.rootByFalsePosition(eqution,xl,xu,es,im);
////		System.out.println(res);
//		String method = sc.nextLine();
//		method = method.toLowerCase(Locale.ROOT);
//		switch (method){
//			case "bisection":
//				String equation = sc.nextLine();
//				double xl = sc.nextDouble();
//				double xu = sc.nextDouble();
//				double es = sc.nextDouble();
//				int im = sc.nextInt();
//				BisectionMethod d = new BisectionMethod();
//
//		}



	}

}
