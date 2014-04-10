/*
 * 原题: 排序只有1，2，3三个元素的数组，不能统计1，2，3的个数。
 * 分析
 * 这个题目，尽管也是排序，但却不能使用快速排序的方法。只有三个元素，如果时间复杂度仍旧是O(nlogn)，
 * 显然不是最好的。那就可以使用线性的排序算法，例如计数排序，可是题目中要求，不能够对1，2，3
 * 进行统计个数。那该如何处理呢？请大家看下面的方法，我们首先通过例子来说明：
 * 2	1	1	3	3	2
 * p1	p2				p3
 * 假设，我们有三个指针：p1、p2、p3.p1从左侧开始，指向第一个非1的数字；
 * p3从右侧开始，指向第一个非3的数字。p2从p1开始遍历，如果是2，p2继续遍历，直到p2遇到1或者3：
 * 如果遇到1，则和p1进行交换，然后p1向右，指向第一个非1的数字
 * 如果遇到3，则和p3进行交换，然后p3向左，指向第一个非3的数字
 * 
 * 基于快排划分的思路
 * 上面的思路，是针对三个数的，如果有更多的数，怎么处理呢？比如，4个，5个等等。下面根据快速的排序的启发，
 * 介绍一种算法，尽管在处理三个数的时候，比较次数会多些，但，具有很好的通用性。
 * 思路来自快排的划分部分，快排的划分部分：给定pivot，然后将数据划分为<=pivot和>pivot两部分。这样，三个数字时，需要两次划分：
 * 第一次，用1作为pivot,划分1到最左边；
 * 第二次，用2作为pivot，划分2到左边，则得到整体的排序。
 * 
 * 最巧妙的思路
 * 我们将1，2，3，替换为互质的2，3，5，得到如下：
 * 2	1	1	3	3	2
 * 3	2	2	5	5	3
 * 之后，乘起来得到的900.这900里，除以2，有多少个2，就有多少个1；然后除以3，有多少个3，
 * 就有多少个3对应的2；然后除以5，有多少个5，就有多少个5对应的3。这是如何保证的呢？因为2，3，5是互质的。
 * 如下：
 * 被除数	除数	商	余数	排序结果
 * 900	2	450	0	1
 * 450	2	225	0	1
 * 225	2	112	1	2尝试结束，尝试3
 * 225	3	75	0	2
 * 75	3	25	0	2
 * 25	3	8	1	3尝试结束，尝试5
 * 25	5	5	0	3
 * 5	5	1	0	3
 * 1	5	1	1	全部结束
 * 最终结果为112233.上面的这种思路，实际上是计数的一种变种。没有直接的技术，那自然就是可以的。
 */
package CodeChallange;

import java.util.Arrays;

public class Sort123 {

    public void sort123(int[] A) {
        int p1 = 0, p2 = 0, p3 = A.length - 1;
        while (A[p1] == 1) {
            p1++;
        }
        while (A[p3] == 3) {
            p3--;
        }
        p2 = p1;
        while (p2 <= p3) {
            if (A[p2] == 1) {
                int tmp = A[p1];
                A[p1] = A[p2];
                A[p2] = tmp;
                p1++;
            } else if (A[p2] == 3) {
                int tmp = A[p3];
                A[p3] = A[p2];
                A[p2] = tmp;
                p3--;
            }
            p2++;
        }
    }

    public void sort123Quick(int[] A) {
        int p1 = -1;
        int p2 = A.length;
        int pivot = 1;
        while (p1 < p2) {
            while (A[++p1] <= pivot);
            while (p2 > 0 && A[--p2] > pivot);
            if (p1 >= p2) {
                break;
            } else {
                swap(A, p1, p2);
            }
        }
        p2 = A.length;
        pivot = 2;
        while (p1 < p2) {
            while (A[++p1] <= pivot);
            while (p2 > 0 && A[--p2] > pivot);
            if (p1 >= p2) {
                break;
            } else {
                swap(A, p1, p2);
            }
        }
    }

    private void swap(int[] A, int p1, int p2) {
        int tmp = A[p1];
        A[p1] = A[p2];
        A[p2] = tmp;
    }

    public static void main(String[] args) {
        int[] A = {2, 1, 1, 3};
        Sort123 s = new Sort123();
        s.sort123(A);
        System.out.println(Arrays.toString(A));
        int[] A2 = {3, 2, 1, 1, 3};
        s.sort123Quick(A2);
        System.out.println(Arrays.toString(A2));
    }
}
