package String;

/**
 * Given a string s and a dictionary of words dict, add spaces in s to construct
 * a sentence where each word is a valid dictionary word.
 * Return all such possible sentences.
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].
 */

import DeepFirstSearch.*;
import java.util.*;

public class WordBreakII {

    private void dfs(String s, boolean seg[][], int start,
            ArrayList<String> ret, StringBuilder sb, Set<String> dict) {
        // exit  
        if ("".equals(s)) {
            // need to trim the ending white space  
            ret.add(sb.substring(0, sb.length() - 1));
        }

        for (int len = 1; len <= s.length(); len++) {
            // do pruning here  
            if (seg[start][len]) {
                String str = s.substring(0, len);
                if (dict.contains(str)) {
                    sb.append(str).append(" ");
                    dfs(s.substring(len), seg, start + len, ret, sb, dict);
                    // backtrack  
                    sb.delete(sb.length() - str.length() - 1, sb.length());
                }
            }
        }
    }

    public ArrayList<String> wordBreak(String s, Set<String> dict) {
        ArrayList<String> ret = new ArrayList<String>();
        // directly use the code of word break I here for later pruning flag  
        if ("".equals(s)) {
            return ret;
        }
        // seg[i][0] is wasted  
        boolean seg[][] = new boolean[s.length()][s.length() + 1];
        for (int len = 1; len <= s.length(); len++) {
            for (int i = 0; i < s.length(); i++) {
                if (i + len <= s.length()) {
                    // 1) seg(i, len) is a dictionary word  
                    if (dict.contains(s.substring(i, i + len))) {
                        seg[i][len] = true;
                        continue;
                    }
                    // 2) seg(i, k) and seg(i + k, len - k) can be segmented  
                    for (int k = 1; k < len; k++) {
                        if (seg[i][k] && seg[i + k][len - k]) {
                            seg[i][len] = true;
                            break;
                        }
                    }
                }
            }
        }

        // if no solution  
        if (!seg[0][s.length()]) {
            return ret;
        }

        dfs(s, seg, 0, ret, new StringBuilder(), dict);

        return ret;
    }

    //純 DFS 會超時
    public ArrayList<String> wordBreak2(String s, Set<String> dict) {
        ArrayList<String> result = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();
        wordBreakHelper(s, dict, result, sb);
        return result;
    }

    private void wordBreakHelper(String s, Set<String> dict, ArrayList<String> result, StringBuilder sb) {
        if (s.length() == 0) {
            result.add(sb.toString());
            return;
        }
        for (int i = 1; i <= s.length(); i++) {
            String str = s.substring(0, i);
            if (!dict.contains(str)) {
                continue;
            }
            sb.append(str + " ");
            wordBreakHelper(s.substring(i), dict, result, sb);
            sb.delete(sb.length() - i - 1, sb.length());
        }
    }

    public static void main(String[] args) {
        String s = "12345";
        StringBuilder sb = new StringBuilder("sheng");
        String str = s.substring(0, 2);
        sb.append(str);
        System.out.println(sb.toString());
        sb.delete(sb.length() - 2, sb.length());
        System.out.println(sb.toString());

        WordBreakII w = new WordBreakII();
        String test = "catsanddog";
        HashSet<String> dict = new HashSet<String>();
        String[] words = {"cat", "cats", "and", "sand", "dog"};
        for (String word : words) {
            dict.add(word);
        }
        ArrayList<String> result = w.wordBreak(test, dict);
        System.out.println(result);
    }
}
