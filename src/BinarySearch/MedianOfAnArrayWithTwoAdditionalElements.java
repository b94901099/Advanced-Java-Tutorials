/*
 *  Find the median of n+2 elements: n elements from sorted array a, in addition to 2 new elements e1, e2.
 */
package BinarySearch;

public class MedianOfAnArrayWithTwoAdditionalElements {

    public double median(int[] A, int e1, int e2) {
        if (e1 > e2) {
            int tmp = e1;
            e1 = e2;
            e2 = e1;
        }
        
        int insertIndexE1 = searchInsertionIndex(A, e1);
        int insertIndexE2 = searchInsertionIndex(A, e2);
        int size = A.length + 2;
        if (size % 2 == 1) {
            return element(A, size / 2, e1, insertIndexE1, e2, insertIndexE2);
        }
        
        double median = (element(A, size / 2 - 1, e1, insertIndexE1, e2, insertIndexE2)
                + element(A, size / 2, e1, insertIndexE1, e2, insertIndexE2)) / 2.0;
        return median;
    }

    private int element(int[] A, int i, int e1, int insertE1, int e2, int insertE2) {
        if (i < insertE1) {
            return A[i];
        } else if (i == insertE1) {
            return e1;
        } else if (insertE1 < i && i <= insertE2) { // 注意 '='
            return A[i - 1];
        } else if (i == insertE2 + 1) { //注意邊界範圍的移動
            return e2;
        } else {
            return A[i - 2];
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
        MedianOfAnArrayWithTwoAdditionalElements m = new MedianOfAnArrayWithTwoAdditionalElements();
        int[] oddA = {1, 4, 7, 10, 13};
        int[] evenA = {1, 4, 7, 8, 9, 10, 13, 16};
        System.out.println(m.median(oddA, 8, 9));
        System.out.println(m.median(evenA, 8, 9));
    }
}
