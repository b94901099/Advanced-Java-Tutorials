/*
 * 给定一个数组，我们可以找到两个不相交的、并且是连续的子数组A和B，A中的数字和为sum(A), 
 * B中的元素和为sum(B)。找到这样的A和B，满足sum(A) - sum(B)的绝对值是最大的。
 * 例如：[2, -1 -2, 1, -4, 2, 8]划分为A=[-1, -2, 1, -4], B=[2, 8]， 最大的值为16
 * 子数组是不相交的
 * 子数组是连续的，这个有点多余，但还是强调一下得好
 * 然后题目的要求是，差的绝对值最大。那我们自然而然能够想到：找到的两个不相交的子数组，
 * 一个值要很小，一个值要很大。这样才能够保证差的绝对值最大。那如何找到这样的数组呢？我们从不相交的这个条件入手。看题目中例子：
 * 0	1	2	3	4	5	6
 * 2	-1	-2	1	4	2	8
 * 看上面的表格，如果两个子数组不想交，我们有六个位置，作为划分的备选，0和1之间、1和2之间、2和3之间，
 * 直到5和6之间。这六个位置，都可以将数组划分为两部分。我们设定，数组长度为n，i将数据划分为两部分分别为 
 * [0,i-1]和[i,n-1]。都是两边包含的集合。i是从1到n-1的。
 * 对于任意的i，我们得到了两部分[0, i-1]和[i, n-1]。接下来，就是在这两部分中，找到一个和最小的子数组A，
 * 以及和最大的子数组B。那么A-B的绝对值，就是i这个划分下，满足条件的两个数组的差的最大值。
 * 对于，所有的i而言，这个绝对值最大时的A和B就是我们要找到的。
 * 
 * 作法:
 * 从左向右遍历数组，计算max_left和min_left数组，O(n)时间复杂度
 * 从右向左遍历数组，计算max_right和min_right数组，O(n)时间复杂度
 * 然后对于每一个i，i从1开始到n-1,计算max_left[i - 1] - min_right[i], max_right[i] - min_left[i - 1]。选取绝对值最大的。
 * 
 */
package CodeChallange;

import java.util.Arrays;

public class SubarrayMaxiAbsDiff {

    public int maxAbsDif(int[] A) {
        int[] maxLeft = findSubarrayMaxSumLeft(A);
        int[] minLeft = findSubarrayMinSumLeft(A);
        int[] maxRight = findSubarrayMaxSumRight(A);
        int[] minRight = findSubarrayMinSumRight(A);
        
        int maxDiff = 0;
        
        for (int i = 0; i < A.length - 1; i++) {
            int diff = Math.abs(maxLeft[i] - minRight[i + 1]);
            maxDiff = Math.max(maxDiff, diff);
        }
        
        for (int i = 0; i < A.length - 1; i++) {
            int diff = Math.abs(minLeft[i] - maxRight[i + 1]);
            maxDiff = Math.max(maxDiff, diff);
        }
        return maxDiff;
    }

    public int[] findSubarrayMaxSumLeft(int[] A) {
        int[] sumArray = new int[A.length];
        sumArray[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sumArray[i] = sumArray[i - 1] > 0 ? sumArray[i - 1] : 0;
            sumArray[i] = sumArray[i] + A[i];
        }
        return sumArray;
    }

    public int[] findSubarrayMinSumLeft(int[] A) {
        int[] sumArray = new int[A.length];
        sumArray[0] = A[0];
        for (int i = 1; i < A.length; i++) {
            sumArray[i] = sumArray[i - 1] < 0 ? sumArray[i - 1] : 0;
            sumArray[i] = sumArray[i] + A[i];
        }
        return sumArray;
    }

    public int[] findSubarrayMaxSumRight(int[] A) {
        int[] sumArray = new int[A.length];
        sumArray[A.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            sumArray[i] = sumArray[i + 1] > 0 ? sumArray[i + 1] : 0;
            sumArray[i] = sumArray[i] + A[i];
        }
        return sumArray;
    }

    public int[] findSubarrayMinSumRight(int[] A) {
        int[] sumArray = new int[A.length];
        sumArray[A.length - 1] = A[A.length - 1];
        for (int i = A.length - 2; i >= 0; i--) {
            sumArray[i] = sumArray[i + 1] < 0 ? sumArray[i + 1] : 0;
            sumArray[i] = sumArray[i] + A[i];
        }
        return sumArray;
    }

    public static void main(String[] args) {
        SubarrayMaxiAbsDiff s = new SubarrayMaxiAbsDiff();
        int[] A = {-5, 20, -4, 10, -18};
        int[] maxSumLeft = s.findSubarrayMaxSumLeft(A);
        int[] minSumLeft = s.findSubarrayMinSumLeft(A);
        int[] maxSumRight = s.findSubarrayMaxSumRight(A);
        int[] minSumRight = s.findSubarrayMinSumRight(A);
        
        System.out.println(Arrays.toString(maxSumLeft));
        System.out.println(Arrays.toString(minSumRight));
        System.out.println(Arrays.toString(minSumLeft));
        System.out.println(Arrays.toString(maxSumRight));
        System.out.println(s.maxAbsDif(A));
        
    }
}
