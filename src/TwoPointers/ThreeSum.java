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

    public static void main(String[] args) {
    }
}
