/*
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * Note: You may assume that duplicates do not exist in the tree.
 */
package BinaryTree;

public class ConstructBinaryTreefromPreorderandInorderTraversal {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode root = buildTreeHelper(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1);
        return root;
    }

    private TreeNode buildTreeHelper(int[] inorder, int instart, int inend,
            int[] preorder, int prestart, int preend) {
        if (instart > inend) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[prestart]);
        int position = findPosition(inorder, instart, inend, preorder[prestart]);
        int leftNum = position - instart;
        root.left = buildTreeHelper(inorder, instart, position - 1, preorder, prestart + 1, prestart + leftNum);
        root.right = buildTreeHelper(inorder, position + 1, inend, preorder, prestart + leftNum + 1, preend);
        return root;
    }

    private int findPosition(int[] inorder, int instart, int inend, int key) {
        for (int i = instart; i <= inend; i++) {
            if (key == inorder[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}
