package easy._0876_Middle_of_the_Linked_List.notes;

import utils.list_node.ListNode;

public class Solution_2_Fast_and_Slow_Pointer {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
