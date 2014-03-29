/*Given a set of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7, 
 * A solution set is: 
 * [7] 
 * [2, 2, 3] 
 */

package DeepFirstSearch;
/*很像传统的硬币问题，我们同样运用Subset和Permutation的思路，只需要考虑两个问题：
 * 1. 什么时候返回？
 * 2. 递归的时候传入什么样的参数
 * 对于上面第二点，我们不妨可以这么想，如果现在和为Target,此时取一个硬币，假设其值为v1，
 * 那么下一次，我们其实解决的是相同的子问题，只不过和变成了Target - v1 
 * 既然明确了第二点，我们就可以通过Target的取值来确定返回条件
 * 1. 当Target < 0 的时候，无解，return
 * 2. 当Target ＝＝ 0 的时候，发现解，添加进res里面，然后return
 * 
 * 需要注意的几点问题:
 * 1.如果不用pos，从数组开头往数组尾巴扫，会出现什么问题？
 * 会出现重复集合的问题，这里的重复不是指顺序完全相同的Set，而是对于每一种组合，我们输出一次就可以了 
 * 比如Sum = 3 不加pos会出现 [1,2],[2,1]两种一样的解，我们加入一个pos，
 * 限制每次添加只能再添加和这个数字相等或者比这个数字大的数字（数组之前sort过升序排列）这样就会避免问题
 */

import java.util.*;

public class CombinationSum {
    
    public ArrayList<ArrayList<Integer>> combinationSum_2(int[] candidates, int target) {
        Arrays.sort(candidates);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(result, list, candidates, target, 0);
        return result;
    }
    
    private void helper(ArrayList<ArrayList<Integer>> result, ArrayList<Integer> list,
            int[] candidates, int target, int pos) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList(list));
            return;
        }
        for (int i = pos; i < candidates.length; i++) {
            list.add(candidates[i]);
            helper(result, list, candidates, target - candidates[i], i);
            list.remove(list.size() - 1);
        }
    }
    
    
    // sol2 一樣概念
    public ArrayList<ArrayList<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        ArrayList<ArrayList<Integer>> result = new ArrayList<ArrayList<Integer>>();
        Stack<Integer> path = new Stack<Integer>();
        combinationSum(candidates, target, 0, path, result);
        return result;
    }
    
    private void combinationSum(int[] arr, int target, int start, Stack<Integer> path, ArrayList<ArrayList<Integer>> result) {
        if (target == 0) {
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.addAll(path);
            result.add(list);
            return;
        }
        for (int i = start; i < arr.length; i++) {
            if (arr[i] > target) //这时候break可以保证不要再查后面那些越来越大的
            {
                return;
            }
            path.push(arr[i]);
            combinationSum(arr, target - arr[i], i, path, result); //start永远不会越界，所以一开始不用check
            path.pop();
        }
    }
    
    public static void main(String[] args) {
        CombinationSum cs = new CombinationSum();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        ArrayList<ArrayList<Integer>> result = cs.combinationSum_2(candidates, target);
        for (ArrayList<Integer> list : result) {
            System.out.print("[");
            for (Integer i : list) {
                System.out.print(i + ", ");
            }
            System.out.println("]");
        }
    }
}
