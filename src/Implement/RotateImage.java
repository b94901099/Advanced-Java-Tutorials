/*
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 */
package Implement;

import java.util.Arrays;

public class RotateImage {

    public void rotate(int[][] matrix) {
        int n = matrix.length;

        for (int i = 0; i < n / 2; i++) {
            int start = i;
            int end = n - 1 - i;

            for (int j = start; j < end; j++) {
                int offset = j - start;
                int temp = matrix[start][j];
                matrix[start][j] = matrix[end - offset][start];
                matrix[end - offset][start] = matrix[end][end - offset];
                matrix[end][end - offset] = matrix[j][end];
                matrix[j][end] = temp;
            }
        }
    }

    // 有bug
    public void rotate2(int[][] matrix) {
        int n = matrix.length;

        for (int layer = 0; layer < n / 2; layer++) {
            for (int i = layer; i < n - layer - 1; i++) {
                int tmp = matrix[layer][i];
                matrix[layer][i] = matrix[n - layer - i - 1][layer];
                matrix[n - layer - i - 1][layer] = matrix[n - layer - 1][n - layer - 1 - i];
                matrix[n - layer - 1][n - layer - 1 - i] = matrix[i][n - layer - 1];
                matrix[i][n - layer - 1] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        RotateImage r = new RotateImage();
        r.rotate(matrix);
        for (int[] i : matrix) {
            System.out.println(Arrays.toString(i));
        }
    }
}
