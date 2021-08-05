package medium._0150_Evaluate_Reverse_Polish_Notation.notes;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class Approach_1_Reducing_the_List_In_place {

    private static final Map<String, BiFunction<Integer, Integer, Integer>> OPERATIONS = new HashMap<>();

    // Ensure this only gets done once for ALL test cases.
    static {
        OPERATIONS.put("+", (a, b) -> a + b);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
    }

    public int evalRPN(String[] tokens) {

        int currentPosition = 0;
        int length = tokens.length; // We need to keep track of this ourselves.

        while (length > 1) {

            // Move the position pointer to the next operator token.
            while (!OPERATIONS.containsKey(tokens[currentPosition])) {
                currentPosition++;
            }

            // Extract the operation and numbers to apply operation too.
            String operation = tokens[currentPosition];
            int number1 = Integer.parseInt(tokens[currentPosition - 2]);
            int number2 = Integer.parseInt(tokens[currentPosition - 1]);

            // Calculate the result to overwrite the operator with.
            BiFunction<Integer, Integer, Integer> operator = OPERATIONS.get(operation);
            int value = operator.apply(number1, number2);
            tokens[currentPosition] = Integer.toString(value);

            // Delete numbers and point pointers correctly.
            delete2AtIndex(tokens, currentPosition - 2, length);
            currentPosition--;
            length -= 2;
        }

        return Integer.parseInt(tokens[0]);
    }

    private void delete2AtIndex(String[] tokens, int d, int length) {
        for (int i = d; i < length - 2; i++) {
            tokens[i] = tokens[i + 2];
        }
    }
}

/** Here are the solutions without the use of lambda functions. */
//class Solution {
//    public int evalRPN(String[] tokens) {
//
//        int currentPosition = 0;
//        int length = tokens.length; // We will need to keep track of this ourselves.
//
//        while (length > 1) {
//
//            // Move the position pointer to the next operator token.
//            while (!"+-*/".contains(tokens[currentPosition])) {
//                currentPosition++;
//            }
//
//            // Extract the numbers.
//            int number1 = Integer.parseInt(tokens[currentPosition - 2]);
//            int number2 = Integer.parseInt(tokens[currentPosition  - 1]);
//
//            // Calculate the result to overwrite the operator with.
//            int newValue = 0;
//            switch (tokens[currentPosition]) {
//                case "+":
//                    newValue = number1 + number2;
//                    break;
//                case "-":
//                    newValue = number1 - number2;
//                    break;
//                case "*":
//                    newValue = number1 * number2;
//                    break;
//                case "/":
//                    newValue = number1 / number2;
//                    break;
//            }
//            tokens[currentPosition] = Integer.toString(newValue);
//
//            // Delete numbers and point pointers correctly.
//            delete2AtIndex(tokens, currentPosition - 2, length);
//            currentPosition--;
//            length -= 2;
//        }
//
//        return Integer.parseInt(tokens[0]);
//    }
//
//    private void delete2AtIndex(String[] tokens, int d, int length) {
//        for (int i = d; i < length - 2; i++) {
//            tokens[i] = tokens[i + 2];
//        }
//    }
//}