/*
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Your goal is to reach the last index in the minimum number of jumps.
 * For example:
 * Given array A = [2,3,1,1,4]
 * The minimum number of jumps to reach the last index is 2. 
 * (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
package DynamicProgramming;

public class JumpGameII {

    public static int jumpGreedy(int[] A) {

        int n = A.length;
        int maxRange = 0, tempRange = 0, stepNum = 0;
        for (int i = 0; i < n;) {
            if (tempRange >= n - 1) {
                break;
            }
            while (i <= tempRange) {
                maxRange = Math.max(maxRange, i + A[i]);
                ++i;
            }
            stepNum++;
            tempRange = maxRange;
        }
        return stepNum;

    }

    public static void main(String[] args) {
        int[] A = {3, 0, 1, 0, 4};
        System.out.println(jumpGreedy(A));
        int[] A1 = {1, 2, 1, 1, 1};
        System.out.println(jumpGreedy(A1));
        int[] A2 = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
        System.out.println(jumpGreedy(A2));

    }
}
