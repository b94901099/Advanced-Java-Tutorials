package BinarySearch;

import java.util.Arrays;

public class SearchForARange {

    public static void main(String[] args) {
        SearchForARange s = new SearchForARange();
        int[] A = {1, 3, 8, 8, 8, 8, 10, 12};
        int[] B = {2, 2};
        System.out.println(Arrays.toString(s.searchRange(A, 8)));
        System.out.println(Arrays.toString(s.searchRange(B, 2)));
    }

    public int[] searchRange(int[] A, int target) {
        int[] bound = {-1, -1};
        if (A == null || A.length == 0) {
            return bound;
        }
        if (A[0] > target) {
            return bound;
        }
        if (A[A.length - 1] < target) {
            return bound;
        }

        int start = 0;
        int end = A.length - 1;
        int mid = -1;
        System.out.println("leftbound: ");
        //find left bound
        while (start + 1 < end) {
            mid = (start + end) / 2;
            System.out.println("start = " + start + ", end = " + end + ", mid = " + mid);
            if (A[mid] == target) {
                end = mid;
            }
            if (A[mid] > target) {
                end = mid;
            }
            if (A[mid] < target) {
                start = mid;
            }
        }
        System.out.println("End While:");
        System.out.println("start = " + start + ", end = " + end + ", mid = " + mid);
        if (A[end] == target) {
            bound[0] = end;
        }
        if (A[start] == target) {
            bound[0] = start;
        }

        start = 0;
        end = A.length - 1;
        mid = -1;
        System.out.println("\nright bound:");
        //find right bound
        while (start + 1 < end) {
            mid = (start + end) / 2;
            System.out.println("start = " + start + ", end = " + end + ", mid = " + mid);
            if (A[mid] == target) {
                start = mid;
            }
            if (A[mid] > target) {
                end = mid;
            }
            if (A[mid] < target) {
                start = mid;
            }
        }
        System.out.println("End While:");
        System.out.println("start = " + start + ", end = " + end + ", mid = " + mid);

        if (A[start] == target) {
            bound[1] = start;
        }
        if (A[end] == target) {
            bound[1] = end;
        }
        return bound;
    }
}
