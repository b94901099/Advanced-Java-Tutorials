/*
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
 * Find all unique triplets in the array which gives the sum of zero.
 * Note:
 * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
 * The solution set must not contain duplicate triplets.
 * For example, given array S = {-1 0 1 2 -1 -4},
 * A solution set is: (-1, 0, 1), (-1, -1, 2)
 */
package TwoPointers;

import java.util.*;

public class ThreeSum {

    public ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        if (num == null) {
            return null;
        }
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        if (num.length < 3) {
            return result;
        }
        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {

            if (i > 0 && num[i] == num[i - 1]) {
                continue;
            }

            int p = i + 1;
            int q = num.length - 1;
            while (p < q) {
                if (num[i] + num[p] + num[q] == 0) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(num[i]);
                    list.add(num[p]);
                    list.add(num[q]);
                    result.add(list);
                    do {
                        p++;
                    } while (p < q && num[p] == num[p - 1]);
                    do {
                        q--;
                    } while (p < q && num[q] == num[q + 1]);
                } else if (num[i] + num[p] + num[q] > 0) {
                    do {
                        q--;
                    } while (p < q && num[q] == num[q + 1]);
                } else {
                    do {
                        p++;
                    } while (p < q && num[p] == num[p - 1]);
                }
            }
        }
        return result;
    }


    // 有 bug
    public ArrayList<ArrayList<Integer>> threeSum2(int[] num) {
        if (num == null)
            return null;

        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();

        if (num.length < 3) {
            return result;
        }

        Arrays.sort(num);

        for (int i = 0; i < num.length - 2; i++) {
            for (int j = i + 1; j < num.length - 1; j++) {
                int sum = num[i] + num[j];
                if (contains(num, j + 1, sum * -1)) {
                    ArrayList<Integer> list = new ArrayList<Integer>();
                    list.add(num[i]);
                    list.add(num[j]);
                    list.add(-sum);
                    result.add(list);
                }
                while (j > i && j < num.length - 1 && num[j] == num[j - 1])
                    j++;
            }
            while (i > 0 && i < num.length - 2 && num[i] == num[i - 1])
                i++;
        }

        return result;
    }

    private boolean contains(int[] num, int start, int val) {
        int end = num.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (num[mid] == val)
                return true;
            if (num[mid] > val)
                end = mid;
            else
                start = mid;
        }
        if (num[start] == val) return true;
        if (num[end] == val) return true;
        return false;
    }


    public static void main(String[] args) {
        int[] num = {-13, 10, 11, -3, 8, 11, -4, 8, 12, -13, 5, -6, -4, -2, 12, 11, 7, -7,
                -3, 10, 12, 13, -3, -2, 6, -1, 14, 7, -13, 8, 14, -10, -4, 10, -6, 11, -2,
                -3, 4, -13, 0, -14, -3, 3, -9, -6, -9, 13, -6, 3, 1, -9, -6, 13, -4, -15,
                -11, -12, 7, -9, 3, -2, -12, 6, -15, -10, 2, -2, -6, 13, 1, 9, 14, 5, -11,
                -10, 14, -5, 11, -6, 6, -3, -8, -15, -13, -4, 7, 13, -1, -9, 11, -13, -4,
                -15, 9, -4, 12, -4, 1, -9, -5, 9, 8, -14, -1, 4, 14};

        ThreeSum t = new ThreeSum();
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        result = t.threeSum2(num);
        System.out.println(result.size());
//        for (ArrayList<Integer> list : result) {
//            System.out.println(list.toString());
//        }
    }
}
