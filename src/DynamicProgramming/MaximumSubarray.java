package DynamicProgramming;

/*
 * 我们这么想，当我们从头到尾遍历这个数组的时候，对于一个数组里的数字，它有几种选择呢？
 * 它只有两种选择： 1. 加入之前的SubArray， 2. 自己另起一个SubArray
 * 好，那什么时候会出现这两种情况呢。
 * 注意我们是找累加的最大值，那么，
 * 如果之前SubArray的总体和大于0的话，我们可以认为其对后续数字是有贡献的。这种情况下我们选择加入之前的SubArray
 * 如果之前SubArray的总体和为0或者小于0的话，我们可以认为其对后续数字是没有贡献，甚至是有害的（小于0时）。
 * 这种情况下我们只能选择以这个数字开始，另起一个SubArray
 * 明白了这点，代码就很好写了, sum记录之前SubArray的和，max用来返回最大值，
 * 当sum的值大于max时，说明发现和更大的SubArray序列，此时更新max的值。
 * 
 * Note: 可推導出 Binary Tree Maximum Path Sum 
 */

public class MaximumSubarray {

    public int maxSubArray2(int[] A) {
        if (A.length < 1 || A == null) {
            return 0;
        }

        int sum = A[0];
        int max = A[0];
        for (int i = 1; i < A.length; i++) {
            sum = Math.max(sum, 0);
            sum = sum + A[i];
            max = Math.max(sum, max);
        }
        return max;
    }

    public int maxSubArray(int[] A) {
        if (A.length < 1 || A == null) {
            return 0;
        }

        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum = sum + A[i];
            max = Math.max(max, sum);
            sum = Math.max(sum, 0);
        }
        return max;
    }

    public static void main(String[] args) {
        MaximumSubarray m = new MaximumSubarray();
        int[] A = {-5, 20, -4, 10, -18};
        System.out.println(m.maxSubArray(A));
        System.out.println(m.maxSubArray2(A));
    }
}
