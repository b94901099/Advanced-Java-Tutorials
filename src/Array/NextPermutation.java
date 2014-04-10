/*
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 * 
 * 这道题是给定一个数组和一个排列，求下一个排列。算法上其实没有什么特别的地方，
 * 主要的问题是经常不是一见到这个题就能马上理清思路。下面我们用一个例子来说明，
 * 比如排列是(2,3,6,5,4,1)，求下一个排列的基本步骤是这样：
 * 1) 先从后往前找到第一个不是依次增长的数，记录下位置p。比如例子中的3，对应的位置是1;
 * 2) 接下来分两种情况：
 * (1) 如果上面的数字都是依次增长的，那么说明这是最后一个排列，下一个就是第一个，
 * 其实把所有数字反转过来即可(比如(6,5,4,3,2,1)下一个是(1,2,3,4,5,6));
 * (2) 否则，如果p存在，从p开始往后找，找到下一个数就比p对应的数小的数字，然后两个调换位置，
 * 比如例子中的4。调换位置后得到(2,4,6,5,3,1)。最后把p之后的所有数字倒序，比如例子中得到(2,4,1,3,5,6), 
 * 这个即是要求的下一个排列。
 * 
 * 解釋二
 * O(n)， 其实也是BF而已，主要是找到做的方法。
 * 分三步：
 * 1. 从后往前找falling edge，下降沿。（下降之后的那个元素）
 * 2. 从下降沿开始往后找出替换它的元素。（就是第一个比它小的前一个元素）
 * 3. 反转后面所有元素，让他从小到大sorted（因为之前是从大到小sorted的）
 * 例如 “547532“
 * 1. “547532”， 4是下降沿。
 * 2. “547532”， 5是要替换的元素， 替换后得到 “ 557432”
 * 3. "557432",   7432反转，得到 “552347”。
 */
package Array;

import java.util.Arrays;

public class NextPermutation {

    public void nextPermutation2(int[] num) {
        if (num.length <= 1) {
            return;
        }

        // 1. 从后往前找falling edge，下降沿。（下降之后的那个元素）  
        int edge = -1;
        for (int i = num.length - 2; i >= 0; i--) {
            if (num[i] < num[i + 1]) {
                edge = i;
                break;
            }
        }

        if (edge > -1) {
            // 2. 从下降沿开始往后找出替换它的元素。（就是第一个比它小的前一个元素）  
            for (int i = edge + 1; i < num.length; i++) {
                if (num[edge] >= num[i]) {
                    nextPermutationSwap(num, edge, i - 1);
                    break;
                }
                if (i == num.length - 1) {
                    nextPermutationSwap(num, edge, i);
                    break;
                }
            }
        }

        // 3. 反转后面所有元素，让他从小到大sorted（因为之前是从大到小sorted的）  
        for (int i = edge + 1, j = num.length - 1; i <= edge + (num.length - edge - 1) / 2; i++, j--) {
            nextPermutationSwap(num, i, j);
        }
    }

    public void nextPermutationSwap(int[] num, int i, int j) {
        int tmp = num[i];
        num[i] = num[j];
        num[j] = tmp;
    }

    /**
     * @param num
     */
    public void nextPermutation(int[] num) {
        if (num == null || num.length == 0) {
            return;
        }

        int i = num.length - 1;

        while (i > 0 && num[i - 1] >= num[i])
            i--;

        if (i == 0) {
            reverseArray(num, 0, num.length - 1);
            return;
        }

        int toSwapIndex = i - 1;
        while (i < num.length && num[i] > num[toSwapIndex])
            i++;

        swap(num, toSwapIndex, i - 1);
        reverseArray(num, toSwapIndex + 1, num.length - 1);
    }

    private void swap(int[] num, int p, int q) {
        int tmp = num[p];
        num[p] = num[q];
        num[q] = tmp;
    }

    private void reverseArray(int[] num, int start, int end) {
        while (start < end) {
            int tmp = num[start];
            num[start] = num[end];
            num[end] = tmp;
            start++;
            end--;
        }
    }

    public static void main(String[] args) {
        NextPermutation n = new NextPermutation();

        int[] test1 = {1, 2, 3};
        int[] test2 = {3, 2, 1};
        int[] test3 = {5, 1, 1};
        n.nextPermutation2(test1);
        n.nextPermutation2(test2);
        n.nextPermutation2(test3);
        System.out.println(Arrays.toString(test1));
        System.out.println(Arrays.toString(test2));
        System.out.println(Arrays.toString(test3));

        int[] test4 = {1, 2, 3};
        int[] test5 = {3, 2, 1};
        int[] test6 = {5, 1, 1};
        n.nextPermutation(test4);
        n.nextPermutation(test5);
        n.nextPermutation(test6);
        System.out.println(Arrays.toString(test4));
        System.out.println(Arrays.toString(test5));
        System.out.println(Arrays.toString(test6));

    }
}
