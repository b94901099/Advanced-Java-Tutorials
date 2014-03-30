/*
 * Given a set of distinct integers, S, return all possible subsets.
 * Note: Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 * For example, If S = [1,2,3], a solution is:
 * [ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 */
package DeepFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;

public class Subsets {

    public ArrayList<ArrayList<Integer>> subsets(int[] num) {
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        subsetsHelper(result, list, num, 0);
        return result;
    }

    private void subsetsHelper(ArrayList<ArrayList<Integer>> result,
            ArrayList<Integer> list, int[] num, int pos) {
        result.add(new ArrayList<Integer>(list));
        System.out.println("result.add(new Array(list)) " + pos + ", result.size = " + result.size() + ", list.size = " + list.size());
        for (int i = pos; i < num.length; i++) {
            list.add(num[i]);
            System.out.println("list.add" + num[i]);
            subsetsHelper(result, list, num, i + 1);
            System.out.println("Helper(" + result.size() + ", " + list.size() + ", " + num.length + ", " + (i + 1));
            list.remove(list.size() - 1);
            System.out.println("list.remove(" + (list.size() - 1) + ")");
        }
    }

    public static void main(String[] args) {
        int[] i = {10, 20, 30, 40};
        Subsets s = new Subsets();
        ArrayList<ArrayList<Integer>> result = s.subsets(i);
        for (ArrayList<Integer> list : result) {
            System.out.print("{");
            for (Integer in : list) {
                System.out.print(in + ", ");
            }
            System.out.println("}");
        }
    }
}
