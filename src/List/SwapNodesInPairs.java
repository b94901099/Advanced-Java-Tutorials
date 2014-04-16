package List;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * Your algorithm should use only constant space.
 * You may not modify the values in the list, only nodes itself can be changed.
 */
public class SwapNodesInPairs {

    public ListNode swapPairs(ListNode head) {
        ListNode helper = new ListNode(0);
        helper.next = head;
        ListNode n1 = helper, n2 = head;

        while (n2 != null && n2.next != null) {
            ListNode temp = n2.next.next;
            n2.next.next = n1.next;
            n1.next = n2.next;
            n2.next = temp;
            n1 = n2;
            n2 = n1.next;
        }

        return helper.next;
    }

    public ListNode swapPairs2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = head;
        ListNode cur = head.next;
        ListNode l2_dummy = new ListNode(0);
        ListNode l2_tail = l2_dummy;

        while (cur != null) {
            ListNode tmp = cur.next;
            l2_tail.next = cur;
            l2_tail = l2_tail.next;
            cur.next = null;
            pre.next = tmp;
            if (tmp != null && tmp.next != null) {
                cur = tmp.next;
                pre = tmp;
            } else {
                cur = null;
            }
        }

        ListNode head2 = l2_dummy.next;
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int i = 0;
        while (head != null && head2 != null) {
            if (i++ % 2 == 0) {
                tail.next = head2;
                head2 = head2.next;
                tail = tail.next;
            } else {
                tail.next = head;
                head = head.next;
                tail = tail.next;
            }
        }
        if (head != null) {
            tail.next = head;
        }
        if (head2 != null) {
            tail.next = head2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
    }
}
