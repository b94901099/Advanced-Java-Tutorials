package BinarySearch;

/**
 * Given two sorted arrays A, B of size m and n respectively.
 * Find the k-th smallest element in the union of A and B. You can assume that there are no duplicate elements.
 * Given:
 * a[] = {2, 3, 7, 12, 27, 81, 91}
 * b[] = {1, 25, 32, 74, 89}
 * k = 4
 * Return: 7
 * <p/>
 * 解法一: O(m + n)
 * 線性搜索直到兩個 index 加起來為 k
 * <p/>
 * 解法二: O(log(m + n))
 * http://blog.csdn.net/lllcfr/article/details/18092663
 */

public class FindKthSmallestElementInTwoSortedArrays {

    public int findKth(int[] A, int A_start, int[] B, int B_start, int k) {

        if (A_start >= A.length) {
            return B[B_start + k - 1];
        }

        if (B_start >= B.length) {
            return A[A_start + k - 1];
        }

        if (k == 1) {
            return Math.min(A[A_start], B[B_start]);
        }

        int A_key = A_start + k / 2 - 1 < A.length ? A[A_start + k / 2 - 1] : Integer.MAX_VALUE;
        int B_key = B_start + k / 2 - 1 < B.length ? B[B_start + k / 2 - 1] : Integer.MAX_VALUE;

        if (A_key < B_key) {
            return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
        } else {
            return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
        }
    }

    public int linearSearchK(int[] a, int[] b, int k) {
        int i = 0, j = 0;

        for (int time = 0; time < k - 1; time++) {
            if (i + j + 2 == k) {
                if (a[i] < b[j]) return a[i];
                else return b[j];
            } else {
                if (i == a.length) i++;
                else if (j == b.length) j++;
                else if (a[i] < b[j]) i++;
                else j++;
            }
        }

        return Math.min(a[i], b[j]);
    }

    public static void main(String[] args) {
        int a[] = {1, 2, 3, 4, 5, 6};
        int b[] = {7, 8, 9, 10, 11, 12};
        FindKthSmallestElementInTwoSortedArrays f = new FindKthSmallestElementInTwoSortedArrays();
        System.out.println(f.linearSearchK(a, b, 6));
        System.out.println(f.findKth(a, 0, b, 0, 6));
    }
}
