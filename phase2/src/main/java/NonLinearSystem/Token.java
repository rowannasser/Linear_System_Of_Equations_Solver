package NonLinearSystem;

public class Token {
        private double number;
        private int power;

        public Token(double number, int power) {
            this.number = number;
            this.power = power;
        }

        public static Token build(String input, int respectIndex) {
            if (respectIndex != -1) {
                String number = input.substring(0, respectIndex);
                int pow = input.substring(respectIndex).indexOf("^");
                if (pow != -1) {
                    // has a power
                    return new Token(Double.parseDouble(number), Integer.parseInt(input.substring(pow + respectIndex + 1)));
                }
                return new Token(Double.parseDouble(number), 1);

            } else {
                return new Token(Double.parseDouble(input), 1);
            }
        }

        public double getNumber() {
            return number;
        }


        public int getPower() {
            return power;
        }

        @Override
        public String toString() {
            return "number = " + number + ", power = " + power;
        }
    }

