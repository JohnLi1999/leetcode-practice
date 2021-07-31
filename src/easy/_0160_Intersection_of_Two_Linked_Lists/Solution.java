package easy._0160_Intersection_of_Two_Linked_Lists;

import utils.list_node.ListNode;

import java.util.ArrayList;
import java.util.HashSet;

public class Solution {
    static ListNode shared;
    static ListNode headA;
    static ListNode headB;
    public static void formTestLists(int[] a, int[] b, int[] share) {
        headA = ListNode.arrayToListNodes(a);
        headB = ListNode.arrayToListNodes(b);
        shared = ListNode.arrayToListNodes(share);
        if (headA == null) {
            headA = shared;
        } else {
            ListNode currA = headA;
            while (currA.next != null) {
                currA = currA.next;
            }
            currA.next = shared;
        }

        if (headB == null) {
            headB = shared;
        } else {
            ListNode currB = headB;
            while (currB.next != null) {
                currB = currB.next;
            }
            currB.next = shared;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        formTestLists(new int[] {4,1}, new int[] {5,6,1}, new int[] {8,4,5});
        ListNode.printList(s.getIntersectionNode(headA, headB)); // 8 4 5
        formTestLists(new int[] {1,9}, new int[] {3}, new int[] {2,4});
        ListNode.printList(s.getIntersectionNode(headA, headB)); // 2 4
        formTestLists(new int[] {2,6,4}, new int[] {1,5}, new int[] {});
        ListNode.printList(s.getIntersectionNode(headA, headB)); // null
        formTestLists(new int[] {}, new int[] {1,2}, new int[] {2});
        ListNode.printList(s.getIntersectionNode(headA, headB)); // 2
        formTestLists(new int[] {1}, new int[] {}, new int[] {2});
        ListNode.printList(s.getIntersectionNode(headA, headB)); // 2
        formTestLists(new int[] {}, new int[] {}, new int[] {2});
        ListNode.printList(s.getIntersectionNode(headA, headB)); // 2
    }

    /*  Two pointers Improved Version - Derived From Sample Solution
        Time complexity: O(m + n)
            m -> headA length
            n -> headB length
        Space complexity: O(1)
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        /*  Use one pointer to traverse list A then list B
            Use another pointer to traverse list B then list A
            When two points meet, it is the intersection.
                    x
                --------     y
                z       -----------
            ------------
            list A is [x + y]
            list B is [z + y]
            y is the intersection
            The two pointers will always meet since
                x + y + z = z + y + x
            Even if the two lists does not intersect, the two pointers will both
            become null at the end, which is case that the shared y is an empty list
         */

        ListNode pointerA = headA;
        ListNode pointerB = headB;

        while (pointerA != pointerB) {
            pointerA = pointerA == null ? headB : pointerA.next;
            pointerB = pointerB == null ? headA : pointerB.next;
        }

        return pointerA;
    }

//    /*  Two pointers - Derived From Sample Solution
//        Time complexity: O(m + n)
//            m -> headA length
//            n -> headB length
//        Space complexity: O(1)
//     */
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        if (headA == null || headB == null) {
//            return null;
//        }
//
//        int lenA = getLen(headA);
//        int lenB = getLen(headB);
//
//        if (lenA >= lenB) {
//            return getIntersection(headB, headA, lenA - lenB);
//        } else {
//            return getIntersection(headA, headB, lenB - lenA);
//        }
//    }
//
//    public ListNode getIntersection(ListNode shorter, ListNode longer, int diff) {
//        ListNode startLonger = longer;
//        int count = 1;
//        while (count <= diff) {
//            startLonger = startLonger.next;
//            count++;
//        }
//
//        ListNode currLonger = startLonger;
//        ListNode currShorter = shorter;
//        while (currLonger != null) {
//            if (currLonger == currShorter) {
//                return currShorter;
//            }
//            currLonger = currLonger.next;
//            currShorter = currShorter.next;
//        }
//
//        return null;
//    }
//
//    public int getLen(ListNode head) {
//        int len = 0;
//        ListNode current = head;
//        while (current != null) {
//            len++;
//            current = current.next;
//        }
//        return len;
//    }

//    /*  Using HashSet - Derived From Sample Solution
//        Time complexity: O(m + n)
//            m -> headA length
//            n -> headB length
//        Space complexity: O(m)
//
//        Note: the main function might return false test result
//              since the formTestLists function is not perfect
//     */
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        HashSet<ListNode> set = new HashSet<>();
//
//        ListNode curr = headA;
//        while (curr != null) {
//            set.add(curr);
//            curr = curr.next;
//        }
//
//        curr = headB;
//        while (curr != null) {
//            if (set.contains(curr)) {
//                return curr;
//            }
//            curr = curr.next;
//        }
//
//        return null;
//    }

//    /*  Save nodes using extra space
//        Time complexity: O(m + n)
//            m -> headA length
//            n -> headB length
//        Space complexity: O(m + n)
//     */
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        if (headA == null || headB == null) {
//            return null;
//        }
//
//        ArrayList<ListNode> listA = new ArrayList<>();
//        ArrayList<ListNode> listB = new ArrayList<>();
//
//        ListNode currA = headA;
//        while (currA != null) {
//            listA.add(currA);
//            currA = currA.next;
//        }
//        ListNode currB = headB;
//        while (currB != null) {
//            listB.add(currB);
//            currB = currB.next;
//        }
//
//        int i = listA.size() - 1;
//        int j = listB.size() - 1;
//
//        while (i >= 0 && j >= 0) {
//            if (listA.get(i) == listB.get(j)) {
//                if (i == 0) {
//                    return listA.get(i);
//                }
//                if (j == 0) {
//                    return listB.get(j);
//                }
//                i--;
//                j--;
//            } else {
//                if (i == listA.size() - 1 || j == listB.size() - 1) {
//                    break;
//                } else {
//                    return listA.get(i + 1);
//                }
//            }
//        }
//
//        return null;
//    }

//    /*  Nested loop
//        Time complexity: O(m * n)
//            m -> headA length
//            n -> headB length
//        Space complexity: O(1)
//     */
//    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
//        ListNode currA = headA;
//        while (currA != null) {
//            ListNode currB = headB;
//            while (currB != null) {
//                if (currA == currB) {
//                    return currA;
//                }
//                currB = currB.next;
//            }
//            currA = currA.next;
//        }
//        return null;
//    }
}
