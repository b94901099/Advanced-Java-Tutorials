package DeepFirstSearch;

import java.util.ArrayList;
import java.util.Arrays;

// http://blog.csdn.net/u011095253/article/details/9158397
// 用 while : 
// 我们需要做的是，在删去元素后，再取元素的时候，
// 不要取和刚刚取过的元素相等的元素 即加上这么一条语句
//  while(i<num.length-1 && num[i]==num[i+1]) i++; 唯一的区别就在这一行 
class Permutation2 {

    public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> tmp = new ArrayList<Integer>();
        boolean[] visit = new boolean[num.length];
        Arrays.sort(num);
        dfs(res, tmp, num, visit);
        return res;
    }

    public void dfs(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> tmp, int[] num, boolean[] visit) {
        if (tmp.size() == num.length) {
            res.add(new ArrayList<Integer>(tmp));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (visit[i] == false) {
                tmp.add(num[i]);
                visit[i] = true;
                dfs(res, tmp, num, visit);
                tmp.remove(tmp.size() - 1);
                visit[i] = false;
                while (i < num.length - 1 && num[i] == num[i + 1]) {
                    i++;
                } //唯一的区别就在这一行
            }
        }
    }
}

public class PermutationsII {

    public ArrayList<ArrayList<Integer>> permutWithDup(int[] num) {
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> result = new ArrayList();
        ArrayList<Integer> list = new ArrayList();
        boolean[] visited = new boolean[num.length];
        helper(result, list, num, visited);
        return result;
    }

    private void helper(ArrayList<ArrayList<Integer>> result,
            ArrayList<Integer> list, int[] num, boolean[] visited) {
        if (list.size() == num.length) {
            result.add(new ArrayList(list));
            return;
        }
        for (int i = 0; i < num.length; i++) {
            if (visited[i] || (i != 0 && num[i] == num[i - 1] && !visited[i - 1])) {
                continue;
            }
            list.add(num[i]);
            visited[i] = true;
            helper(result, list, num, visited);
            list.remove(list.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        int[] ia = {1, 1, 2};
        PermutationsII p = new PermutationsII();
        ArrayList<ArrayList<Integer>> result = p.permutWithDup(ia);
        for (ArrayList<Integer> list : result) {
            System.out.print("{");
            for (Integer in : list) {
                System.out.print(in + ", ");
            }
            System.out.println("}");
        }
    }
}
