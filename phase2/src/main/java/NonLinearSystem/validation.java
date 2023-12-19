package NonLinearSystem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Locale;

public class validation {
    boolean check_brackets(String expr){
        Deque<Character> stack = new ArrayDeque<Character>();
        for (int i = 0; i < expr.length(); i++)
        {
            char x = expr.charAt(i);
            if (x == '(')
            {
                stack.push(x);
                continue;
            }
            else if(x == ')'){
                stack.pop();
            }
//            if (stack.isEmpty())
//                return false;
//            char check;
//            switch (x) {
//                case ')':
//                    System.out.println("tota");
//                    check = stack.pop();
//                    if (check == '{' || check == '[')
//                        return false;
//                    break;
//            }
            }
        return (stack.isEmpty());
    }

    public boolean validate(String eqn){
        eqn = eqn.toLowerCase(Locale.ROOT);
        boolean state=true;
        if(!(eqn.contains("sin")||eqn.contains("cos")||eqn.contains("^")||eqn.contains("e"))){
            System.out.println("Sorry, the equation does not include poly, sin or cos");
            return false;
        }else if(eqn.contains("<") || eqn.contains(">")){
            System.out.println("Sorry, Incorrect format");
            return false;
        }
        else if(!check_brackets(eqn)){
            System.out.println("Sorry, Incorrect format");
            return false;
        }
        else if(eqn.contains("tan")){
            System.out.println("Sorry, Incorrect format");
            return false;
        }


        return state;
    }
}
