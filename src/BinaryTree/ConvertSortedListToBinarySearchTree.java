package BinaryTree;

public class ConvertSortedListToBinarySearchTree {

    public TreeNode sortedListToBST(ListNode head) {
        int size;
        current = head;
        size = getListLength(head);
        return sortedListToBSTHelper(size);
    }
    ListNode current = null;

    private TreeNode sortedListToBSTHelper(int size) {
        if (size <= 0) {
            return null;
        }
        TreeNode left = sortedListToBSTHelper(size / 2);
        TreeNode root = new TreeNode(current.val);
        current = current.next;
        TreeNode right = sortedListToBSTHelper(size - 1 - size / 2);
        root.left = left;
        root.right = right;

        return root;
    }

    private int getListLength(ListNode head) {
        int length = 0;
        ListNode current = head;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    public static void main(String[] args) {
    }

    private class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
