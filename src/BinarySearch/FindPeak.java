/*
 *查找峰值。假设有一个数组,相邻两数都不相等，且A[0]<A[1], A[length-2] > A[length-1] 。
 * 如果A[i] > A[i-1] && A[i] > A[i+1]，那么认为A[i]是一个峰值。
 * 数组中可能存在多个峰值。给定A，找到任意一个峰值。
 */
package BinarySearch;

public class FindPeak {

    public int findPeek(int[] A) {
        if (A == null || A.length < 3) {
            return -1;
        }
        int start = 1;
        int end = A.length - 2;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] > A[mid - 1] && A[mid] > A[mid + 1]) {
                return mid;
            } else if (A[mid] > A[mid - 1] && A[mid] < A[mid + 1]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[start] > A[start - 1] && A[start] > A[start + 1]) {
            return start;
        }
        if (A[end] > A[end - 1] && A[end] > A[end + 1]) {
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
    }
}
