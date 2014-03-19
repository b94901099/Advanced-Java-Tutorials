/*
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 *  
 * 1         3     3      2      1
 *  \       /     /      / \      \
 *   3     2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 * 
 * 这道题的思路就是对1..n中的每一个数都依次让它做root，然后分出左右区间，再递归求解。
 * 最后把左右区间求得的子结果依次分别作为root的左右孩子，因此总共要3次循环。
 * 还有值得注意的技巧是，当begin>end时，要往ret AL里面添加null，使得每个AL里面至少有一个元素（null）。
 * 这样可以避免判断只有左区间或只有右区间的情况。
 */
package DeepFirstSearch;

import java.util.*;

public class UniqueBinarySearchTreesII {

    public static ArrayList<TreeNode> generateTrees(int n) {
        return dfs(0, n - 1);
    }

    public static ArrayList<TreeNode> dfs(int begin, int end) {
        ArrayList<TreeNode> ret = new ArrayList<TreeNode>();
        if (begin > end) {
            ret.add(null);          // 相当于占位符  
            return ret;
        }

        for (int i = begin; i <= end; i++) {
            ArrayList<TreeNode> left = dfs(begin, i - 1);   // left和right至少会有一个元素null！  
            ArrayList<TreeNode> right = dfs(i + 1, end);

            for (int j = 0; j < left.size(); j++) {            // 因为size至少为1，所以左右都会被访问到  
                for (int k = 0; k < right.size(); k++) {
                    TreeNode root = new TreeNode(i + 1);      // root node, i+1 因为第一个从1而不是从0开始  
                    root.left = left.get(j);
                    root.right = right.get(k);
                    ret.add(root);
                }
            }
        }
        return ret;
    }

    public static void main(String[] args) {
    }

    private static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }
}
