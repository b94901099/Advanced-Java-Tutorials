/*
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * For example, this binary tree is symmetric:
 * 
 *    1
 *   / \
 *  2   2
 * / \ / \
 *3  4 4  3
 * But the following is not:
 *    1
 *   / \
 *  2   2
 *   \   \
 *   3    3
 * Note:
 * Bonus points if you could solve it both recursively and iteratively.
 */
package BinaryTree;

import java.util.*;

public class SymmetricTree {

    //这题难的是iterative的解法，基本做法是BFS，一层一层看是否对称，然后用两个queue来存储结点。
    public boolean isSymmetricIteration(TreeNode root) {
        if (root == null) {
            return true;
        }
        LinkedList<TreeNode> left = new LinkedList<TreeNode>();
        LinkedList<TreeNode> right = new LinkedList<TreeNode>();
        left.add(root.left);
        right.add(root.right);
        while (!left.isEmpty() && !right.isEmpty()) {
            TreeNode temp1 = left.poll();
            TreeNode temp2 = right.poll();
            if (temp1 == null && temp2 != null || temp1 != null && temp2 == null) {
                return false;
            }
            if (temp1 != null) {
                if (temp1.val != temp2.val) {
                    return false;
                }
                left.add(temp1.left);
                left.add(temp1.right);
                right.add(temp2.right);
                right.add(temp2.left);
            }
        }
        return true;
    }

    // Recursion
    public boolean isSymmetric(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return true;
        }
        return isSym(root.left, root.right);
    }

    private boolean isSym(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return isSym(left.left, right.right) && isSym(left.right, right.left);
    }

    public static void main(String[] args) {
    }
}
