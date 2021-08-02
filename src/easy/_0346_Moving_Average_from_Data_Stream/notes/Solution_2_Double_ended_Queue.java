package easy._0346_Moving_Average_from_Data_Stream.notes;

import java.util.ArrayDeque;
import java.util.Deque;

public class Solution_2_Double_ended_Queue {
    class MovingAverage {
        int size, windowSum = 0, count = 0;
        Deque queue = new ArrayDeque<Integer>();

        public MovingAverage(int size) {
            this.size = size;
        }

        public double next(int val) {
            ++count;
            // calculate the new sum by shifting the window
            queue.add(val);
            int tail = count > size ? (int)queue.poll() : 0;

            windowSum = windowSum - tail + val;

            return windowSum * 1.0 / Math.min(size, count);
        }
    }
}
