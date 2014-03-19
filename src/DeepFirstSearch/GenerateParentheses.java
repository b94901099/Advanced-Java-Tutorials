/*
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * For example, given n = 3, a solution set is:
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 * 
 * 好了，我们已经征服了dfs的基础题，那么从数字的组合扩展到放置括号，我们只要分析什么情况下括号的组合是合法的即可
 * 1. 如果左括号数还没有用完，那么我们能继续放置左括号
 * 2. 如果已经放置的左括号数大于已经放置的右括号数，那么我们可以放置右括号 
 *   （如果放置的右括号数大于放置的左括号数，会出现不合法组合）
 * 所以，运用dfs在每一层递归中，如果满足条件先放置左括号，如果满足条件再放置右括号
 * 程序有两种书写习惯，
 * 第一种，像之前的程序一样，我们把String tmp写在dfs里面，每回Return删去刚刚添加的字符
 * 第二种，直接把tmp ＋（添加的字符）当参数传入下一层调用函数，这样返回后在上一层是之前传入的参数，不用删字符
 */
package DeepFirstSearch;

import java.util.*;

public class GenerateParentheses {

    public ArrayList<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<String>();
        if (n <= 0) {
            return result;
        }
        generateParenthesisHelper(result, "", n, 0, 0);
        return result;
    }

    private void generateParenthesisHelper(ArrayList<String> result, String tmp, int n, int leftP, int rightP) {
        if (leftP < rightP) {
            return;
        }
        if (leftP == n && rightP == n) {
            result.add(tmp);
            return;
        }
        if (leftP <= n) {
            generateParenthesisHelper(result, tmp + "(", n, leftP + 1, rightP);
        }
        if (rightP <= leftP) {
            generateParenthesisHelper(result, tmp + ")", n, leftP, rightP + 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses gp = new GenerateParentheses();
        ArrayList<String> result = gp.generateParenthesis(3);
        for (String s : result) {
            System.out.println(s);
        }
    }
}
