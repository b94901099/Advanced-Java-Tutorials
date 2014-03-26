package BinaryTree;

import java.util.*;

public class Test {

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
        System.out.println("Preorder Traversal");
        BinaryTreePreorderTraversal b = new BinaryTreePreorderTraversal();
        for (Integer i : b.preorderTraversal(node0)) {
            System.out.print(i + ", ");
        }
        System.out.println("Inorder Traversal");
        
        for (Integer i : new BinaryTreeInorderTraversal().inorderTraversal(node0)) {
            System.out.print(i + ", ");
        }
        System.out.println("");
        
        BinaryTreeLevelOrderTraversalII bt = new BinaryTreeLevelOrderTraversalII();
        for (ArrayList<Integer> list : bt.levelOrderBottom(node0)) {
            for (Integer i : list) {
                System.out.print(i + ", ");
            }
            System.out.println("");
        }

        //checkBST
        System.out.println("Check if bst");
        CheckIfBST c = new CheckIfBST();
        System.out.println(c.checkBST(node0));

    }
}
