package BinaryTree;

import java.util.ArrayList;
import java.util.Stack;

public class BinaryTreeInorderTraversal {

    /*循环实现，这题就有点难度了，不过没关系，我们可以借用栈空间来解决这题。
     * 树中某结点不为空，则将其左结点压入栈中，如果没有，则弹出该节点，
     * 将该节点的值存入vector中，再压入出栈元素的右结点，依次进行循环遍历，直到栈为空为止。
     */
    public ArrayList<Integer> inorderTraversalIteration(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> tmpStack = new Stack<TreeNode>();
        TreeNode current = root;
        while (true) {
            while (current != null) {
                tmpStack.push(current);
                current = current.left;
            }

            if (tmpStack.empty())
                return result;

            current = tmpStack.pop();
            result.add(current.val);
            current = current.right;
        }
    }

    public ArrayList<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        inOrderTraversalHelper(root, result);
        return result;
    }

    private void inOrderTraversalHelper(TreeNode node, ArrayList<Integer> list) {
        if (node == null) {
            return;
        }
        inOrderTraversalHelper(node.left, list);
        list.add(node.val);
        inOrderTraversalHelper(node.right, list);
    }

    public static void main(String[] args) {
    }
}
