/*
 *The path may start and end at any node in the tree.
 *For example:
 *Given the below binary tree,
 *       1
 *      / \
 *     2   3
 *Return 6.
 * 
 * http://blog.csdn.net/u011095253/article/details/9221167
 * 可由 Maximum SubArray 推導而得
 * 首先，我们应该注意到这个Path能从任意节点开始，到任意节点结束，不过不要被它吓住，仔细想想是不是和上面的题差不多
 * 如果说Array只有一个方向的话，那么Binary Tree其实只是左，右两个方向而已，我们需要比较两个方向上的值
 * 那好，如果说Array可以从头到尾遍历，那么Binary Tree怎么办呢，我们可以采用Binary Tree最常用的dfs来进行遍历
 * 首先，L, R 是dfs返回的两个子分支的值我们先不提。对于sum，我们照搬第一题的思路
 * 如果L大于0，那么对后续是有利的，我们加上L。 例如图中A节点，左子树大于0，那么更新最大路径的和为【B, A】的和
 * 如果R大于0，那么对后续是有利的，我们加上R。例如图中A节点，右子树大于0，那么更新最大路径的和为【C, A】的和
 * 如果L，R同时大于0，左右两边对后续都是有利的，我们两边都要，例如图中A节点，左右子树都大于0，那么更新最大路径的和为【B,A,C】的和
 * 如果L，R都小于等于0，那么左右两边对后续都是无利甚至有害的，我们从这个节点，另起一个路径，例如图中的A节点，更新最大路径的和0为【A】的和
 * 好了，sum怎么来的我们已经很清楚了，还有一点值得注意是在Return的语句里
 * return Math.max(r,l)>0?Math.max(r,l)+root.val:root.val;
 * 可能有人疑惑了，为什么只返回一个方向的值呢？（如果Math.max(r,l)>0, 我们只返回这两个方向上最大值的方向）
 * 我们需要明确sum 和 return 返回值的区别，还是举A节点的例子
 * 对于sum，当我们两个方向的值都取得时候，路径是图中绿色笔的路径，是因为分析sum的时候我们是站在A节点的角度上分析的
 * 而对于Return，A节点最后Return的时候，是返回到上一层D节点的递归中。重点是，Return方向是向上的。
 * 所以不会出现图中绿色的路径，只可能出现红色的两个路径中的一条（当Math.max(l,r)>0时）或者就一个D节点（当Math.max(l,r)<=0时）
 * 这一点明确后，整个题就变得很直观了，最后记得持续更新max，当发现更大路径和的时候
 * 这两个题放到一起，大家感受一下。
 */
package BinaryTree;

public class BinaryTreeMaximumPathSum {

    int max;

    public int maxPathSum(TreeNode root) {
        max = (root == null) ? 0 : root.val;
        findMax(root);
        return max;
    }

    private int sum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSum = sum(root.left);
        int rightSum = sum(root.right);

        int curSum = root.val;
        curSum = curSum + leftSum > 0 ? leftSum : 0;
        curSum = curSum + rightSum > 0 ? rightSum : 0;
        max = Math.max(curSum, max);
        
        return root.val + (Math.max(leftSum, rightSum) > 0 ? Math.max(leftSum, rightSum) : 0);
        // 注意: return 時只 return 一條路徑
    }

    // 相同解法
    public int maxPathSum2(TreeNode root) {
        max = (root == null) ? 0 : root.val;
        findMax(root);
        return max;
    }

    public int findMax(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // recursively get sum of left and right path
        int left = Math.max(findMax(node.left), 0);
        int right = Math.max(findMax(node.right), 0);

        //update maximum here
        max = Math.max(node.val + left + right, max);

        // return sum of largest path of current node
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
    }
}
