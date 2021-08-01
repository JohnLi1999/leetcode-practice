package medium._0092_Reverse_Linked_List_II;

import utils.list_node.ListNode;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head;

        head = ListNode.arrayToListNodes(new int[] {1,2,3,4,5});
        ListNode.printList(s.reverseBetween(head, 2, 4)); // 1 4 3 2 5
        head = ListNode.arrayToListNodes(new int[] {5});
        ListNode.printList(s.reverseBetween(head, 1, 1)); // 5
    }

    /*  Time complexity: O(n)
        Space complexity: O(1)
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left == right) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode before = dummy;
        int pos = 1;
        while (pos < left) {
            before = before.next;
            pos += 1;
        }

        int reverseLength = right - left;
        int count = 0;
        ListNode curr = before.next;
        while (count < reverseLength) {
            /* before -> 1 -> 2 -> 3
               before    1 -> 2 -> 3
                 |------------^
                         |---------v
               before    1 -> 2 -> 3
                 |------------^
                 before -> 2 -> 1 -> 3
             */
            ListNode next = before.next;
            before.next = curr.next;
            curr.next = curr.next.next;
            before.next.next = next;
            count += 1;
        }

        return dummy.next;
    }
}
