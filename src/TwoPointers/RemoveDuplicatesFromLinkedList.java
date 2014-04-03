/*
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * For example,
 * Given 1->1->2, return 1->2.
 * Given 1->1->2->3->3, return 1->2->3.
 */
package TwoPointers;

public class RemoveDuplicatesFromLinkedList {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }

    public ListNode deleteDuplicates3(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode cur = head;

        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                do {
                    cur.next = cur.next.next;
                } while (cur.next != null && cur.next.val == cur.val);
            } else {
                cur = cur.next;
            }
        }
        return head;
    }

    public ListNode deleteDuplicates2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode G = new ListNode(head.val - 1);
        G.next = head;
        ListNode pre = G;
        ListNode cur = head;
        while (cur != null) {
            boolean isDup = false;
            while (cur.next != null && cur.val == cur.next.val) {
                isDup = true;
                cur = cur.next;
            }
            if (isDup) {
                cur = cur.next;
                continue;
            }
            pre.next = cur;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = cur;
        ListNode temp = G.next;
        return temp;
    }

    public static void main(String[] args) {
    }
}
