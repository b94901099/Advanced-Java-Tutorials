/* 
 * Given two sorted arrays A, B of size m and n respectively. 
 * Find the k-th smallest element in the union of A and B. You can assume that there are no duplicate elements.
 * Given:
 * a[] = {2, 3, 7, 12, 27, 81, 91}
 * b[] = {1, 25, 32, 74, 89}
 * k = 4
 * Return: 7
 * 
 * 解法一: O(m + n)
 * 線性搜索直到兩個 index 加起來為 k
 * 
 * 解法二: O(log(m + n))
 * http://blog.csdn.net/lllcfr/article/details/18092663
 * 
 */
package BinarySearch;

public class FindKthSmallestElementInTwoSortedArrays {
    
    public int binarySearchK(int[] a, int[] b, int k) {
        
    }

    public int linearSearchK(int[] a, int[] b, int k) {
        int i = 0, j = 0;
        while (i < a.length && j < b.length) {
            if (i + j + 2 == k) { // k 從 1 開始, i/j 從 0
                if (a[i] < b[j]) {
                    return b[j];
                } else {
                    return a[i];
                }
            } else {
                if (i == a.length) {
                    j++;
                } else if (j == b.length) {
                    i++;
                } else if (a[i] < b[j]) {
                    i++;
                } else {
                    j++;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int a[] = {2, 3, 7, 12, 27, 81, 91};
        int b[] = {1, 25, 32, 74, 89};
        FindKthSmallestElementInTwoSortedArrays f = new FindKthSmallestElementInTwoSortedArrays();
        System.out.println(f.linearSearchK(a, b, 6));
    }
}
