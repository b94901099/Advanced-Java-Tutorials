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
 * 普遍來說有以下兩種情況:
 * b[j] < a[i] < b[j + 1]: a[i] 是 a 中第 i 大, 而且 比 b 裡面 j 個element 大,
 * a[i] < b[j] < a[i + 1]: 反之亦然
 * 因為 k 從 1 開始算, 所以a,b index 都得加 1, 得到我們的目標: i + j + 2 = k
 * 就可以得到全部第 k 大的 element, 在達到目標時分以下兩種情況:
 * If b[j] < a[i] < b[j + 1], then a[i] must be the k-th smallest,
 * else if a[i] < b[j] < a[i + 1], then b[j] must be the k-th smallest.
 * 另外, 如何移動 i, j? 哪部分要捨棄? a[i], b[j] 本身如何考慮?
 * 在 a[i] < b[j + 1] 為真, 但是 b[j] < a[i] 不為真時, a[i] < b[j] < [j + 1]
 * 當  i + j + 2 < k 時, 從 a[i] 往前都是可以捨棄的
 * 當  i + j + 2 > k 時, 從 b[j + 1] 往後都是可以捨棄的
 * 同樣的, 另一條件也有類似特性
 * 
 * 
 */
package BinarySearch;

public class FindKthLargestElementInTwoSortedArrays {

    public int linearSearch(int[] a, int[] b, int k) {
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
        FindKthLargestElementInTwoSortedArrays f = new FindKthLargestElementInTwoSortedArrays();
        System.out.println(f.linearSearch(a, b, 6));
    }
}
