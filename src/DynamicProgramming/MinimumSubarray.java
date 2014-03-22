/*
 * MaximumSubarray 的變形
 */
package DynamicProgramming;

public class MinimumSubarray {
    
    public int minSubArray(int[] A) {
        int sum = A[0];
        int min = A[0];
        for (int i = 1; i < A.length; i++) {
            sum = Math.min(sum, 0);
            sum = sum + A[i];
            min = Math.min(sum, min);
        }
        return min;
    }
    
    public static void main(String[] args) {
        MinimumSubarray m = new MinimumSubarray();
        int[] A = {-5, 20, -4, 10, -18};
        System.out.println(m.minSubArray(A));
    }
}
