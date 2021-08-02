package easy._0346_Moving_Average_from_Data_Stream;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) {
        MovingAverage movingAverage = new MovingAverage(3);
        System.out.println(movingAverage.next(1)); // 1.0
        System.out.println(movingAverage.next(10)); // 5.5
        System.out.println(movingAverage.next(3)); // 4.66667
        System.out.println(movingAverage.next(5)); // 6.0

        MovingAverage_Circular movingAverageCircular = new MovingAverage_Circular(3);
        System.out.println(movingAverageCircular.next(1)); // 1.0
        System.out.println(movingAverageCircular.next(10)); // 5.5
        System.out.println(movingAverageCircular.next(3)); // 4.66667
        System.out.println(movingAverageCircular.next(5)); // 6.0
    }
}

/*  Using a fixed size circular queue - Derived from Sample Solution
    Time complexity: O(1)
        All the array operations takes constant time
    Space complexity: O(n)
        n -> input size
 */
class MovingAverage_Circular {
    int[] circularQueue;
    int windowSize;
    double sum;
    int head;
    boolean full;

    /** Initialize your data structure here. */
    public MovingAverage_Circular(int size) {
        circularQueue = new int[size];
        windowSize = size;
        sum = 0.0;
        head = 0;
        full = false;
    }

    public double next(int val) {
        sum = sum - circularQueue[head] + val;
        circularQueue[head] = val;
        head = (head + 1) % windowSize;

        if (full) {
            return sum / windowSize;
        }
        if (head == 0) {
            full = true;
            return sum / windowSize;
        }

        return sum / head;
    }
}

/*  Using a queue
    Time complexity: O(1)
        Both add and poll function of a queue takes constant time
    Space complexity: O(n)
        n -> input size
 */
class MovingAverage {
    private final int size;
    private final Queue<Integer> queue;
    private double sum = 0.0;

    /** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        queue = new LinkedList<>();
    }

    public double next(int val) {
        queue.add(val);
        sum += val;
        if (queue.size() > size) {
            sum -= queue.poll();
        }
        return sum / queue.size();
    }
}