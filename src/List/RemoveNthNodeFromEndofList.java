package List;

/**
 * Given a linked list, remove the nth node from the end of list and return its head.
 * For example, Given linked list: 1->2->3->4->5, and n = 2.
 * After removing the second node from the end, the linked list becomes 1->2->3->5.
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 */
public class RemoveNthNodeFromEndofList {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode f = dummy;
        ListNode s = dummy;

        for (int i = 0; i < n; i++) {
            f = f.next;
        }

        while (f.next != null) {
            f = f.next;
            s = s.next;
        }

        s.next = s.next.next;

        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {

        if (head == null || head.next == null)
            return null;

        ListNode runner = head;

        for (int i = 0; i < n; i++) {
            if (runner.next == null) runner = head;
            else runner = runner.next;
        }

        if (runner == head)
            return head.next;

        ListNode pre = head;

        while (runner.next != null) {
            runner = runner.next;
            pre = pre.next;
        }

        pre.next = pre.next.next;

        return head;
    }
}
