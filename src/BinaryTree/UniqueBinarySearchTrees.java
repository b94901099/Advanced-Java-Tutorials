/*
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 
 * 1         3     3      2      1
 *  \       /     /      / \      \
 *   3     2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 * 
 * DP:
 * 
 * 递归 : 由于是二叉查找树，先选择任一结点根结点，假设为结点i，则[1，i-1]范围的结点为结点i的左子树结点，
 * [i+1，n]范围的结点为结点i的右子树结点，则以结点i为根结点的BST个数为左，右子树可构成BST个数的乘积，
 * 基于这个思路，可以写出以下递归程序。
 */
package BinaryTree;

import DynamicProgramming.*;
import BinarySearch.*;

public class UniqueBinarySearchTrees {

    /*
     * 因此，dp[2] = dp[0] * dp[1] + dp[1] * dp[0]
     * n == 3时，构造方法如题目给的示例所示，dp[3] = dp[0] * dp[2] + dp[1] * dp[1] + dp[2] * dp[0]
     * 同时，当根节点元素为 1, 2, 3, 4, 5,  ..., i, ..., n时，基于以下原则的BST树具有唯一性：
     * 以i为根节点时，其左子树构成为[0,...,i-1],其右子树构成为[i+1,...,n]构成
     * 因此，dp[i] = sigma（dp[0...k] * dp[k+1...i]） 0 <= k < i - 1
     */
    
    public int numTrees(int n) {
        int[] count = new int[n + 1];
        count[0] = 1;
        count[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                count[i] = count[i] + count[j] * count[i - j - 1];
            }
        }
        return count[n];
    }

    // Recursion Sol
    //DFS 解法
    //有n个树。那么只考察BST的root的取值的可能性，有n个。假设BST的root为i，
    //则以i为root的BST的总数量为取决于它的左子树和右子树，具体说来，是左子树的数量和右子树的数量的乘积。
    //那么左子树的数量有多少个呢？ 右子树的数量有多少个呢？ 这个问题和刚才的问题完全一样，所以这样就构成了递归
    //base case是，当n=0或者n=1时，显然BST数量只能有1个。
    
    int numTreesRec(int n) {
        if (n == 1 || n == 0) {
            return 1;
        } else {
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += numTrees(i - 1) * numTrees(n - i);
            }
            return sum;
        }
    }

    // Rec 解法 2
    int numTreesDFS(int n) {
        return numTreesDFS(1, n);
    }

    int numTreesDFS(int start, int end) {
        if (start >= end) {
            return 1;
        }
        int totalNum = 0;
        for (int i = start; i <= end; ++i) {
            totalNum += numTreesDFS(start, i - 1) * numTreesDFS(i + 1, end);
        }
        return totalNum;
    }

    public static void main(String[] args) {
        UniqueBinarySearchTrees u = new UniqueBinarySearchTrees();
        for (int i = 1; i < 6; i++) {
            System.out.println(i + ": " + u.numTrees(i));
            System.out.println(i + ": " + u.numTreesDFS(i));
        }
    }
}
