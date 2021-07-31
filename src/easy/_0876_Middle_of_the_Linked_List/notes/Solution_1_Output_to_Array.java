package easy._0876_Middle_of_the_Linked_List.notes;

import utils.list_node.ListNode;

public class Solution_1_Output_to_Array {
    public ListNode middleNode(ListNode head) {
        ListNode[] A = new ListNode[100];
        int t = 0;
        while (head != null) {
            A[t++] = head;
            head = head.next;
        }
        return A[t / 2];
    }
}