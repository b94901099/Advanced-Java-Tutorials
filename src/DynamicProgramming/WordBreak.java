/*
 * Given a string s and a dictionary of words dict, determine if s can be 
 * segmented into a space-separated sequence of one or more dictionary words.
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * 这个题目比较一般，就是判断一个串是否能被分解成几个出现在词典中的词。这个dp的思想就很明显了。
 * 关键是子问题的想法，我写了三种，其实就是看子问题不同。
 * 如:leetcode 可以看为l,le,lee,leet,leetc,leeco,leetcod是否能够被词典中的词表示，然后加入e，
 * 判断如果某个前缀可以表示如:leet可以被词典中的词表示，那么就可以判断code是否出现在词典中。
 * （ie:如果lee能就判断tcode是否出现），这样就可以判断leetcode是否可以被词典中词表示了。
 * dp[i]=dp[j] && find(str(j,i)) 0=<j&&j<=i
 */
package DynamicProgramming;

import java.util.*;

public class WordBreak {

    //AC Sol
    public boolean wordBreak3(String s, Set<String> dict) {

        if (s == null || s.length() == 0) {
            return false;
        }

        HashSet<String> unmatch = new HashSet<String>();
        return wordBreak3Helper(s, dict, unmatch);
    }

    private boolean wordBreak3Helper(String s, Set<String> dict, HashSet<String> unmatch) {
        if (s.length() == 0) {
            return true;
        }
        boolean flag = false;
        for (int i = 1; i <= s.length(); i++) {
            String prefixStr = s.substring(0, i);
            if (!dict.contains(prefixStr)) {
                continue;
            }
            String postStr = s.substring(i);
            if (unmatch.contains(postStr)) {
                continue; // 紀錄剩餘部分是否符合, 減少 dfs 次數
            } else {
                flag = wordBreak3Helper(postStr, dict, unmatch);
                if (flag) {
                    return true;
                } else {
                    unmatch.add(postStr);
                }
            }
        }
        return false;
    }

    private int getMaxLength(Set<String> dict) {
        int maxLength = 0;
        for (String word : dict) {
            maxLength = Math.max(maxLength, word.length());
        }
        return maxLength;
    }

    public boolean wordBreak(String s, Set<String> dict) {

        if (s == null || s.length() == 0) {
            return false;
        }

        int maxLength = getMaxLength(dict);
        boolean[] canSegment = new boolean[s.length() + 1];
        canSegment[0] = true;

        for (int i = 1; i <= s.length(); i++) {
            canSegment[i] = false;
            for (int j = 1; j <= maxLength && j <= i; j++) {
                if (!canSegment[i - j]) {
                    continue;
                }
                String word = s.substring(i - j, i);
                if (dict.contains(word)) {
                    canSegment[i] = true;
                    break;
                }
            }
        }
        return canSegment[s.length()];
    }

    public boolean wordBreak2(String s, Set<String> dict) {

        if (s == null || s.length() == 0) {
            return false;
        }

        boolean[][] table = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (dict.contains(s.subSequence(i, j + 1))) {
                    table[i][j] = true;
                }
            }
        }
        return helper(s, dict, 0, table);
    }

    private boolean helper(String s, Set<String> dict, int pos, boolean[][] table) {
        if (pos == s.length()) {
            return true;
        }
        for (int i = pos; i < s.length(); i++) {
            if (table[pos][i]) {
                if (helper(s, dict, i + 1, table)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        WordBreak w = new WordBreak();
        HashSet<String> set = new HashSet<String>();
        String[] dictArray = {"cat", "cats", "and", "sand", "dog"};
        for (String s : dictArray) {
            set.add(s);
        }
        String s = "catsanddog";
        Stopwatch time1 = new Stopwatch();
        System.out.println(w.wordBreak(s, set));
        System.out.println(time1.elapsedTime());
        Stopwatch time2 = new Stopwatch();
        System.out.println(w.wordBreak2(s, set));
        System.out.println(time2.elapsedTime());
        Stopwatch time3 = new Stopwatch();
        System.out.println(w.wordBreak3(s, set));
        System.out.println(time3.elapsedTime());

    }
}
