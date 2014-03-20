/*
 * Follow up for "Search in Rotated Sorted Array": What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * Write a function to determine if a given target is in the array.
 * 
 * 思路:
 * 考慮 1111111811
 * 無法二分, 只有線性搜索
 * 
 */
package BinarySearch;

public class SearchinRotatedSortedArrayII {

    public boolean search(int[] A, int target) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == target) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
    }
}
