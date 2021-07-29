package medium._0148_Sort_List;

import utils.list_node.ListNode;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head;

        head = ListNode.arrayToListNodes(new int[] {4,2,1,3});
        ListNode.printList(s.sortList(head));

        head = ListNode.arrayToListNodes(new int[] {-1,5,3,4,0});
        ListNode.printList(s.sortList(head));

        head = ListNode.arrayToListNodes(new int[] {});
        ListNode.printList(s.sortList(head));
    }

    /*  Merge Sort - Better
        Time complexity: O(n*log(n))
            Master Theorem Case 2
                T(n) = a * T(n / b) + f(n)
                    a = 2, b = 2, log_a_(b) = 1 => f(n) in O(n)
                => T(n) in Theta(n*log(n))
        Space complexity: O(log(n))
            Stack used for Recursion
    */
    public ListNode sortList(ListNode head) {
        // Empty list or only has one node
        if (head == null || head.next == null) {
            return head;
        }

        // 0 1 2 3 4
        // 0 1
        // 0 1 2 3
        // 0 1 2
        // 0 1 2 3 4 5 6

        // Get middle element using fast and slow pointers
        ListNode mid = head; // slow pointer
        ListNode tail = head.next; // fast pointer
        ListNode rightStart = null;
        while (mid != null) {
            if (tail.next == null || tail.next.next == null) {
                rightStart = mid.next;
                mid.next = null;
                break;
            }
            mid = mid.next;
            tail = tail.next.next;
        }

        // Divide and Conquer
        ListNode left = sortList(head);
        ListNode right = sortList(rightStart);
        return merge(left, right);
    }

    public ListNode merge(ListNode leftHead, ListNode rightHead) {
        ListNode head = new ListNode();
        ListNode current = head;

        while (leftHead != null && rightHead != null) {
            if (leftHead.val < rightHead.val) {
                current.next = leftHead;
                leftHead = leftHead.next;
            } else {
                current.next = rightHead;
                rightHead = rightHead.next;
            }
            current = current.next;
        }

        if (leftHead == null) {
            current.next = rightHead;
        } else {
            current.next = leftHead;
        }

        return head.next;
    }

//    /*  Merge Sort - My Way
//        Time complexity: O(n*log(n))
//            Master Theorem Case 2
//                T(n) = a * T(n / b) + f(n)
//                    a = 2, b = 2, log_a_(b) = 1 => f(n) in O(n)
//                => T(n) in Theta(n*log(n))
//        Space complexity: O(log(n))
//            Stack used for Recursion
//     */
//    public ListNode sortList(ListNode head) {
//        // Empty list or only has one node
//        if (head == null || head.next == null) {
//            return head;
//        }
//
//        // Get length of head
//        ListNode current = head;
//        int length = 0;
//        while (current != null) {
//            length++;
//            current = current.next;
//        }
//
//        // Find the middle point
//        int half = length / 2;
//        ListNode leftEnd = head;
//        ListNode rightStart;
//        int count = 1;
//        while (count < half) {
//            count++;
//            leftEnd = leftEnd.next;
//        }
//        rightStart = leftEnd.next;
//        leftEnd.next = null;
//
//        // Divide and Conquer
//        ListNode left = sortList(head);
//        ListNode right = sortList(rightStart);
//        return merge(left, right);
//    }
//
//    public ListNode merge(ListNode leftHead, ListNode rightHead) {
//        ListNode head = new ListNode();
//        ListNode current = head;
//
//        while (leftHead != null && rightHead != null) {
//            if (leftHead.val < rightHead.val) {
//                current.next = leftHead;
//                leftHead = leftHead.next;
//            } else {
//                current.next = rightHead;
//                rightHead = rightHead.next;
//            }
//            current = current.next;
//        }
//
//        if (leftHead == null) {
//            current.next = rightHead;
//        } else {
//            current.next = leftHead;
//        }
//
//        return head.next;
//    }

//    /*  Selection Sort - Time Limit Exceeded
//        Time complexity: O(n^2)
//        Space complexity: O(1)
//     */
//    public ListNode sortList(ListNode head) {
//        // Dummy head
//        ListNode root = new ListNode(-1);
//        ListNode sortedTail = root;
//
//        /*  case 1: min is head
//                sortedTail -> head -> ...
//            case 2: min is not head
//                sortedTail -> head -> ... -> prevMinNode -> minNode -> ...
//
//         */
//        ListNode startHead = head;
//        ListNode current;
//        ListNode minNode;
//        ListNode prevMinNode;
//
//        while (startHead != null) {
//            current = startHead;
//            minNode = null;
//            prevMinNode = null;
//
//            int min = current.val;
//            while (current.next != null) {
//                if (current.next.val < min) {
//                    min = current.next.val;
//                    minNode = current.next;
//                    prevMinNode = current;
//                }
//                current = current.next;
//            }
//            if (minNode != null) {
//                prevMinNode.next = minNode.next;
//                sortedTail.next = minNode;
//                minNode.next = startHead;
//                sortedTail = sortedTail.next;
//                startHead = sortedTail.next;
//            } else {
//                sortedTail.next = startHead;
//                sortedTail = sortedTail.next;
//                startHead = startHead.next;
//            }
//        }
//
//        return root.next;
//    }
}
