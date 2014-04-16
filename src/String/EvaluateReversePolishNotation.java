package String;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 *
 */

import java.util.*;

public class EvaluateReversePolishNotation {

    public int evalRPN(String[] tokens) {
        if (tokens.length < 1) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < tokens.length; i++) {
            if (isInteger(tokens[i])) {
                stack.push(Integer.parseInt(tokens[i]));
            } else {
                int back = stack.pop();
                int front = stack.pop();
                int result = calculate(front, back, tokens[i]);
                stack.push(result);
            }
        }
        return stack.peek();
    }

    private boolean isInteger(String str) {
        String Regex = "[+-]?\\d+";
        if (str.matches(Regex)) {
            return true;
        }
        return false;
    }

    private int calculate(int front, int back, String operator) {
        if (operator.equals("+")) {
            return front + back;
        }
        if (operator.equals("-")) {
            return front - back;
        }
        if (operator.equals("*")) {
            return front * back;
        }
        return front / back;
    }

    public static void main(String[] args) {
        EvaluateReversePolishNotation e = new EvaluateReversePolishNotation();
        String[] s1 = {"2", "1", "+", "3", "*"};
        String[] s2 = {"4", "13", "5", "/", "+"};
        String[] s3 = {"-78", "-33", "196", "+", "-19", "-", "115", "+", "-", "-99",
            "/", "-18", "8", "*", "-86", "-", "-", "16", "/", "26", "-14", "-", "-",
            "47", "-", "101", "-", "163", "*", "143", "-", "0", "-", "171", "+", "120",
            "*", "-60", "+", "156", "/", "173", "/", "-24", "11", "+", "21", "/", "*",
            "44", "*", "180", "70", "-40", "-", "*", "86", "132", "-84", "+", "*", "-",
            "38", "/", "/", "21", "28", "/", "+", "83", "/", "-31", "156", "-", "+", "28",
            "/", "95", "-", "120", "+", "8", "*", "90", "-", "-94", "*", "-73", "/", "-62",
            "/", "93", "*", "196", "-", "-59", "+", "187", "-", "143", "/", "-79", "-89", "+", "-"};

        System.out.println(e.evalRPN(s1));
        System.out.println(e.evalRPN(s2));
        System.out.println(e.evalRPN(s3));
    }
}
