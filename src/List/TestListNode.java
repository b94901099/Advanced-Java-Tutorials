package List;

public class TestListNode {

    public void go(ListNode node) {
        while (node != null) {
            node = node.next;
        }
    }

    public static void main(String[] args) {
        ListNode n0 = new ListNode(0);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n0.next = n1;
        n1.next = n2;
        n2.next = n3;

        TestListNode t = new TestListNode();
        t.go(n1);

        System.out.println(n3.val);
    }
}
