/*
 *   3
 *  / \
 * 9  20
 *   /  \
 *  15   7
 *return its zigzag level order traversal as:
 *[
 * [3],
 * [20,9],
 * [15,7]
 *]
 */
package BinaryTree;

import java.util.*;

public class BinaryTreeZigzagLevelOrderTraversal {

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int h = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                TreeNode tmp = queue.poll();
                if (h % 2 == 0) {
                    list.add(tmp.val);
                } else {
                    list.add(0, tmp.val);
                }
                if (tmp.left != null) {
                    queue.offer(tmp.left);
                }
                if (tmp.right != null) {
                    queue.offer(tmp.right);
                }
            }
            result.add(list);
            h++;
        }
        return result;
    }

    public ArrayList<ArrayList<Integer>> zigzagLevelOrder1(TreeNode root) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (root == null) {
            return result;
        }
        Stack< TreeNode> currLevel = new Stack< TreeNode>();
        Stack< TreeNode> nextLevel = new Stack< TreeNode>();
        Stack< TreeNode> tmp;
        currLevel.push(root);

        boolean leftToRight = true;
        while (!currLevel.isEmpty()) {
            ArrayList< Integer> currLevelResult = new ArrayList< Integer>();
            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                currLevelResult.add(node.val);
                if (leftToRight) {
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                } else {
                    if (node.right != null) {
                        nextLevel.push(node.right);
                    }
                    if (node.left != null) {
                        nextLevel.push(node.left);
                    }
                }
            }
            result.add(currLevelResult);
            tmp = currLevel;
            currLevel = nextLevel;
            nextLevel = tmp;
            leftToRight = !leftToRight;
        }
        return result;
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

        BinaryTreeZigzagLevelOrderTraversal bt = new BinaryTreeZigzagLevelOrderTraversal();

        for (ArrayList<Integer> list : bt.zigzagLevelOrder(node0)) {
            for (Integer i : list) {
                System.out.print(i + ", ");
            }
            System.out.println("");
        }
    }
}
