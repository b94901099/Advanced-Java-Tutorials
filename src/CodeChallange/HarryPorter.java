/*
 * 有一个2维矩阵，假设你是Harry Potter，在矩阵的左上角，你现在要走到右下角。矩阵中每个点都有一个权值，
 * 有正数也有负数，遇到正数表示你的生命力能增加那么多，遇到负数表示生命力减少那么多，
 * 在任何时刻如果你的生命力小于0，那么你就挂了。在一开始你有一定的初始生命力，现在问这个初始的生命力最少是多少，
 * 才能保证你能够找到一条路。走到右下角。每一步只能向右或者向下。
 * 考虑如下矩阵：
 0 0 0 0 
 0 0 -6 0
 0 -5 20 0
 0 0 0 1
 * 容易看出，这里走通过-5和20的路径可以保证最后的生命值最大化，然而由于之前必须经过一个负权值点，
 * 所以这里最小的初始生命值为5。然而其实此题明显最优解是直接走两个边路中的一个，初始生命值为0即可。
 * 2）使用DP时，除了需要归纳出递归表达式意外，还需要找到自底向上的base case。
 * 其实这个base case是可以引导推理的方向的。在Minimum Path Sum题目里，显然base case是在左上角时，
 * 因为最小的和就是当前左上角这个点的权值了。这样一来，从左上角往右下角推理会很顺畅。
 * 这一题中，左上角的值其实是所求，而base case其实应该在右下角，必须在终点的时候不为负数。
 * （在起点的时候生命值仅仅不为负是无法保证能够一直走完全程的。）所以，这题应该从右下角往左上角推理。
 * 如果设M和N分别为矩阵的两边长，dp[i][j]表示在没有加上matrix[i][j]的权值之前所需的最小的生命值，我们这题其实就是求的dp[0][0]。
 * 
 * Base Case: 
 * 对于最右下角，由于需要dp[M - 1][N - 1] + matrix[i][j] >= 0，可以得到dp[M - 1][N - 1] >= -matrix[i][j]。
 * 如果这里的matrix[i][j]为正数，那么dp[M - 1][N - 1]最小值就为负数，是题目不允许的，所以：
 * dp[M - 1][N - 1] = MAX{-1 * matrix[M - 1][N - 1], 0}
 * 
 * Recursion: 对于任意一个dp[i][j], 
 * 1）如果向下延伸至dp[i + 1][j]，可得dp[i][j] + matrix[i][j] >= dp[i + 1][j]，且dp[i][j] >= 0。
 * 所以 dp[i][j] = MAX{dp[i + 1][j] - matrix[i][j], 0}
 * 2）如果向右延伸至dp[i][j + 1]，可得dp[i][j] + matrix[i][j] >= dp[i][j + 1]，且dp[i][j] >= 0。
 * 所以 dp[i][j] = MAX{dp[i][j + 1] - matrix[i][j], 0}
 * 综上，dp[i][j] = MIN{ MAX{dp[i + 1][j] - matrix[i][j], 0}, MAX{dp[i][j + 1] - matrix[i][j], 0} }。
 * 注意，另外两条边上的dp值只需要沿一个方向递归，所以需要特殊处理。代码如下：
 */
package CodeChallange;

public class HarryPorter {

    public int harryPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        int[][] hp = new int[m][n];
        hp[m - 1][n - 1] = Math.min(-matrix[m - 1][n - 1], 0);

        for (int i = m - 2; i >= 0; i--) {
            hp[i][n - 1] = Math.max(hp[i + 1][n - 1] - matrix[i][n - 1], 0);
        }

        for (int j = n - 2; j >= 0; j--) {
            hp[m - 1][j] = Math.max(hp[m - 1][j + 1] - matrix[m - 1][j], 0);
        }

        for (int i = m - 2; i >= 0; i--) {
            for (int j = n - 2; j >= 0; j--) {
                hp[i][j] = Math.min(Math.max(hp[i + 1][j] - matrix[i][j], 0),
                        Math.max(hp[i][j + 1] - matrix[i][j], 0));
            }
        }
        return hp[0][0];
    }

    public static void main(String[] args) {
    }
}
