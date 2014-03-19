//就是Preorder + 換點
package BinaryTree;

public class FlattenBinaryTreetoLinkedList {

    private TreeNode lastNode = null;

    public void flatten(TreeNode root) {
        if (root == null) {
            return;
        }

        if (lastNode != null) {
            lastNode.left = null;
            lastNode.right = root;
        }

        lastNode = root;
        TreeNode right = root.right;
        flatten(root.left);
        flatten(right);
    }

    public static void main(String[] args) {
    }
}
