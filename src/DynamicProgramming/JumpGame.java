/*
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * For example:
 * A = [2,3,1,1,4], return true.
 * A = [3,2,1,0,4], return false.
 */
package DynamicProgramming;

public class JumpGame {

    public static boolean canJump(int[] A) {
        boolean[] can = new boolean[A.length];
        can[0] = true;
        for (int i = 1; i < A.length; i++) {
            for (int j = 0; j < i; j++) {
                if (can[j] && j + A[j] >= i) {
                    can[i] = true;
                    break;
                }
            }
        }
        return can[A.length - 1];
    }

    public static boolean canJump2(int[] A) {
        boolean[] can = new boolean[A.length];
        can[0] = true;
        for (int k = 1; k < A.length; k++) {
            if (can[k - 1] == false) {
                can[k] = false;
                continue;
            }
            for (int j = 0; j < k; j++) {
                if (can[j] && j + A[j] >= k) {
                    can[k] = true;
                    break;
                }
            }
        }
        return can[A.length - 1];
    }

    //貪心法, 總是找當前最優解
    boolean canJumpGreedy(int A[]) {
        int n = A.length;
        int maxRange = 0;
        for (int i = 0; i < n; ++i) {
            if (i <= maxRange) {
                maxRange = Math.max(maxRange, i + A[i]);
                if (maxRange >= n - 1) { // 若 maxRange 已能到達 n-1 則直接 return true
                    return true;
                }
            }
        }
        return maxRange >= n - 1;
    }

    public static void main(String[] args) {
        int[] A = {2, 2, 1, 0, 4};
        System.out.println(canJump(A));
        System.out.println(canJump2(A));
    }
}
