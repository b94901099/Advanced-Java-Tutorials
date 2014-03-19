/*
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 */
package List;

public class RotateList {

    public ListNode rotateRight(ListNode head, int n) {
        if (head == null || n == 0) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode n1 = head;
        ListNode n2 = head;
        while (n > 0) {
            n2 = n2.next;
            n--;
            if (n2 == null) {
                n2 = head; // n過大會循環, 從頭開始
            }
        }
        
        if(n1==n2) return n1;

        while (n2.next != null) {
            n1 = n1.next;
            n2 = n2.next;
        }

        n2.next = dummy.next;
        dummy.next = n1.next;
        n1.next = null;

        return dummy.next;
    }

    public static void main(String[] args) {
    }
}
