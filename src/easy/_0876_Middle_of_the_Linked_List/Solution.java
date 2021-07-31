package easy._0876_Middle_of_the_Linked_List;

import utils.list_node.ListNode;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        ListNode head;

        head = ListNode.arrayToListNodes(new int[] {1,2,3,4,5});
        ListNode.printList(s.middleNode(head)); // 3 4 5
        head = ListNode.arrayToListNodes(new int[] {1,2,3,4,5,6});
        ListNode.printList(s.middleNode(head)); // 4 5 6
        head = ListNode.arrayToListNodes(new int[] {});
        ListNode.printList(s.middleNode(head)); // null
        head = ListNode.arrayToListNodes(new int[] {1});
        ListNode.printList(s.middleNode(head)); // 1
        head = ListNode.arrayToListNodes(new int[] {1,2});
        ListNode.printList(s.middleNode(head)); // 2
        head = ListNode.arrayToListNodes(new int[] {1,2,3});
        ListNode.printList(s.middleNode(head)); // 2 3
        head = ListNode.arrayToListNodes(new int[] {1,2,3,4});
        ListNode.printList(s.middleNode(head)); // 3 4
    }

    /*  Fast-slow pointers
        Time complexity: O(n)
            It takes n/2 time
        Space complexity: O(1)
     */
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
