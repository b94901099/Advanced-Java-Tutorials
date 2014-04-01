/*
 * 给定一个包含n个整数的数组，除了一个数出现一次外所有的整数均出现三次，找出这个只出现一次的整数。
 */
package BitManipulation;

import java.util.*;

public class SingleNumberII {

    /**
     * int的32个bit逐个处理-这个方法还算简单的了
     * 我们知道如果每个元素重复出现三次，那么每一位出现1的次数也会是3的倍数，
     * 如果我们统计完对每一位进行取余3，那么结果中就只剩下那个出现一次的元素。
     * 总体只需要对数组进行一次线性扫描，统计完之后每一位进行取余3并且将位数字赋给结果整数，
     * 这是一个常量操作（因为整数的位数是固定32位），所以时间复杂度是O(n)。
     * 而空间复杂度需要一个32个元素的数组，也是固定的，因而空间复杂度是O(1)。代码如下：
     * @param A
     * @return
     */
    public int singleNumber(int[] A) {
        int[] countsPerBit = new int[32];
        int result = 0;
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < A.length; j++) {
                if (((A[j] >> i) & 1) == 1) {
                    countsPerBit[i] = (countsPerBit[i] + 1) % 3;
                }
            }
            result |= (countsPerBit[i] << i);
        }
        return result;
    }

    public static void main(String[] args) {
    }
}
