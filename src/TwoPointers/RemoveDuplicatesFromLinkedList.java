package TwoPointers;

public class RemoveDuplicatesFromLinkedList {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        while (cur != null) {
            if (cur.val == pre.val) {
                cur = cur.next;
                pre.next = null;
            } else {
                pre.next = cur;
                pre = cur;
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
