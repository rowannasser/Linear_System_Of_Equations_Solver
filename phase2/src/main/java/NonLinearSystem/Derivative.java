package NonLinearSystem;
import java.util.ArrayList;

public class Derivative {

    //function to derivative the input
    public static String der(String input) {
        input = formatInput(input);
        ArrayList<Token> tokens = new ArrayList<>();
        splitIntoTokens(tokens, input);
        tokens.sort((t1, t2) -> {
            return t1.getPower() > t2.getPower() ? -1 : 0;
        });
            return printDiff(tokens);
    }

    // function to handle the input
    private static String formatInput(String input) {

        input = input.trim();
        if (!(input.startsWith("-") || input.startsWith("+"))) {
            input = '+' + input;}
        input = input.replaceAll("\\s+", "").replaceAll("(-x)", "-1x")
                .replaceAll("(\\+x)", "+1x").replaceAll("\\*","");
        return input;
    }

    private static void splitIntoTokens(ArrayList<Token> tokens, String input) {

        char var = 'x';
        if (input.isEmpty()) {return;}

        int varIndex = input.indexOf(var);
        System.out.println(input);
        if (varIndex != -1) {
            int plusIndex = input.substring(varIndex).indexOf("+");
            int minusIndex = input.substring(varIndex).indexOf("-");

            if (plusIndex == -1 && minusIndex == -1) {
                Token t = Token.build(input, varIndex);
                tokens.add(t);}
            else if (minusIndex != -1 && minusIndex < plusIndex || plusIndex == -1) {
                Token t = Token.build(input.substring(0, minusIndex + varIndex), varIndex);
                tokens.add(t);
                splitIntoTokens(tokens, input.substring(minusIndex + varIndex));}
            else {
                Token t = Token.build(input.substring(0, plusIndex + varIndex), varIndex);
                tokens.add(t);
                splitIntoTokens(tokens, input.substring(plusIndex + varIndex));}
        }
    }

    // function distinct positive numbers
    private static String withSign(double in) {
        return in >= 0 ? "+" + in : "" + in;}

    // function to collect the output and print
    private static String printDiff(ArrayList<Token> tokens) {
        String re= "";
        double total = tokens.get(0).getNumber();
        int p = tokens.get(0).getPower();
        for (int x = 1; x < tokens.size(); x++) {
            Token t = tokens.get(x);

            if (t.getPower() == p) {
                total += t.getNumber();}
            else {
                re = re + withSign(total * p) + "x^" + (p - 1);
                p = t.getPower();
                total = t.getNumber();}
        }
        re = re+withSign(total * p) + "x^" + (p - 1);
        return re;
    }
}
