package Medallia;

import java.util.Arrays;
import java.util.List;

public class Q1 {

    static LinkedListNode reverse(LinkedListNode list, int k) {
        if (list == null || k < 1) {
            return list;
        }
        LinkedListNode reversedList = reverse(list);
        LinkedListNode dummy = new LinkedListNode(0);
        dummy.next = reversedList;

        LinkedListNode pre = null;
        LinkedListNode cur = dummy.next;
        LinkedListNode nxt = null;
        LinkedListNode connectToPost = dummy;
        LinkedListNode temp = null;

        while (cur != null) {
            temp = cur;
            for (int i = 0; i < k && cur != null; i++) {
                nxt = cur.next;
                cur.next = pre;
                pre = cur;
                cur = nxt;
            }
            connectToPost.next = pre;
            pre = null;
            connectToPost = temp;
        }
        return dummy.next;
    }

    private static LinkedListNode reverse(LinkedListNode list) {
        LinkedListNode pre = null;
        LinkedListNode cur = list;
        while (cur != null) {
            LinkedListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }


    /**
     * @return {@link LinkedListNode} head of the linked list containing the
     * given values
     */
    static LinkedListNode createFromList(List<Integer> list) {
        LinkedListNode head = null;
        LinkedListNode node = null;
        for (int val : list) {
            LinkedListNode prev = node;
            node = new LinkedListNode(val);
            if (head == null) {
                head = node;
            }

            if (prev != null) {
                prev.next = node;
            }
        }

        return head;
    }

    /**
     * Asserts that two linked lists are equal
     */
    static void assertLinkedListsEqual(LinkedListNode root1, LinkedListNode root2) {
        LinkedListNode node1 = root1;
        LinkedListNode node2 = root2;
        while (node1 != null && node2 != null) {
            if (node1.val != node2.val) {
                throw new AssertionError("Expected " + printLinkedList(root1) + " but found " + printLinkedList(root2));
            }
            node1 = node1.next;
            node2 = node2.next;
        }

        if (node1 == null ^ node2 == null) {
            throw new AssertionError("Expected " + printLinkedList(root1) + " but found " + printLinkedList(root2));
        }
    }

    /**
     * Prints the linked lists
     */
    static String printLinkedList(LinkedListNode head) {
        StringBuilder sb = new StringBuilder();
        LinkedListNode node = head;
        while (node != null) {
            if (node != head) {
                sb.append(',');
            }
            sb.append(node.val);
            node = node.next;
        }
        return sb.toString();
    }

    private static void basicTest() {
        LinkedListNode testLinkedList = createFromList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
        LinkedListNode reversed = reverse(testLinkedList, 2);
        LinkedListNode expected = createFromList(Arrays.asList(8, 9, 6, 7, 4, 5, 2, 3, 1));
        assertLinkedListsEqual(expected, reversed);
    }
    
    
    public static void main(String[] args) {
        basicTest();
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
