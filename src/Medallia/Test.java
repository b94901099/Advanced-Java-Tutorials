package Medallia;

public class Test {

    public static void main(String[] args) {
        LinkedListNode n1 = new LinkedListNode(0);
        LinkedListNode n2 = new LinkedListNode(1);
        LinkedListNode n3 = new LinkedListNode(2);
        n1.next = n2;
        n2.next = n3;
        System.out.println(size(n1));
    }
    
    static int size(LinkedListNode list) {
        int i = 0;
        LinkedListNode node = list;
        while (node != null) {
            node = node.next;
            i++;
        }
        return i;
    }
    public static class LinkedListNode {

        int val;
        LinkedListNode next;

        LinkedListNode(int node_value) {
            val = node_value;
            next = null;
        }
    }
}
