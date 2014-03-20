/*
 * 有一类数组，例如数组[1,2,3,4,6,8,9,4,8,11,18,19,100] 前半部分是是一个递增数组，后面一个还是递增数组，
 * 但整个数组不是递增数组，那么怎么最快的找出其中一个数？
 * 分析：此题数组不是严格递增的数据，因为有重复的元素。对数组的前半部分和后半部分分别进行二分查找。
 */
package BinarySearch;

import java.util.*;

public class SearchInSortedOverlappingArray {

    public ArrayList<Integer> search(int[] nums, int target) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (nums == null || nums.length < 1) {
            result.add(-1);
            return result;
        }
        int leftNum = getLeftNum(nums); // 找出分界點, 各作 binary search
        if (leftNum == -1) { //沒有分界點
            int tmp = binarySearch(nums, target);
            result.add(tmp);
            return result;
        }
        int[] left = new int[leftNum];
        System.arraycopy(nums, 0, left, 0, leftNum);
        System.out.println("left array: " + Arrays.toString(left));
        int[] right = new int[nums.length - leftNum];
        System.arraycopy(nums, leftNum, right, 0, right.length);
        System.out.println("right array: " + Arrays.toString(right));
        
        int leftSearch = binarySearch(left, target);
        int rightSearch = binarySearch(right, target);
        if (leftSearch != -1) {
            result.add(leftSearch + 1);
        }
        if (rightSearch != -1) {
            result.add(rightSearch + leftNum + 1);
        }
        if (result.size() == 0) {
            result.add(-1);
        }
        return result;
    }

    private int getLeftNum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                return i;
            }
        }
        return -1;
    }

    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        if (nums[start] == target) {
            return start;
        }
        if (nums[end] == target) {
            return end;
        }
        return -1;
    }

    public static void main(String[] args) {
        SearchInSortedOverlappingArray s = new SearchInSortedOverlappingArray();
        int[] test = {1, 2, 3, 4, 6, 8, 9, 4, 8, 11, 18, 19, 100};
        System.out.println(s.search(test, 8));
        System.out.println(s.getLeftNum(test));
    }
}
