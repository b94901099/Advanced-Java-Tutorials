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

import java.util.List;

//  Definition for singly-linked list.
public class ReverseALinkedListII {

    public ListNode reverseBetweenII(ListNode head, int m, int n) {
        if (head == null || head.next == null)
            return head;

        if (m == n)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode right = dummy;

        for (int i = 0; i < n - m && right != null; i++) {
            right = right.next;
        }

        return dummy.next;
    }

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


    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode pre = null;

        while (head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }

        System.out.printf("reverse Head = " + pre.val);
        return pre;
    }

    public ListNode reverseBetween2(ListNode head, int m, int n) {

        if (head == null || head.next == null)
            return head;

        if (m == n)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode left = dummy;
        ListNode right = dummy;
        ListNode preCon = dummy;

        for (int i = 0; i < n - m; i++) {
            right = right.next;
        }

        for (int i = 0; i < m; i++) {
            preCon = left;
            left = left.next;
            right = right.next;
        }

        ListNode conPost = right.next;
        right.next = null;
        preCon.next = null;

        System.out.println("preCon = " + preCon.val);
        System.out.println("left = " + left.val);
        System.out.println("right = " + right.val);

        ListNode reverseHead = reverse(left);
        preCon.next = reverseHead;
        left.next = conPost;

        return dummy.next;
    }

    public static void main(String[] args) {
        ReverseALinkedListII r = new ReverseALinkedListII();
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        n1.next = n2;

        ListNode result = r.reverseBetween2(n1, 1, 2);

        System.out.println();
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
    }

}