package medium._0362_Design_Hit_Counter;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class Solution {
    public static void main(String[] args) {
        HitCounter hitCounter = new HitCounter();
        hitCounter.hit(1); // hit at timestamp 1.
        hitCounter.hit(2); // hit at timestamp 2.
        hitCounter.hit(3); // hit at timestamp 3.
        System.out.println(hitCounter.getHits(4)); // get hits at timestamp 4, return 3.
        hitCounter.hit(300); // hit at timestamp 300.
        System.out.println(hitCounter.getHits(300)); // get hits at timestamp 300, return 4.
        System.out.println(hitCounter.getHits(301)); // get hits at timestamp 301, return 3.
        System.out.println(hitCounter.getHits(301)); // get hits at timestamp 301, return 3.
        hitCounter.hit(301); // hit at timestamp 301.
        hitCounter.hit(301); // hit at timestamp 301.
        System.out.println(hitCounter.getHits(301)); // get hits at timestamp 301, return 5.
    }

    /*  Using Deque
        Time complexity:
            O(1) - constructor
            Amortized O(1) - hit
            Amortized O(1) - getHists
                The number of elements to remove is proportional to the timestamp got hit
        Space complexity: O(k)
            where k is length of the interval
     */
    static class HitCounter {
        public static final int INTERVAL = 300;
        Deque<Pair> deque;
        int sum;

        /** Initialize your data structure here. */
        public HitCounter() {
            deque = new ArrayDeque<>();
            sum = 0;
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            int intervalStart = timestamp - INTERVAL + 1;
            while (!deque.isEmpty() && deque.getFirst().key < intervalStart) {
                sum -= deque.getFirst().value;
                deque.pollFirst();
            }

            if (deque.isEmpty() || deque.getLast().key != timestamp) {
                deque.addLast(new Pair(timestamp, 1));
            } else{
                deque.getLast().value = deque.getLast().value + 1;
            }
            sum += 1;
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            int intervalStart = timestamp - INTERVAL + 1;
            while (!deque.isEmpty() && deque.getFirst().key < intervalStart) {
                sum -= deque.getFirst().value;
                deque.pollFirst();
            }
            return sum;
        }
    }

    static class Pair {
        public int key;
        public int value;
        public Pair(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

//    /*  Using HashMap
//        Time complexity:
//            O(1) - constructor
//            O(1) - hit
//            O(n) - getHists, where n is the length of the interval
//        Space complexity: O(k)
//            where k is the number of unique timestamps got hits
//     */
//    static class HitCounter {
//        public static final int INTERVAL = 300;
//        HashMap<Integer, Integer> map;
//
//        /** Initialize your data structure here. */
//        public HitCounter() {
//            map = new HashMap<>();
//        }
//
//        /** Record a hit.
//         @param timestamp - The current timestamp (in seconds granularity). */
//        public void hit(int timestamp) {
//            map.put(timestamp, map.getOrDefault(timestamp, 0) + 1);
//        }
//
//        /** Return the number of hits in the past 5 minutes.
//         @param timestamp - The current timestamp (in seconds granularity). */
//        public int getHits(int timestamp) {
//            int sum = 0;
//            for (int time = timestamp - INTERVAL + 1; time <= timestamp; time++) {
//                sum += map.getOrDefault(time, 0);
//            }
//            return sum;
//        }
//    }
}
