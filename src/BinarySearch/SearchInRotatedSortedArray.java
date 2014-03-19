package BinarySearch;

public class SearchInRotatedSortedArray {

    public int search(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        if (A[0] == target) {
            return 0;
        }
        if (A[A.length - 1] == target) {
            return A.length - 1;
        }

        int start = 0;
        int end = A.length - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[mid] > A[start]) {
                if (A[start] < target && target < A[mid]) {
                    end = mid;
                } else {
                    start = mid;
                }
            } else if (A[mid] < A[start]) {
                if (A[mid] < target && target < A[end]) {
                    start = mid;
                } else {
                    end = mid;
                }
            }
        }
        if (A[start] == target) {
            return start;
        }
        if (A[end] == target) {
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] i = {7,9,0,1,3,4,6};
        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
        System.out.println(s.search(i, 6));
    }
}
