/*
 * Given a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct numbers from the original list.
 * For example,
 * Given 1->2->3->3->4->4->5, return 1->2->5.
 * Given 1->1->1->2->3, return 2->3.
 */
package TwoPointers;
public class RemoveDuplicatesfromSortedListII {
    
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        
        ListNode pre = dummy;
        ListNode cur = head;
        
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                int tmp = cur.val;
                do {
                    pre.next = cur.next;
                    cur = cur.next;
                } while (cur != null && cur.val == tmp);
            } else {
                pre = pre.next;
                cur = cur.next;
            }
        }
        
        return dummy.next;
    }

    public static void main(String[] args) {
    }
}
