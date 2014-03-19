/*
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * For example, If n = 4 and k = 2, a solution is:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 */
package DeepFirstSearch;

import java.util.ArrayList;

public class Combinations {

    public ArrayList<ArrayList<Integer>> combine(int n, int k) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        combineHelper(n, k, result, list, 1);
        return result;
    }

    private void combineHelper(int n, int k, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list, int pos) {
        if (list.size() > k) {
            return;
        }
        if (list.size() == k) {
            result.add(new ArrayList(list));
            return;
        }
        for (int i = pos; i <= n; i++) {
            list.add(i);
            combineHelper(n, k, result, list, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
    }
}
