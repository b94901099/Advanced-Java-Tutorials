package BinarySearch;

public class BinearySearch {

    public int binearySearch(int[] A, int target) {
        int end = A.length - 1;
        int start = 0;
        int mid;
        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (A[mid] == target) {
                return mid;
            }
            if (A[mid] > target) {
                end = mid;
            }
            if (A[mid] < target) {
                start = mid;
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
}
