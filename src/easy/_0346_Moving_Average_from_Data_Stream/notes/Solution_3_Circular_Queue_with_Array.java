package easy._0346_Moving_Average_from_Data_Stream.notes;

public class Solution_3_Circular_Queue_with_Array {
    class MovingAverage {
        int size, head = 0, windowSum = 0, count = 0;
        int[] queue;
        public MovingAverage(int size) {
            this.size = size;
            queue = new int[size];
        }

        public double next(int val) {
            ++count;
            // calculate the new sum by shifting the window
            int tail = (head + 1) % size;
            windowSum = windowSum - queue[tail] + val;
            // move on to the next head
            head = (head + 1) % size;
            queue[head] = val;
            return windowSum * 1.0 / Math.min(size, count);
        }
    }
}
