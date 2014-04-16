/*
 * Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.
 * 
 * http://blog.csdn.net/kenden23/article/details/17503899
 * 每一行运用max histogram算法思想计算。
 * 
 * 这题与Largest Rectangle in Histogram联系紧密，从上到下（或从下到上）得到每个节点上（下）方连续'1'
 * （包括自己）的个数。则要得到最大矩形，就是在每一行里进行一次Largest Rectangle in Histogram，
 * 这样得到了解法2。虽然这样复杂度降到了O(m * n)，但是从LeetCode测试结果上看，解法1还更快些，可能是解法1运气好的时候剪枝比较多。
 * 
 * 
 * 这题最直接的就是O(m^2 * n)的遍历（解法1），从左到右（或者从右到左）得到每个节点左（右）方连续的‘1’
 * （包括自己）的个数。同时，从下到上（或从上到下）计算节点下方（上方）所能构成的矩形的最大值。
 */
package Implement;

import java.util.*;

public class MaximalRectangle {

    int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return 0;
        }
        int[] height = new int[matrix[0].length];
        int max_area = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }
            max_area = Math.max(max_area, largestRectangleArea(height));
        }
        return max_area;
    }

    public int largestRectangleArea(int[] height) {
        int ret = 0;

        if (height == null) {
            return 0;
        }

        Stack<Integer> stack = new Stack<Integer>();
        int N = height.length;
        for (int i = 0; i < N; ++i) {
            if (stack.isEmpty() || height[stack.peek()] <= height[i]) {
                stack.push(i);
            } else {
                int index = stack.pop();
                int area = height[index]
                        * (stack.isEmpty() ? i : i - stack.peek() - 1);
                ret = Math.max(ret, area);
                --i;
            }
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            int area = height[index]
                    * (stack.isEmpty() ? N : N - stack.peek() - 1);
            ret = Math.max(ret, area);
        }

        return ret;
    }

    //sol 1
    //这题最直接的就是O(m^2 * n)的遍历（解法1），从左到右（或者从右到左）得到每个节点左（右）方连续的‘1’
    //（包括自己）的个数。同时，从下到上（或从上到下）计算节点下方（上方）所能构成的矩形的最大值。
    public int maximalRectangle1(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int M = matrix.length;
        int N = matrix[0].length;
        int max = 0;

        // init  
        int[][] dp = new int[M + 1][N + 1];

        // dp  
        for (int i = M - 1; i >= 0; --i) {
            for (int j = N - 1; j >= 0; --j) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = dp[i][j + 1] + 1;
                    int min = dp[i][j];
                    int count = 1;
                    int tempMax = dp[i][j];
                    while (dp[i + count][j] != 0) {
                        ++count;
                        min = Math.min(dp[i + count][j], min);
                        tempMax = Math.max(min * count, tempMax);
                    }
                    max = Math.max(max, tempMax);
                }
            }
        }
        return max;
    }

    /* 
     * 这题与Largest Rectangle in Histogram联系紧密，从上到下（或从下到上）得到每个节点上（下）方连续'1'（包括自己）的个数。
     * 则要得到最大矩形，就是在每一行里进行一次Largest Rectangle in Histogram，这样得到了解法2。
     * 虽然这样复杂度降到了O(m * n)，但是从LeetCode测试结果上看，解法1还更快些，可能是解法1运气好的时候剪枝比较多。
     */
    public int maximalRectangle2(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int M = matrix.length;
        int N = matrix[0].length;

        int max = 0;
        int[][] height = new int[M + 1][N];
        for (int i = 1; i <= M; ++i) {
            Stack<Integer> stack = new Stack<Integer>();
            for (int j = 0; j < N; ++j) {
                if (matrix[i - 1][j] == '1') {
                    height[i][j] = height[i - 1][j] + 1;
                }
                if (stack.isEmpty() || height[i][stack.peek()] <= height[i][j]) {
                    stack.push(j);
                } else {
                    int index = stack.pop();
                    int area = height[i][index]
                            * (stack.isEmpty() ? j : (j - stack.peek() - 1));
                    max = Math.max(max, area);
                    --j;
                }
            }
            while (!stack.isEmpty()) {
                int index = stack.pop();
                int area = height[i][index]
                        * (stack.isEmpty() ? N : (N - stack.peek() - 1));
                max = Math.max(max, area);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        
    }
}
