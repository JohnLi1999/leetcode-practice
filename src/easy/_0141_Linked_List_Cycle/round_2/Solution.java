package easy._0141_Linked_List_Cycle.round_2;

import utils.list_node.ListNode;

public class Solution {
    static ListNode head;
    static void formNodeList(int[] arr, int pos) {
        head = ListNode.arrayToListNodes(arr);
        if (pos == -1) {
            return;
        }
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        ListNode curr = head;
        int count = 0;
        while (count < pos) {
            curr = curr.next;
            count += 1;
        }
        tail.next = curr;
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        formNodeList(new int[] {3,2,0,-4}, 1);
        System.out.println(s.hasCycle(head)); // true
        formNodeList(new int[] {1,2}, 0);
        System.out.println(s.hasCycle(head)); // true
        formNodeList(new int[] {1}, -1);
        System.out.println(s.hasCycle(head)); // false
    }

    /*  Slow-fast pointers
        Time complexity: O(n)
        Space complexity: O(1)
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
