package DeepFirstSearch;

import java.util.*;

public class Permutations {

    public ArrayList<ArrayList<Integer>> permute(int[] num) {
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        ArrayList<Integer> list = new ArrayList();
        HashSet<Integer> set = new HashSet();
        permuteHelper(result, list, set, num, 0);
        return result;
    }

    private void permuteHelper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list,
            HashSet<Integer> set, int[] num, int pos) {
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
            permuteHelper(result, list, set, num, i + 1);
            list.remove(list.size() - 1);
            set.remove(num[i]);
        }
    }

    public static void main(String[] args) {
        int[] ia = {1, 2, 3};
        Permutations p = new Permutations();
        ArrayList<ArrayList<Integer>> result = p.permute(ia);
        for (ArrayList<Integer> list : result) {
            System.out.print("{");
            for (Integer in : list) {
                System.out.print(in + ", ");
            }
            System.out.println("}");
        }
    }
}
