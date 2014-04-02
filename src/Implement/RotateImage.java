/*
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * Follow up:
 * Could you do this in-place?
 */
package Implement;

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
    
    public static void main(String[] args) {
    }
}
