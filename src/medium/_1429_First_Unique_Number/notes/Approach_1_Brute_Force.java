package medium._1429_First_Unique_Number.notes;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class Approach_1_Brute_Force {
    class FirstUnique {

        private Queue<Integer> queue = new ArrayDeque<>();

        public FirstUnique(int[] nums) {
            for (int num : nums) {
                queue.add(num);
            }
        }

        public int showFirstUnique() {
            for (int num : queue) {
                int count = Collections.frequency(queue, num);
                if (count == 1) {
                    return num;
                }
            }
            return -1;
        }

        public void add(int value) {
            queue.add(value);
        }
    }
}
