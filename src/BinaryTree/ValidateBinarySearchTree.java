/*
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 * Assume a BST is defined as follows:
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
package BinaryTree;

public class ValidateBinarySearchTree {

    private int lastVal = Integer.MIN_VALUE;

    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (!isValidBST(root.left)) {
            return false;
        }
        if (root.val <= lastVal) {
            return false;
        }
        lastVal = root.val;
        if (!isValidBST(root.right)) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
    }
}
