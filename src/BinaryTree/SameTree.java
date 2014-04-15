/*
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
package BinaryTree;

import java.util.*;

public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
    }

    public boolean isSameTreeBFS(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue1 = new LinkedList<TreeNode>();
        Queue<TreeNode> queue2 = new LinkedList<TreeNode>();
        queue1.offer(p);
        queue2.offer(q);

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            if (queue1.size() != queue2.size()) return false;

            int size = queue1.size();

            for (int i = 0; i < size; i++) {
                TreeNode cur1 = queue1.poll();
                TreeNode cur2 = queue2.poll();
                if (cur1 == null && cur2 == null) continue;
                if (cur1 == null || cur2 == null) return false;
                if (cur1.val != cur2.val) return false;
                queue1.offer(cur1.left);
                queue1.offer(cur1.right);
                queue2.offer(cur2.left);
                queue2.offer(cur2.right);
            }
        }

        if (!queue1.isEmpty() || !queue2.isEmpty()) return false;

        return true;
    }
}
