/*
 * Two elements of a binary search tree (BST) are swapped by mistake.
 * Recover the tree without changing its structure.
 */
package BinaryTree;

public class RecoverBinarySearchTree {

    private TreeNode firstElement = null;
    private TreeNode secondElement = null;
    private TreeNode lastElement = new TreeNode(Integer.MIN_VALUE);

    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }

        traverse(root.left);

        if (firstElement == null && root.val < lastElement.val) {
            firstElement = lastElement;
        }
        if (firstElement != null && root.val < lastElement.val) {
            secondElement = root;
        }

        lastElement = root;
        traverse(root.right);
    }

    public void recoverTree(TreeNode root) {
        // traverse and get two elements
        traverse(root);
        // swap
        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }
    /* 下解有bug
     */
    private TreeNode first;
    private TreeNode second;

    public void recoverTree2(TreeNode root) {
        if (root == null) {
            return;
        }
        recoverTree(root, new TreeNode(Integer.MIN_VALUE));
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    private void recoverTree(TreeNode root, TreeNode pre) {
        if (root == null) {
            return;
        }
        recoverTree(root.left, pre);

        if (first == null && root.val < pre.val) {
            first = pre;
        }
        if (first != null && root.val < pre.val) {
            second = root;
        }

        pre = root;
        recoverTree(root.right, pre);
    }

    public static void main(String[] args) {
    }
}
