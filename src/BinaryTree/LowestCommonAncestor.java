/*
 * Lowest Common Ancestor of a Binary Search Tree (BST)
 * Given a binary search tree (BST), find the lowest common ancestor of 
 * two given nodes in the BST.
 * Hint:
 * A top-down walk from the root of the tree is enough.
 * Solution:
 * There’s only three cases you need to consider.
 * 1. Both nodes are to the left of the tree.
 * 2. Both nodes are to the right of the tree.
 * 3. One node is on the left while the other is on the right.
 */
package BinaryTree;

public class LowestCommonAncestor {

    public static TreeNode LCA(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) {
            return null;
        }
        if (Math.max(p.val, q.val) < root.val) {
            return LCA(root.left, p, q);
        }
        if (Math.min(p.val, q.val) > root.val) {
            return LCA(root.right, p, q);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode node0 = new TreeNode(6);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(9);
        TreeNode node7 = new TreeNode(3);
        TreeNode node8 = new TreeNode(5);
        node0.left = node1;
        node0.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
        node4.left = node7;
        node4.right = node8;
        TreeNode lca = LCA(node0, node3, node8);
        System.out.println(lca.val);
    }
}
