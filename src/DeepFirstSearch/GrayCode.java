/*
 * 
 */
package DeepFirstSearch;

import java.util.*;

public class GrayCode {

    // 数学解：从第0个开始，第i个gray code为：(i >> 1) ^ i
    public ArrayList<Integer> grayCode2(int n) {
        ArrayList<Integer> results = new ArrayList<Integer>();
        int N = 1 << n;
        for (int i = 0; i < N; ++i) {
            results.add((i >> 1) ^ i);
        }
        return results;
    }

    public ArrayList<Integer> grayCode(int n) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if (n < 0) {
            return result;
        }

        if (n == 0) {
            result.add(0);
            return result;
        }

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = (int) Math.pow(2, i);
        }

        codeHelper(nums, result, 0, 0);
        return result;
    }

    private void codeHelper(int[] nums, ArrayList<Integer> result, int val, int pos) {
        result.add(val);
        if (pos == nums.length) {
            return;
        }
        for (int i = pos; i < nums.length; i++) {
            codeHelper(nums, result, val + nums[i], i + 1);
        }
    }

    public static void main(String[] args) {
        GrayCode g = new GrayCode();
        ArrayList<Integer> list = g.grayCode(3);
        for (int i : list) {
            System.out.print(i + ", ");
        }
        
        System.out.println("\n\n數學解:");
        ArrayList<Integer> list2 = g.grayCode2(3);
        for (int i : list2) {
            System.out.print(i + ", ");
        }

    }
}
