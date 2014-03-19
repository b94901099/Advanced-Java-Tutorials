package List;

public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = new ListNode(l1.val);
                l1 = l1.next;
            } else {
                tail.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            tail = tail.next;
        }
        while (l1 != null) {
            tail.next = new ListNode(l1.val);
            l1 = l1.next;
            tail = tail.next;
        }
        while (l2 != null) {
            tail.next = new ListNode(l2.val);
            l2 = l2.next;
            tail = tail.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
    }
}
