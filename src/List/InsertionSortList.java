package List;

public class InsertionSortList {

    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(-1);

        dummy.next = head;
        ListNode cur = head;

        while (cur != null && cur.next != null) {
            if (cur.val > cur.next.val) {
                // find node which is not in order
                ListNode toInsert = cur.next;
                ListNode pre = dummy;
                // find position for toInsert
                while (pre.next.val < toInsert.val) {
                    pre = pre.next;
                }
                ListNode temp = pre.next;
                pre.next = toInsert;

                cur.next = toInsert.next;
                toInsert.next = temp;
            } else {
                // node is in order
                cur = cur.next;
            }
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(3);
        ListNode n5 = new ListNode(4);
        ListNode n6 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        InsertionSortList i = new InsertionSortList();
        n1 = i.insertionSortList(n1);
        printList(n1);
    }

    public static void printList(ListNode x) {
        if (x != null) {
            System.out.print(x.val + " ");
            while (x.next != null) {
                System.out.print(x.next.val + " ");
                x = x.next;
            }
            System.out.println();
        }
    }
}
