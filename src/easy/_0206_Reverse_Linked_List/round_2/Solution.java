package easy._0206_Reverse_Linked_List.round_2;

import utils.list_node.ListNode;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head;

//        head = ListNode.arrayToListNodes(new int[] {1,2,3,4,5});
//        ListNode.printList(s.reverseList_Iterative(head)); // 5 4 3 2 1
//        head = ListNode.arrayToListNodes(new int[] {1,2});
//        ListNode.printList(s.reverseList_Iterative(head)); // 2 1
//        head = ListNode.arrayToListNodes(new int[] {});
//        ListNode.printList(s.reverseList_Iterative(head)); // null

        head = ListNode.arrayToListNodes(new int[] {1,2,3,4,5});
        ListNode.printList(s.reverseList_Recursive(head)); // 5 4 3 2 1
        head = ListNode.arrayToListNodes(new int[] {1,2});
        ListNode.printList(s.reverseList_Recursive(head)); // 2 1
        head = ListNode.arrayToListNodes(new int[] {});
        ListNode.printList(s.reverseList_Recursive(head)); // null
    }

    /*  Time complexity: O(n)
        Space complexity: O(1)
     */
    public ListNode reverseList_Iterative(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode next;
        while (head.next != null) {
            next = head.next;
            head.next = next.next;
            next.next = dummy.next;
            dummy.next = next;
        }

        return dummy.next;
    }


    /*  Time complexity: O(n)
        Space complexity: O(n)
            stack used for recursive calls
    */
    public ListNode reverseList_Recursive(ListNode head) {
        if (head == null) {
            return null;
        }

        if (head.next == null) {
            return head;
        }

        ListNode trueHead = reverseList_Recursive(head.next);
        head.next.next = head;
        head.next = null;

        return trueHead;
    }
}
