/*
 * Reverse a linked list from position m to n. Do it in-place and in one-pass.

For example:
Given 1->2->3->4->5->NULL, m = 2 and n = 4,

return 1->4->3->2->5->NULL.

Note:
Given m, n satisfy the following condition:
1 ≤ m ≤ n ≤ length of list.
 */


package List;

//  Definition for singly-linked list.
public class ReverseALinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null)
            return head;

        if (m == n)
            return head;


        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt = null;

        for (int i = 1; i < m; i++) {
            pre = cur;
            cur = cur.next;
        }

        ListNode conToPost = cur;
        ListNode preCon = pre;

        while (m++ <= n) {
            nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }

        conToPost.next = cur;
        if (preCon == null) {
            head = pre;
        } else {
            preCon.next = pre;
        }
        return head;
    }
}