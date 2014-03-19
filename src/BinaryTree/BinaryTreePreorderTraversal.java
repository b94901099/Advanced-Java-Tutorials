package BinaryTree;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreePreorderTraversal {

    //Stack 要先放 right 再 left
    public ArrayList<Integer> PreorderVisitIterative(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();

        if (root == null) {
            return result;
        }

        Stack<TreeNode> tmpStack = new Stack<TreeNode>();
        tmpStack.push(root);
        
        while (!tmpStack.empty()) {
            TreeNode current = tmpStack.pop();
            if (current.right != null) {
                tmpStack.push(current.right);
            }
            if (current.left != null) {
                tmpStack.push(current.left);
            }
            result.add(current.val);
        }
        return result;
    }

    public ArrayList<Integer> preorderTraversal(TreeNode root) {
        ArrayList<Integer> list = new ArrayList();
        preOrderTraversalHelper(root, list);
        return list;
    }

    private void preOrderTraversalHelper(TreeNode node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preOrderTraversalHelper(node.left, list);
        preOrderTraversalHelper(node.right, list);
    }

    public static void main(String[] args) {
    }
}
