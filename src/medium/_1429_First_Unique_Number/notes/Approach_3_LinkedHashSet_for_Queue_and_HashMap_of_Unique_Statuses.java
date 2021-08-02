package medium._1429_First_Unique_Number.notes;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class Approach_3_LinkedHashSet_for_Queue_and_HashMap_of_Unique_Statuses {
    class FirstUnique {

        private Set<Integer> setQueue = new LinkedHashSet<>();
        private Map<Integer, Boolean> isUnique = new HashMap<>();

        public FirstUnique(int[] nums) {
            for (int num : nums) {
                this.add(num);
            }
        }

        public int showFirstUnique() {
            // If the queue contains values, we need to get the first one from it.
            // We can do this by making an iterator, and getting its first item.
            if (!setQueue.isEmpty()) {
                return setQueue.iterator().next();
            }
            return -1;
        }

        public void add(int value) {
            // Case 1: This value is not yet in the data structure.
            // It should be ADDED.
            if (!isUnique.containsKey(value)) {
                isUnique.put(value, true);
                setQueue.add(value);
                // Case 2: This value has been seen once, so is now becoming
                // non-unique. It should be REMOVED.
            } else if (isUnique.get(value)) {
                isUnique.put(value, false);
                setQueue.remove(value);
            }
        }
    }
}
