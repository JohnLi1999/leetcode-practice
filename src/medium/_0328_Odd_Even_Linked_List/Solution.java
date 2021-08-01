package medium._0328_Odd_Even_Linked_List;

import utils.list_node.ListNode;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head;
        head = ListNode.arrayToListNodes(new int[] {1,2,3,4,5});
        ListNode.printList(s.oddEvenList(head)); // 1,3,5,2,4
        head = ListNode.arrayToListNodes(new int[] {2,1,3,5,6,4,7});
        ListNode.printList(s.oddEvenList(head)); // 2,3,6,7,1,5,4
    }

    /*  Time complexity: O(n)
        Space complexity: O(1)
     */
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode oddEnd = head;
        ListNode evenStart = head.next;
        ListNode current = head.next;
        boolean meetEven = true;

        while (current != null) {
            if (meetEven) {
                if (current.next == null) {
                    break;
                }

                ListNode next = current.next;
                oddEnd.next = next;
                current.next = next.next;
                next.next = evenStart;
                oddEnd = oddEnd.next;
                meetEven = false;
            } else {
                current = current.next;
                meetEven = true;
            }
        }

        return head;
    }
}
