package medium._0362_Design_Hit_Counter.notes;

import java.util.LinkedList;
import java.util.Queue;

public class Approach_1_Using_Queue {
    class HitCounter {
        private Queue<Integer> hits;

        /** Initialize your data structure here. */
        public HitCounter() {
            this.hits = new LinkedList<Integer>();
        }

        /** Record a hit.
         @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            this.hits.add(timestamp);
        }

        /** Return the number of hits in the past 5 minutes.
         @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            while (!this.hits.isEmpty()) {
                int diff = timestamp - this.hits.peek();
                if (diff >= 300) this.hits.remove();
                else break;
            }
            return this.hits.size();
        }
    }
}
