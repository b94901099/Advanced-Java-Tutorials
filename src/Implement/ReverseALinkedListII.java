package Implement;

//  Definition for singly-linked list.
class ListNode {

    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

public class ReverseALinkedListII {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null) {
            return head;
        }
        if (m == n) {
            return head;
        }
        
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