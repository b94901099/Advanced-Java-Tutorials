package BinarySearch;

/**
 * http://decomplexify.blogspot.com/
 *
 * 給定一個 Array 和額外 elements, 找到median
 * Find the median of n+1 elements: n elements from sorted array a, in addition to a new element e.
 *
 * 注意: 運用 helper method 控制返回 index, 避免 insertion
 */

public class MedianOfAnArrayWithOneAdditionalElement {

    /*
     * array 加上一個額外 element, 求 median
     * 思路: 用binary search 找到 element 應該安插的index, 在不改動 array 的情況下求 median
     * SearchInsertPosition
     * 
     * First, we define a helper function that finds an element by index in the new array
     * obtained by inserting a new element into a given array at a given position without actually modifying the old array:
     */
    double median1(int A[], int e) {
        int insertIndex = searchInsertionIndex(A, e);
        int size = A.length + 1;
        if (size % 2 == 1) {
            return element1(A, size / 2, e, insertIndex);
        }
        return (element1(A, size / 2 - 1, e, insertIndex) + element1(A, size / 2, e, insertIndex)) / 2;
    }

    /* 
     * 這個 method 模擬了把 e 加進 array 的行為, 但實際上並沒有加進去
     * i是想要求的 index, j 是 e 的插入位置
     */
    double element1(int A[], int i, int e, int insertionIndexOfE) {
        if (i < insertionIndexOfE) {
            return A[i];
        } else if (i == insertionIndexOfE) {
            return e;
        } else {
            return A[i - 1];
        }
    }

    private int searchInsertionIndex(int[] A, int toInsert) {
        int start = 0, end = A.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (A[mid] == toInsert) {
                return mid;
            }
            if (A[mid] < toInsert) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (A[end] < toInsert) {
            return end + 1;
        } else if (A[end] == toInsert) {
            return end;
        } else if (A[start] < toInsert) {
            return start + 1;
        } else {
            return start;
        }
    }

    public static void main(String[] args) {
        MedianOfAnArrayWithOneAdditionalElement m = new MedianOfAnArrayWithOneAdditionalElement();
        int[] oddA = {1, 3, 5, 7, 9};
        int[] evenA = {1, 3, 5, 7, 9, 11};
        System.out.println(m.median1(oddA, 6));
        System.out.println(m.median1(evenA, 6));
    }
}
