package medium._0150_Evaluate_Reverse_Polish_Notation.notes;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.function.BiFunction;

public class Approach_2_Evaluate_with_Stack {

    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = new HashMap<>();

    // Ensure this only gets done once for ALL test cases.
    static {
        OPERATIONS.put("+", (a, b) -> a + b);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
    }

    public int evalRPN(String[] tokens) {

        Stack<Integer> stack = new Stack<>();

        for (String token : tokens) {

            if (!OPERATIONS.containsKey(token)) {
                stack.push(Integer.valueOf(token));
                continue;
            }

            int number2 = stack.pop();
            int number1 = stack.pop();
            BiFunction<Integer, Integer, Integer> operation;
            operation = OPERATIONS.get(token);
            int result = operation.apply(number1, number2);
            stack.push(result);
        }

        return stack.pop();

    }
}

/** Here are the solutions without lambda. */
//class Solution {
//
//    public int evalRPN(String[] tokens) {
//
//        Stack<Integer> stack = new Stack<>();
//
//        for (String token : tokens) {
//
//            if (!"+-*/".contains(token)) {
//                stack.push(Integer.valueOf(token));
//                continue;
//            }
//
//            int number2 = stack.pop();
//            int number1 = stack.pop();
//
//            int result = 0;
//
//            switch (token) {
//                case "+":
//                    result = number1 + number2;
//                    break;
//                case "-":
//                    result = number1 - number2;
//                    break;
//                case "*":
//                    result = number1 * number2;
//                    break;
//                case "/":
//                    result = number1 / number2;
//                    break;
//            }
//
//            stack.push(result);
//
//        }
//
//        return stack.pop();
//    }
//}