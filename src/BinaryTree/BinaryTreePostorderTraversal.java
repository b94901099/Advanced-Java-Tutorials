package BinaryTree;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreePostorderTraversal {

    public ArrayList<Integer> postorderTraversalIterate(TreeNode root) {

        ArrayList<Integer> lst = new ArrayList<Integer>();

        if (root == null) {
            return lst;
        }

        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);

        TreeNode prev = null;
        while (!stack.empty()) {
            TreeNode curr = stack.peek();

            // go down the tree.
            //check if current node is leaf, if so, process it and pop stack,
            //otherwise, keep going down
            if (prev == null || prev.left == curr || prev.right == curr) {
                //prev == null is the situation for the root node
                if (curr.left != null) {
                    stack.push(curr.left);
                } else if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    stack.pop();
                    lst.add(curr.val);
                }

                //go up the tree from left node    
                //need to check if there is a right child
                //if yes, push it to stack
                //otherwise, process parent and pop stack
            } else if (curr.left == prev) {
                if (curr.right != null) {
                    stack.push(curr.right);
                } else {
                    stack.pop();
                    lst.add(curr.val);
                }

                //go up the tree from right node 
                //after coming back from right node, process parent node and pop stack. 
            } else if (curr.right == prev) {
                stack.pop();
                lst.add(curr.val);
            }

            prev = curr;
        }
        return lst;
    }

    public ArrayList<Integer> postorderTraversalIteration(TreeNode root) {
        ArrayList<Integer> result = new ArrayList();
        Stack<TreeNode> tmpStack = new Stack<TreeNode>();
        TreeNode current = root;
        while (true) {
            while (current != null) {
                if (current.right != null) {
                    tmpStack.push(current.right);
                }
                tmpStack.push(current);
                current = current.left;
            }
            if (tmpStack.empty()) {
                return result;
            }

            current = tmpStack.pop();
            if (current.right != null && !tmpStack.empty() && current.right == tmpStack.peek()) {
                tmpStack.pop();
                tmpStack.push(current);
                current = current.right;
            } else {
                result.add(current.val);
                current = null;
            }
        }
    }

    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList();
        if (root == null) {
            return result;
        }
        postOrderTraversalHelper(root, result);
        return result;
    }

    private void postOrderTraversalHelper(TreeNode node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        postOrderTraversalHelper(node.left, list);
        postOrderTraversalHelper(node.right, list);
        list.add(node.val);
    }

    private void PostorderVisitIterative(TreeNode node, ArrayList<Integer> list) {
        Stack<TreeNode> tmpNodes = new Stack();
        tmpNodes.push(node);
        TreeNode top = null;
        TreeNode pre = node;
        while (!tmpNodes.empty()) {
            top = tmpNodes.peek();
            if (top.left != null && top.right != null && top.left != pre) {
                tmpNodes.push(top.left);
            } else if (top.right != null && top.right != pre) {
                tmpNodes.push(top.right);
            } else {
                list.add(top.val);
                pre = top;
                tmpNodes.pop();
            }
        }
    }

    public static void main(String[] args) {
    }
}
