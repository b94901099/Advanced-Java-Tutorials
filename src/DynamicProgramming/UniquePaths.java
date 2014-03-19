/*
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. 
 * The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 */
package DynamicProgramming;

import java.util.ArrayList;

public class UniquePaths {

    public int uniquePaths(int m, int n) {
        int[] res = new int[n];
        // init array
        for (int j = 0; j < n; j++) {
            res[j] = 1;
        }
        // add values
        for (int i = 1; i < m; i++) {
            // reset the head to 1 (simulate the next row head)
            // similar to set all left most elements in a 2D array to 1
            res[0] = 1;
            for (int j = 1; j < n; j++) {
                res[j] = res[j - 1] + res[j];
            }
        }
        return res[n - 1];
    }

    // 深搜 + 緩存, TC = O(n^2), SC = O(n^2)
    public int uniquePaths2(int m, int n) {
        int[][] res = new int[m][n];
        // init left
        for (int i = 0; i < m; i++) {
            res[i][0] = 1;
        }
        // init top
        for (int j = 0; j < n; j++) {
            res[0][j] = 1;
        }
        // add values
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }
        return res[m - 1][n - 1];
    }

    //此法超時 TC = O(n^4)
    public int uniquePaths3(int m, int n) {
        if (m < 1 || n < 1) {
            return 0;
        }
        if (m == 1 && n == 1) {
            return 1;
        }
        return uniquePaths3(m - 1, n) + uniquePaths3(m, n - 1);
    }

    public static void main(String[] args) {
        UniquePaths u = new UniquePaths();
        System.out.println(u.uniquePaths2(3, 4));
    }
}
