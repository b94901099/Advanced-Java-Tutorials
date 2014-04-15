/*
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * What if the given tree could be any binary tree? Would your previous solution still work?
 * Note:
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *     1
 *    /  \
 *   2    3
 *  / \    \
 * 4   5    7
 * After calling your function, the tree should look like:
 *     1 -> NULL
 *    /  \
 *   2 -> 3 -> NULL
 *  / \    \
 * 4-> 5 -> 7 -> NULL
 */
package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointersinEachNodeII {
    public void connect(TreeLinkNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();

        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeLinkNode cur = queue.poll();

            for (int i = 0; i < size; i++) {
                cur.next = i < (size - 1) ? queue.peek() : null;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
                cur = cur.next;
            }
        }
    }

    private static class TreeLinkNode {

        TreeLinkNode left;
        TreeLinkNode right;
        TreeLinkNode next;
    }

}
