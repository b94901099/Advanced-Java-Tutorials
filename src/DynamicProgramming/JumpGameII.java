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

    /*这道题是Jump Game的扩展，区别是这道题不仅要看能不能到达终点，而且要求到达终点的最少步数。
     * 其实思路和Jump Game还是类似的，只是原来的全局最优现在要分成step步最优和step-1步最优（假设当前步数是step）。
     * 当走到超过step-1步最远的位置时，说明step-1不能到达当前一步，我们就可以更新步数，将step+1。
     * 时间复杂度仍然是O(n)，空间复杂度也是O(1)。代码如下：*/
    public int jump(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int lastReach = 0;
        int reach = 0;
        int step = 0;
        for (int i = 0; i <= reach && i < A.length; i++) {
            if (i > lastReach) {
                step++;
                lastReach = reach;
            }
            reach = Math.max(reach, A[i] + i);
        }
        if (reach < A.length - 1) {
            return 0;
        }
        return step;
    }

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
