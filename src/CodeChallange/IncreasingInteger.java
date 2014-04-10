/*
 * n位数字分析
 * 给定数字n，请给出方法，打印出所有的位数是n的数字，并且每一个数字，每一位从左到右依次变大。
 * 例如：n=3时(123,124,125...789).
 */
package CodeChallange;

import java.util.*;

public class IncreasingInteger {

    public ArrayList<String> f(int n) {
        if (n < 1 || n > 10) {
            throw new RuntimeException();
        }
        ArrayList<String> result = new ArrayList<String>();
        helper(n, 0, "", result);
        return result;
    }

    private void helper(int n, int peaked, String tmp, ArrayList<String> result) {
        if (n == 0) {
            result.add(tmp);
            return;
        }
        for (int i = peaked + 1; i < 10; i++) {
            helper(n - 1, i, tmp + i, result);
        }
    }

    public static void main(String[] args) {
        IncreasingInteger i = new IncreasingInteger();
        ArrayList<String> result = i.f(3);
        for (String s: result) 
            System.out.print(s + ", ");
    }
}
