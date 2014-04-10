/*
 * In Binary Tree, Inorder successor of a node is the next node in Inorder traversal 
 * of the Binary Tree. Inorder Successor is NULL for the last node in Inoorder traversal.
 * In Binary Search Tree, Inorder Successor of an input node can also be defined as 
 * the node with the smallest key greater than the key of input node. 
 * So, it is sometimes important to find next node in sorted order.
 *          20
 *         /  \
 *        8    22
 *       / \
 *      4  12
 *        /  \
 *       10  14
 * In the above diagram, inorder successor of 8 is 10, 
 * inorder successor of 10 is 12 and 
 * inorder successor of 14 is 20.
 * 
 * 解法1: 由樹找 O(log n), Terry 的講義, delete 如何找到 successor?
 * 解法2: inorder traversal O(n)
 */
package CodeChallange;
import java.util.*;

public class InorderSuccessorInBinarySearchTree {

    // 測試成功
    public TreeNode inorderSuccessSol1(TreeNode root, TreeNode target) {
        if (root == null || target == null) {
            return null;
        }
        TreeNode candidate = null; //凡是比 target 大的 node 皆是 candidates, 挑最接近的 candidate
        
        while (root.val != target.val) {
            if (root.val < target.val) {
                root = root.right;
            } else {
                candidate = root;
                root = root.left;
            }
            if (root == null) {
                return null;
            }
        } // 找到 target node
        
        if (root.right == null) { //若target node 右邊沒有了, 則返回候選人
            return candidate;
        } else { //若 target 有 right child, 則找出 right child 最左邊的 node (大於 target 而且最接近 target)
            TreeNode retrieve = root.right;
            while (retrieve.left != null) {
                retrieve = retrieve.left;
            }
            return retrieve;
        }
    }

    // inorder traversal
    public TreeNode inorderSuccessSol2(TreeNode root, TreeNode target, boolean found) {
        if (root == null) {
            return null;
        }
        if (found) {
            return root;
        }
        TreeNode left = inorderSuccessSol2(root.left, target, found);
        if (root == target) {
            found = true;
        }
        TreeNode right = inorderSuccessSol2(root.right, target, found);

        if (left != null) {
            return left;
        }
        if (right != null) {
            return right;
        }
        return null;
    }

    public static void main(String[] args) {
    }
}
