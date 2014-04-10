/*
 * 一个数组A[1...n]，满足A[1]>=A[2], A[n] >= A[n-1]。A[i]被成为波谷，
 * 意味着：A[i-1] >= A[i] <= A[i+1]。请给出一个算法，找到数组中的一个波谷。
 * 
 * 分析
 * 这个题目遍历一遍数组，显然就可以找到全部的波谷。时间复杂度O(n)，空间复杂度是O(1)。
 * 但是如果我们只需要找到一个波谷，是否有更快的方法呢？更快的方法O(1)是不可能的，那只有O(logn)，
 * 自然就想到二分查找。这个题目如果进行二分呢？我们看下面的数组A：
 * 0	1	2	3	4	5	6
 * 9	7	7	2	1	3	7
 * left			mid			right
 * A[0]>A[1]，A[6]>A[5]，满足题目中要求数组的条件。满足这样的条件，数组中一定是存在波谷的。
 * 假设不存在波谷，则A[0]<A[1]，A[6]<A[5]，与条件相悖。
 * 上表中的mid=3，A[mid]=2。A[mid-1]<A[mid]<A[mid+]。根据上面的分析，[mid, 6]一定有一个波谷。所以处理有半部分
 * 0	1	2	3	4	5	6
 * 9	7	7	2	1	3	7
 * left	mid		right
 * 此时，mid满足波谷的条件，找到波谷。总结上面的思路，找到数组的mid元素，mid有几种情况：
 * 如果A[mid-1]>=A[mid] && A[mid+1]>=A[mid]，找到波谷；
 * 如果A[mid-1]<=A[mid]<A[mid+1]，right=mid，在左侧继续找；
 * 如果A[mid+1]<=A[mid]<A[mid-1]，left=mid，在右侧继续找；
 * 如果A[mid-1]<A[mid] && A[mid+1]<A[mid]，任意一侧都可以，任意一侧，都必将存在波谷。
 * 进一步分析
 * 如果这个题目中，没有A[1]>=A[2]以及A[n]>=A[n-1]的约束，还能够以O(logn)的时间复杂度完成么？
 * 关键点就在于，中间的元素与其相邻的元素进行大小比较的时候，还能否选择一边继续进行查找。
 * 当A[mid-1]<=A[mid]时，可能是A[1]<A[2]<…<A[mid-1]<=A[mid]是不存在波谷的。
 * 所以，在左侧查找是找不到波谷的。所以，如果没有原题中的条件，两边都要进行遍历查找，我们的递归式应该是如下的形式：
 * T(n)=2T(n/2)+1
 * 根据主定理，解得，时间复杂度为O(n).此时，是不存在O(logn)的。
 */
package CodeChallange;

public class LocalMinimum {

    public int findMinIndex(int[] A) {
        int start = 0;
        int end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] < A[mid + 1] && A[mid] < A[mid - 1]) {
                return mid;
            }
            if (A[start] < A[mid]) {
                end = mid;
            } else {
                start = mid;
            }
        }
        if (A[start - 1] > A[start] && A[start] < A[start + 1]) {
            return start;
        }
        if (A[end - 1] > A[end] && A[end] < A[end + 1]) {
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] A = {3, 2, 1, 2, 3, 1, 4, 3, 5};
        LocalMinimum l = new LocalMinimum();
        System.out.println(l.findMinIndex(A));
    }
}