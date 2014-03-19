/*
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * Output: 7 -> 0 -> 8
 */
package List;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int tmp = 0;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val + tmp;
            tmp = sum / 10;
            sum = sum % 10;
            tail.next = new ListNode(sum);
            tail = tail.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int sum = l1.val + tmp;
            tmp = sum / 10;
            sum = sum % 10;
            tail.next = new ListNode(sum);
            tail = tail.next;
            l1 = l1.next;
        }

        while (l2 != null) {
            int sum = l2.val + tmp;
            tmp = sum / 10;
            sum = sum % 10;
            tail.next = new ListNode(sum);
            tail = tail.next;
            l2 = l2.next;
        }

        if (tmp != 0) {
            tail.next = new ListNode(tmp);
        }

        return dummy.next;
    }

    public static void main(String[] args) {
    }
}
