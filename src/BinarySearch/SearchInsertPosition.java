package BinarySearch;

public class SearchInsertPosition {

    public static void main(String[] args) {
        SearchInsertPosition s = new SearchInsertPosition();
        int[] i = {0, 3, 5, 7, 9, 11, 13};
        System.out.println(s.searchInsertPosition(i, 4));
    }

    public int searchInsertPosition(int[] A, int target) {
        if (A == null || A.length == 0) {
            return -1;
        }
        if (target < A[0]) {
            return 0;
        }
        int count = 0;
        int start = 0;
        int end = A.length - 1;
        int mid = -1;
        while (start + 1 < end) {
            System.out.println("While" + count++);
            mid = (start + end) / 2;
            System.out.println("start = " + start + ", end = " + end + ", mid = " + mid);
            if (target == A[mid]) {
                return mid;
            }
            if (target < A[mid]) {
                end = mid;
            }
            if (target > A[mid]) {
                start = mid;
            }
        }
        System.out.println("End While:");
        System.out.println("start = " + start + ", end = " + end + ", mid = " + mid);
        if (target == A[end]) {
            return end;
        }
        if (target > A[end]) {
            return end + 1;
        }
        if (target > A[start]) {
            return start + 1;
        }
        return start;
    }
}
