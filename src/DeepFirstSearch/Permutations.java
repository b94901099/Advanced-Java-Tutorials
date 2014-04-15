/*
 * Given a collection of numbers, return all possible permutations.
 * For example,
 * [1,2,3] have the following permutations:
 * [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].
 */

package DeepFirstSearch;

import java.util.*;

public class Permutations {

    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        ArrayList<Integer> list = new ArrayList();
        HashSet<Integer> set = new HashSet();
        permuteHelper(result, list, set, num);
        return result;
    }

    private void permuteHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list,
            HashSet<Integer> set, int[] num) {
        if (list.size() == num.length) {
            result.add(new ArrayList(list));
            return;
        }

        for (int i = 0; i < num.length; i++) {
            if (set.contains(num[i])) {
                continue;
            }
            list.add(num[i]);
            set.add(num[i]);
            permuteHelper(result, list, set, num);
            list.remove(list.size() - 1);
            set.remove(num[i]);
        }
    }

    public static void main(String[] args) {
        int[] ia = {1, 2, 3};
        Permutations p = new Permutations();
        ArrayList<ArrayList<Integer>> result = p.permute(ia);
        for (ArrayList<Integer> list : result) {
            System.out.println(list);
        }
    }
}
