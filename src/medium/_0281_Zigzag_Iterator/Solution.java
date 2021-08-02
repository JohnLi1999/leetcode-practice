package medium._0281_Zigzag_Iterator;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    public void test_two_pointers(List<Integer> v1, List<Integer> v2) {
        ZigzagIterator_Two_Pointers i = new ZigzagIterator_Two_Pointers(v1, v2);
        while (i.hasNext()) {
            System.out.print(i.next() + " ");
        }
        System.out.println();
    }

    public void test_queue(List<Integer> v1, List<Integer> v2) {
        ZigzagIterator_Queue i = new ZigzagIterator_Queue(v1, v2);
        while (i.hasNext()) {
            System.out.print(i.next() + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        s.test_two_pointers(List.of(1,2), List.of(3,4,5,6)); // 1,3,2,4,5,6
        s.test_two_pointers(List.of(1), List.of()); // 1
        s.test_two_pointers(List.of(), List.of(1)); // 1
        System.out.println();
        s.test_queue(List.of(1,2), List.of(3,4,5,6)); // 1,3,2,4,5,6
        s.test_queue(List.of(1), List.of()); // 1
        s.test_queue(List.of(), List.of(1)); // 1
    }

    /*  Using two pointers - Derived from Sample Solution Comments
        Time complexity: O(1) for all of constructor, next and hasNext
        Space complexity: O(1)
            Only used four pointers
     */
    static class ZigzagIterator_Two_Pointers {
        private final List<Integer> v1;
        private final List<Integer> v2;
        private int i;
        private int j;
        private boolean isV1;

        public ZigzagIterator_Two_Pointers(List<Integer> v1, List<Integer> v2) {
            this.v1 = v1;
            this.v2 = v2;
            i = 0;
            j = 0;
            isV1 = v1.size() != 0;
        }

        public int next() {
            if (i < v1.size() && isV1) {
                if (j < v2.size()) {
                    isV1 = false;
                }
                return v1.get(i++);
            }
            if (j < v2.size() && !isV1) {
                if (i < v1.size()) {
                    isV1 = true;
                }
                return v2.get(j++);
            }
            return Integer.MIN_VALUE;
        }

        public boolean hasNext() {
            return i < v1.size() || j < v2.size();
        }
    }

    /*  Using Queue
        Time complexity:
            O(n + m) - constructor
            O(1) - next
            O(1) - hasNext
        Space complexity: O(n + m)
     */
    static class ZigzagIterator_Queue {
        private final Deque<Integer> queue;

        public ZigzagIterator_Queue(List<Integer> v1, List<Integer> v2) {
            queue = new LinkedList<>();
            int i = 0;
            int j = 0;

            while (i < v1.size() && j < v2.size()) {
                queue.add(v1.get(i++));
                queue.add(v2.get(j++));
            }
            while (i < v1.size()) {
                queue.add(v1.get(i++));
            }
            while (j < v2.size()) {
                queue.add(v2.get(j++));
            }
        }

        public int next() {
            return queue.poll();
        }

        public boolean hasNext() {
            return queue.size() > 0;
        }
    }
}
