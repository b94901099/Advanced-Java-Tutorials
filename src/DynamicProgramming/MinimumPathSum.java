/*
 * Given a m x n grid filled with non-negative numbers, 
 * find a path from top left to bottom right which minimizes the sum of all numbers along its path.
 * Note: You can only move either down or right at any point in time.
 */
package DynamicProgramming;

public class MinimumPathSum {

    public int minPathSum(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;

        int[][] res = new int[row][col];
        // init
        res[0][0] = grid[0][0];
        // left
        for (int i = 1; i < row; i++) {
            res[i][0] = res[i - 1][0] + grid[i][0];
        }
        // top
        for (int j = 1; j < col; j++) {
            res[0][j] = res[0][j - 1] + grid[0][j];
        }

        // rest elements
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                res[i][j] = grid[i][j] + Math.min(res[i - 1][j], res[i][j - 1]);
            }
        }

        return res[row - 1][col - 1];
    }

    public static void main(String[] args) {
        MinimumPathSum m = new MinimumPathSum();
        int[][] grid = {{3, 5, 8, 7,}, {6, 9, 6, 1}, {0, 5, 8, 1}};
        System.out.println(m.minPathSum(grid));
    }
}
