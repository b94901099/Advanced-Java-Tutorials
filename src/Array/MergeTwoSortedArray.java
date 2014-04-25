package Array;

/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * Note:
 * You may assume that A has enough space to hold additional elements from B.
 * The number of elements initialized in A and B are m and n respectively.
 */

public class MergeTwoSortedArray {

    public void merge(int A[], int m, int B[], int n) {
        if (A == null || B == null) {
            return;
        }

        int indexPos = m + n - 1;
        int indexA = m - 1;
        int indexB = n - 1;

        while (indexA >= 0 && indexB >= 0) {
            if (A[indexA] >= B[indexB]) {
                A[indexPos--] = A[indexA--];
            } else {
                A[indexPos--] = B[indexB--];
            }
        }

        while (indexB >= 0) {
            A[indexPos--] = B[indexB--];
        }
    }

    public static void main(String[] args) {
    }
}
