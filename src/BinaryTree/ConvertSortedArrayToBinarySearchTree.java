package BinaryTree;

public class ConvertSortedArrayToBinarySearchTree {

    private int current = 0;
    private int[] nums = null;

    public TreeNode sortedArrayToBST(int[] num) {
        nums = num;
        return helper(num.length);
    }

    private TreeNode helper(int size) {
        if (size <= 0) {
            return null;
        }
        TreeNode left = helper(size / 2);
        TreeNode root = new TreeNode(nums[current]);
        current++;
        TreeNode right = helper(size - 1 - size / 2);
        root.left = left;
        root.right = right;
        return root;
    }

    private class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static void main(String[] args) {
    }
}
