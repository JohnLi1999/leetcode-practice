package easy._0141_Linked_List_Cycle.notes;

import utils.list_node.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution_1_HashTable {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> nodesSeen = new HashSet<>();
        while (head != null) {
            if (nodesSeen.contains(head)) {
                return true;
            }
            nodesSeen.add(head);
            head = head.next;
        }
        return false;
    }
}
