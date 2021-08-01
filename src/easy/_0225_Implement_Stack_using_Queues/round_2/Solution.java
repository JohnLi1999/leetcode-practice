package easy._0225_Implement_Stack_using_Queues.round_2;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // 2
        System.out.println(myStack.pop()); // 2
        System.out.println(myStack.empty()); // False
    }
}

/*  Using one queue
    Time complexity:
        O(n) for push
        O(1) for pop and top
    Space complexity: O(1)
 */
class MyStack {
    private final Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        /*  queue
            1
            1 2
            1 2 3

            stack
            1
            2 1
            3 2 1
         */
        int size = queue.size();
        queue.add(x);
        int count = 0;
        while (count < size) {
            queue.add(queue.poll());
            count += 1;
        }
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }

    /** Get the top element. */
    public int top() {
        return queue.peek();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
