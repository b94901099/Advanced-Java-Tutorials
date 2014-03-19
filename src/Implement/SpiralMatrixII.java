package Implement;

import java.util.Arrays;

public class SpiralMatrixII {

    public static int[][] generateMatrix(int n) {
        if (n == 0) {
            return null;
        }
        if (n == 1) {
            int[][] matrix = {{1}};
            return matrix;
        }

        int[][] matrix = new int[n][n];

        int up = 0;
        int down = n - 1;
        int left = 0;
        int right = n - 1;
        int input = 1;

        while (up <= down && left <= right) {
            if (up == down) {
                for (int i = left; i <= right; i++) {
                    matrix[up][i] = input++;
                }
                return matrix;
            }

            if (right == left) {
                for (int i = up; i <= down; i++) {
                    matrix[i][right] = input++;
                }
                return matrix;
            }
            for (int i = left; i <= right; i++) {
                matrix[up][i] = input++;
            }
            up++;
            for (int i = up; i <= down; i++) {
                matrix[i][right] = input++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                matrix[down][i] = input++;
            }
            down--;
            for (int i = down; i >= up; i--) {
                matrix[i][left] = input++;
            }
            left++;
        }

        return matrix;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] matrix = generateMatrix(n);
        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
