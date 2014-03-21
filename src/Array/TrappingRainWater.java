/*
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 * For example, given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 * 
 * 下面我们说说优化的算法。这种方法是基于动态规划的，基本思路就是维护一个长度为n的数组，
 * 进行两次扫描，一次从左往右，一次从右往左。第一次扫描的时候维护对于每一个bar左边最大的高度是多少，
 * 存入数组对应元素中，第二次扫描的时候维护右边最大的高度，
 * 并且比较将左边和右边小的最大高度（我们成为瓶颈）存入数组对应元素中。
 * 这样两遍扫描之后就可以得到每一个bar能承受的最大水量，从而累加得出结果。
 * 这个方法只需要两次扫描，所以时间复杂度是O(2*n)=O(n)。空间上需要一个长度为n的数组，复杂度是O(n)。
 * 類似 Candy 的作法 
 */
package Array;

import java.util.Arrays;

public class TrappingRainWater {

    public int trap(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int max = 0;
        int res = 0;
        int[] container = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            container[i] = max; // 紀錄該格左邊最高的 bar
            max = Math.max(max, A[i]);
        }
        max = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            container[i] = Math.min(max, container[i]); // 右邊最大bar跟左邊最大bar比較, 取小者
            max = Math.max(max, A[i]); // 紀錄右邊最高的 bar
            res += container[i] - A[i] > 0 ? container[i] - A[i] : 0; // 若左右最大 bar 的低 bar 高於 A[i], 代表能儲水, 計算儲下來的水
        }
        return res;
    }

    public static void main(String[] args) {
        TrappingRainWater t = new TrappingRainWater();
        int[] A = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(t.trap(A));
    }
}
