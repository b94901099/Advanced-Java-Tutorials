package List;
//Sort a linked list in O(n log n) time using constant space complexity.
public class MergeSortList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = findMid(head);
        ListNode right = sortList(mid.next);
        mid.next = null;
        ListNode left = sortList(head);
        return merge(left, right);
    }

    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        ListNode tail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.val > head2.val) {
                tail.next = head2;
                head2 = head2.next;
            } else {
                tail.next = head1;
                head1 = head1.next;
            }
            tail = tail.next;
        }
        if (head1 != null) {
            tail.next = head1;
        } else {
            tail.next = head2;
        }
        return dummy.next;
    }

    private ListNode findMid(ListNode head) {
        ListNode slowPoint = head;
        ListNode fastPoint = head;
        while (fastPoint != null && fastPoint.next != null) {
            slowPoint = slowPoint.next;
            fastPoint = fastPoint.next.next;
        }
        return slowPoint;
    }

    public static void main(String[] args) {
    }
}
