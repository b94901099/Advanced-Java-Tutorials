/*
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 * Find the total sum of all root-to-leaf numbers.
 * For example,
 *   1
 *  / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 * Return the sum = 12 + 13 = 25.
 * 
 * 兩種做法: 1. 算數字, 到leaf直接加總  2. 算數字, 到leaf紀錄該數字, 得到所有數字後加總
 */
package BinaryTree;

import java.util.*;

public class SumRootToLeafNumbers {
    //分段做, 先求出所有數字, 再算總合
    public int sumNumbers3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        ArrayList<Integer> nums = new ArrayList<Integer>();
        findAllNumbers(root, nums, 0);
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        return sum;
    }

    private void findAllNumbers(TreeNode root, ArrayList<Integer> nums, int tmp) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            nums.add(tmp * 10 + root.val);
        }
        findAllNumbers(root.left, nums, tmp * 10 + root.val);
        findAllNumbers(root.right, nums, tmp * 10 + root.val);
    }
    //sol 2, 求數字算總合一起進行
    int sum2;

    public int sumNumbers2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        sum2 = 0;
        sumNumbers2Helper(root, 0);
        return sum2;
    }

    private void sumNumbers2Helper(TreeNode root, int tmpSum) {
        if (root.left == null && root.right == null) {
            sum2 += tmpSum * 10 + root.val;
            return;
        }
        if (root.left != null) {
            sumNumbers2Helper(root.left, tmpSum * 10 + root.val);
        }
        if (root.right != null) {
            sumNumbers2Helper(root.right, tmpSum * 10 + root.val);
        }
    }

    // 以下為 Sol 1
    public int sumNumbers1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return root.val;
        }
        return sumHelper(root, 0);
    }

    private int sumHelper(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return sum * 10 + root.val;
        }

        int sumLeft = sumHelper(root.left, sum * 10 + root.val);
        int sumRight = sumHelper(root.right, sum * 10 + root.val);
        return sumLeft + sumRight;
    }

    public static void main(String[] args) {
        SumRootToLeafNumbers s = new SumRootToLeafNumbers();
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n3.right = n5;
        System.out.println(s.sumNumbers1(n1));
        System.out.println(s.sumNumbers2(n1));
        System.out.println(s.sumNumbers3(n1));
    }
}
