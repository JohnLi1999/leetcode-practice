package medium._1429_First_Unique_Number;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;

public class Solution {
    public static void main(String[] args) {
        FirstUnique test1 = new FirstUnique(new int[] {2,3,5});
        System.out.println(test1.showFirstUnique()); // return 2
        test1.add(5);            // the queue is now [2,3,5,5]
        System.out.println(test1.showFirstUnique()); // return 2
        test1.add(2);            // the queue is now [2,3,5,5,2]
        System.out.println(test1.showFirstUnique()); // return 3
        test1.add(3);            // the queue is now [2,3,5,5,2,3]
        System.out.println(test1.showFirstUnique()); // return -1

        FirstUnique test2 = new FirstUnique(new int[] {7,7,7,7,7,7});
        System.out.println(test2.showFirstUnique()); // return -1
        test2.add(7);            // the queue is now [7,7,7,7,7,7,7]
        test2.add(3);            // the queue is now [7,7,7,7,7,7,7,3]
        test2.add(3);            // the queue is now [7,7,7,7,7,7,7,3,3]
        test2.add(7);            // the queue is now [7,7,7,7,7,7,7,3,3,7]
        test2.add(17);           // the queue is now [7,7,7,7,7,7,7,3,3,7,17]
        System.out.println(test2.showFirstUnique()); // return 17

        FirstUnique test3 = new FirstUnique(new int[] {809});
        System.out.println(test3.showFirstUnique()); // return 809
        test3.add(809);          // the queue is now [809,809]
        System.out.println(test3.showFirstUnique()); // return -1
    }

    /*  Time complexity:
            O(k) - constructor, k is the size of nums
            O(1) - showFirstUnique,
                LinkedHashSet takes O(1) for removal
            O(1) - add
        Space complexity:
            O(n) - n is number of unique elements
     */
    static class FirstUnique {
        HashMap<Integer, Boolean> map;
        LinkedHashSet<Integer> setQueue;

        public FirstUnique(int[] nums) {
            map = new HashMap<>();
            setQueue = new LinkedHashSet<>();

            for (int num : nums) {
                add(num);
            }
        }

        public int showFirstUnique() {
            if (!setQueue.isEmpty()) {
                return setQueue.iterator().next();
            }
            return -1;
        }

        public void add(int value) {
            if (!map.containsKey(value)) {
                map.put(value, true);
                setQueue.add(value);
            } else if (map.get(value)) {
                map.put(value, false);
                setQueue.remove(value);
            }
        }
    }

//    /*  Time complexity:
//            O(k) - constructor, k is the size of nums
//            Amortized O(1) - showFirstUnique,
//                the number of removals is proportional to the the number of add
//            O(1) - add
//        Space complexity:
//            O(n) - n is number of unique elements
//     */
//    static class FirstUnique {
//        HashMap<Integer, Boolean> map;
//        Deque<Integer> queue;
//
//        public FirstUnique(int[] nums) {
//            map = new HashMap<>();
//            queue = new LinkedList<>();
//
//            for (int num : nums) {
//                add(num);
//            }
//        }
//
//        public int showFirstUnique() {
//            while (!queue.isEmpty()) {
//                int num = queue.peek();
//                if (!map.get(num)) {
//                    queue.poll();
//                } else {
//                    return num;
//                }
//            }
//            return -1;
//        }
//
//        public void add(int value) {
//            if (!map.containsKey(value)) {
//                map.put(value, true);
//                queue.add(value);
//            } else {
//                map.put(value, false);
//            }
//        }
//    }
}
