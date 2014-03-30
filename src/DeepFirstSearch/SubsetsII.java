/*
 * Given a collection of integers that might contain duplicates, S, return all possible subsets.
 * Note: Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example, If S = [1,2,2], a solution is:
 * [ [2], [1], [1,2,2], [2,2], [1,2], [] ]
 */
package DeepFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;

public class SubsetsII {

    public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] num) {
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        ArrayList<Integer> list = new ArrayList();
        subsetsWithDupHelper(result, list, num, 0);
        return result;
    }

    private void subsetsWithDupHelper(ArrayList<ArrayList<Integer>> result,
            ArrayList<Integer> list, int[] num, int pos) {
        result.add(new ArrayList<Integer>(list));
        for (int i = pos; i < num.length; i++) {
            if (i != pos && num[i - 1] == num[i]) {
                continue;
            }
            list.add(num[i]);
            subsetsWithDupHelper(result, list, num, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] ia = {1, 2, 2, 2, 2};
        SubsetsII s = new SubsetsII();
        ArrayList<ArrayList<Integer>> result = s.subsetsWithDup(ia);
        for (ArrayList<Integer> list : result) {
            System.out.print("{");
            for (Integer in : list) {
                System.out.print(in + ", ");
            }
            System.out.println("}");
        }
    }
}
