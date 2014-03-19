/*
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 * For example:
 * Given the below binary tree and sum = 22,
 *             5
 *            / \
 *           4   8
 *          /   / \
 *         11  13  4
 *        /  \      \
 *       7    2      1
 * return
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 */
package BinaryTree;

import java.util.*;

public class PathSumII {

    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(root, sum, result, list);
        return result;
    }

    private void helper(TreeNode root, int sum, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list) {
        if (root == null) {
            return;
        }
        if (sum - root.val == 0 && root.left == null && root.right == null) {
            list.add(root.val);
            result.add(new ArrayList(list));
            list.remove(list.size() - 1);
            return;
        }
        list.add(root.val);
        helper(root.left, sum - root.val, result, list);
        helper(root.right, sum - root.val, result, list);
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
    }
}
