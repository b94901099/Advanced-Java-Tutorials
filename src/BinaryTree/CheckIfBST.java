/*
 * 確認一棵樹是不是 BST
 */
package BinaryTree;

public class CheckIfBST {

    private int pre = Integer.MIN_VALUE;

    public boolean checkBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        boolean left = checkBST(root.left);
        if (pre >= root.val) {
            return false;
        }
        pre = root.val;
        boolean right = checkBST(root.right);
        return left && right;
    }

    public static void main(String[] args) {
        
    }
}
