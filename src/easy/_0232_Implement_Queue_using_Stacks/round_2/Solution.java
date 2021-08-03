package easy._0232_Implement_Queue_using_Stacks.round_2;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {
    public static void main(String[] args) {
        MyQueue myQueue1 = new MyQueue();
        myQueue1.push(1); // queue is: [1]
        myQueue1.push(2); // queue is: [1, 2] (leftmost is front of the queue)
        System.out.println(myQueue1.peek()); // return 1
        System.out.println(myQueue1.pop()); // return 1, queue is [2]
        System.out.println(myQueue1.empty()); // return false

        MyQueue myQueue2 = new MyQueue();
        myQueue2.push(1);
        myQueue2.push(2);
        myQueue2.push(3);
        myQueue2.push(4);
        System.out.println(myQueue2.pop()); // 1
        myQueue2.push(5);
        System.out.println(myQueue2.pop()); // 2
        System.out.println(myQueue2.pop()); // 3
        System.out.println(myQueue2.pop()); // 4
        System.out.println(myQueue2.pop()); // 5

        MyQueue myQueue3 = new MyQueue();
        myQueue3.push(1);
        myQueue3.push(2);
        System.out.println(myQueue3.pop()); // 1
        System.out.println(myQueue3.peek()); // 2
    }

    /*  Using front in peek - Derived from Sample Solution
        Time complexity:
            O(1) - push
            Amortized O(1) - pop
                The number of elements moved from stack1 to stack2 is
                proportional to the number of elements added into stack1
            O(1) - peek
        Space complexity: O(n)
            where n is the number of elements saved in the two stacks before popping out
     */
    static class MyQueue {
        Deque<Integer> stack1;
        Deque<Integer> stack2;
        int front;

        /** Initialize your data structure here. */
        public MyQueue() {
            stack1 = new ArrayDeque<>();
            stack2 = new ArrayDeque<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            if (stack1.isEmpty()) {
                front = x;
            }
            stack1.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        /** Get the front element. */
        public int peek() {
            if (!stack2.isEmpty()) {
                return stack2.peek();
            }
            return front;
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

//    /*  Time complexity:
//            O(1) - push
//            Amortized O(1) - pop
//                The number of elements moved from stack1 to stack2 is
//                proportional to the number of elements added into stack1
//            Amortized O(1) - peek
//                The number of elements moved from stack1 to stack2 is
//                proportional to the number of elements added into stack1
//        Space complexity: O(n)
//            where n is the number of elements saved in the two stacks before popping out
//     */
//    static class MyQueue {
//        Deque<Integer> stack1;
//        Deque<Integer> stack2;
//
//        /** Initialize your data structure here. */
//        public MyQueue() {
//            stack1 = new ArrayDeque<>();
//            stack2 = new ArrayDeque<>();
//        }
//
//        /** Push element x to the back of queue. */
//        public void push(int x) {
//            stack1.push(x);
//        }
//
//        /** Removes the element from in front of queue and returns that element. */
//        public int pop() {
//            if (stack2.isEmpty()) {
//                while (!stack1.isEmpty()) {
//                    stack2.push(stack1.pop());
//                }
//            }
//            return stack2.pop();
//        }
//
//        /** Get the front element. */
//        public int peek() {
//            if (stack2.isEmpty()) {
//                while (!stack1.isEmpty()) {
//                    stack2.push(stack1.pop());
//                }
//            }
//            return stack2.peek();
//        }
//
//        /** Returns whether the queue is empty. */
//        public boolean empty() {
//            return stack1.isEmpty() && stack2.isEmpty();
//        }
//    }
}