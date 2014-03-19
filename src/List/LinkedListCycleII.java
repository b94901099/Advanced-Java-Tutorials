/*
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * Follow up:
 * Can you solve it without using extra space?
 */
package List;

public class LinkedListCycleII {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return findEntry(slow, head);
            }
        }
        return null;
    }

    private ListNode findEntry(ListNode node, ListNode head) {
        ListNode out = head;
        while (out != node) {
            out = out.next;
            node = node.next;
        }
        return out;
    }

    public static void main(String[] args) {
    }
}
