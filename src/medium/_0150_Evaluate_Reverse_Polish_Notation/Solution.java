package medium._0150_Evaluate_Reverse_Polish_Notation;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.function.ToIntBiFunction;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        String[] tokens;
        tokens = new String[] {"2","1","+","3","*"};
        System.out.println(s.evalRPN(tokens)); // 9
        tokens = new String[] {"4","13","5","/","+"};
        System.out.println(s.evalRPN(tokens)); // 6
        tokens = new String[] {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(s.evalRPN(tokens)); // 22
    }

    /*  Using stack with lambda function - Derived from Sample Solution
        Time complexity: O(n)
        Space complexity: O(n)
     */
    private static final Map<String, ToIntBiFunction<Integer, Integer>> OPERATIONS = new HashMap<>();

    static {
        OPERATIONS.put("+", Integer::sum);
        OPERATIONS.put("-", (a, b) -> a - b);
        OPERATIONS.put("*", (a, b) -> a * b);
        OPERATIONS.put("/", (a, b) -> a / b);
    }

    public int evalRPN(String[] tokens) {
        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }

        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            if (OPERATIONS.containsKey(token)) {
                ToIntBiFunction<Integer, Integer> operation = OPERATIONS.get(token);
                int num2 = stack.pop();
                int num1 = stack.pop();
                int result = operation.applyAsInt(num1, num2);
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

//    /*  Using stack
//        Time complexity: O(n)
//        Space complexity: O(n)
//     */
//    public int evalRPN(String[] tokens) {
//        if (tokens.length == 1) {
//            return Integer.parseInt(tokens[0]);
//        }
//
//        Deque<Integer> stack = new ArrayDeque<>();
//        int num1;
//        int num2;
//
//        for (String token : tokens) {
//            switch (token) {
//                case "+":
//                    num2 = stack.pop();
//                    num1 = stack.pop();
//                    int result = num1 + num2;
//                    stack.push(result);
//                    break;
//                case "-":
//                    num2 = stack.pop();
//                    num1 = stack.pop();
//                    result = num1 - num2;
//                    stack.push(result);
//                    break;
//                case "*":
//                    num2 = stack.pop();
//                    num1 = stack.pop();
//                    result = num1 * num2;
//                    stack.push(result);
//                    break;
//                case "/":
//                    num2 = stack.pop();
//                    num1 = stack.pop();
//                    result = num1 / num2;
//                    stack.push(result);
//                    break;
//                default:
//                    stack.push(Integer.parseInt(token));
//                    break;
//            }
//        }
//
//        return stack.pop();
//    }
}
