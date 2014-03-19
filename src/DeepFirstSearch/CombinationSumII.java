/*
 * Given a collection of candidate numbers (C) and a target number (T), 
 * find all unique combinations in C where the candidate numbers sums to T.
 * Each number in C may only be used once in the combination.
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
 * A solution set is: 
 * [[1, 7], [1, 2, 5], [2, 6], [1, 1, 6]]
 */
package DeepFirstSearch;

import java.util.*;

public class CombinationSumII {

    public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        Arrays.sort(num);
        combinationSumHelper(num, target, result, list, 0);
        return result;
    }

    private void combinationSumHelper(int[] num, int target, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int pos) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList(list));
            return;
        }
        for (int i = pos; i < num.length; i++) {
            list.add(num[i]);
            combinationSumHelper(num, target - num[i], result, list, i + 1);
            list.remove(list.size() - 1);
            while (i < num.length - 1 && num[i] == num[i + 1]) {
                i++;
            }
            // 跟 subsetII 和 permutation II 一樣的思路
            // 防止同樣的 set 產生
        }
    }

    public static void main(String[] args) {
        CombinationSumII cs = new CombinationSumII();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        ArrayList<ArrayList<Integer>> result = cs.combinationSum2(candidates, target);
        for (ArrayList<Integer> list : result) {
            System.out.print("[");
            for (Integer i : list) {
                System.out.print(i + ", ");
            }
            System.out.println("]");
        }
    }
}
