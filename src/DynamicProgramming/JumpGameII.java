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
        int maxReach = 0;
        int step = 0;
        for (int i = 0; i <= maxReach && i < A.length; i++) {
            if (i > lastReach) {
                step++;
                lastReach = maxReach;
            }
            maxReach = Math.max(maxReach, A[i] + i);
        }
        if (maxReach < A.length - 1) {
            return 0;
        }
        return step;
    }

    
    //雖然 AC, 但是到不了終點時會產生無限循環
    public static int jumpGreedy(int[] A) {

        int n = A.length;
        int maxx = 0, temp = 0, num = 0;
        for (int i = 0; i < n;) {
            if (temp >= n - 1) {
                break;
            }
            while (i <= temp) {
                maxx = Math.max(maxx, i + A[i]);
                ++i;
            }
            num++;
            temp = maxx;
        }
        return num;
    }

    public static void main(String[] args) {
        JumpGameII j = new JumpGameII();
        int[] A = {3, 0, 1, 0, 4};
//        System.out.println(jumpGreedy(A));
        System.out.println(j.jump(A));
        int[] A1 = {1, 2, 1, 1, 1};
        System.out.println(jumpGreedy(A1));
        System.out.println(j.jump(A1));
        int[] A2 = {7, 0, 9, 6, 9, 6, 1, 7, 9, 0, 1, 2, 9, 0, 3};
        System.out.println(jumpGreedy(A2));
        System.out.println(j.jump(A2));
    }
}
