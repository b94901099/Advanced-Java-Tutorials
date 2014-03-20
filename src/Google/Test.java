package Google;

public class Test {

    /*
     *          20
     *         /  \
     *        8    22
     *       / \
     *      4  12
     *        /  \
     *       10  14
     */
    public static void testTree() {
        TreeNode n0 = new TreeNode(20);
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(22);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(12);
        TreeNode n5 = new TreeNode(10);
        TreeNode n6 = new TreeNode(14);
        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n1.right = n4;
        n4.left = n5;
        n4.right = n6;

        inorderTraversal(n0);
        System.out.println("\n-----------------------------------");
        InorderSuccessorInBinarySearchTree i = new InorderSuccessorInBinarySearchTree();
        TreeNode successor1 = i.inorderSuccessSol1(n0, n1);
        System.out.println(successor1.val);
    }

    public static void inorderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left);
        System.out.print(root.val + ", ");
        inorderTraversal(root.right);
    }

    public static void main(String[] args) {
        testTree();
    }
}
