/**
 * given s = "aab", Return [ ["aa","b"], ["a","a","b"] ]
 *
 * sol1: 直接 DFS, 會重複計算 substring 是否為 palindrome sol2: 加上 dp table, 效率提高
 */
package DeepFirstSearch;

import java.util.ArrayList;

public class PalindromePartitioning {

    //加上 dp table
    boolean[][] table;

    public ArrayList<ArrayList<String>> partitionDPnDFS(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList();
        if (s == null) {
            return result;
        }
        ArrayList<String> list = new ArrayList();
        table = createDPtable(s);
        helperDP(result, list, s, 0);
        return result;
    }

    private void helperDP(ArrayList<ArrayList<String>> result, ArrayList<String> list,
            String s, int pos) {
        if (pos == s.length()) {
            result.add(new ArrayList<String>(list));
            return;
        }

        for (int i = pos + 1; i <= s.length(); i++) {
            String prefix = s.substring(pos, i);
            if (!table[pos][i - 1]) {
                continue;
            }
            list.add(prefix);
            helperDP(result, list, s, i);
            list.remove(list.size() - 1);
        }
    }

    private boolean[][] createDPtable(String s) {
        boolean[][] table = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            table[i][i] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            //substring 為偶數
            int l = i - 1;
            int r = i;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                table[l--][r++] = true;
            }
            //substring為奇數
            l = i - 1;
            r = i + 1;
            while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
                table[l--][r++] = true;
            }
        }
        return table;
    }

    // 直接 dfs, 會有重複計算字串是否為 palindrome 的問題
    public ArrayList<ArrayList<String>> partitionDFS(String s) {
        ArrayList<ArrayList<String>> result = new ArrayList();
        if (s == null) {
            return result;
        }
        ArrayList<String> list = new ArrayList();
        helper(result, list, s, 0);
        return result;
    }

    private void helper(ArrayList<ArrayList<String>> result, ArrayList<String> list,
            String s, int pos) {
        if (pos == s.length()) {
            result.add(new ArrayList<String>(list));
            return;
        }

        for (int i = pos + 1; i <= s.length(); i++) {
            String prefix = s.substring(pos, i);
            if (!isPalindrome(prefix)) {
                continue;
            }
            list.add(prefix);
            helper(result, list, s, i);
            list.remove(list.size() - 1);
        }
    }

    private boolean isPalindrome(String s) {
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartitioning p = new PalindromePartitioning();
        ArrayList<ArrayList<String>> result = p.partitionDPnDFS("aab");
        for (ArrayList<String> list : result) {
            System.out.print("{");
            for (String in : list) {
                System.out.print(in + ", ");
            }
            System.out.println("}");
        }
    }
}
