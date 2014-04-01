/*
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.
 */
package DynamicProgramming;

import DeepFirstSearch.*;
import java.util.ArrayList;

public class PalindromePartitioningII {

    /*这个题很多人都分析过了，其实是两个合在一起的DP，其中一个DP绘制字符子串Palindrome矩阵，我们已经解决了。
     * 那么剩下的问题就是怎么求最小切数了  我们从字符串的尾巴往前推，我们来创造一个dp数组，
     * 叫cut[]，在数组里cut[i] 表示从字符串第i位开始到字符串结尾需要切的刀数（包括该i位左边的一刀）
     * 假设，不幸的是，从第i位开始到后面都不存在Palindrome，那么需要的刀数就为 len - i (包括左边的一刀)，即每个字符间都要切一刀
     * 那么我们从第i位开始，往后找，设计一个循环一直到字符串结尾，看是否存在Palindrome
     * 如果发现T[i][j]是一个Palindrome，那么从T[i][j]我们中间就不用切刀啦，然后去加上j位后面一位需要切刀的数量
     * （+1是因为要保存最左边的一刀），然后我们和之前的cut[i]这一位进行比较，如果比之前的刀数少，我们便保留这个值，
     * 在循环到结尾的过程中一直最小值就能保证第i位以后的字串保持最小切刀数目，一直到cut[0]为止，
     * 最后因为第一个字符最左边不需要切，我们返回cut[0] - 1
     */
    boolean[][] table;

    public int minCut3(String s) {
        if (s == null || s.length() <= 1) {
            return 0;
        }
        table(s);
        int[] count = new int[s.length() + 1];
        count[s.length()] = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            count[i] = Integer.MAX_VALUE;
            for (int j = i; j < s.length(); j++) {
                if (table[i][j]) {
                    count[i] = Math.min(count[i], 1 + count[j + 1]);
                }
            }
        }
        return count[0] - 1;
    }

    private void table(String s) {
        table = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            table[i][i] = true;
        }
        for (int i = 0; i < s.length(); i++) {

            int left = i - 1;
            int right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                table[left--][right++] = true;
            }

            left = i - 1;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                table[left--][right++] = true;
            }
        }
    }

    int minCut2(String s) {
        int len = s.length();
        int[] D = new int[len + 1];
        boolean[][] P = new boolean[len][len];
        //the worst case is cutting by each char  
        for (int i = 0; i <= len; i++) {
            D[i] = len - i;
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                P[i][j] = false;
            }
        }
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || P[i + 1][j - 1])) {
                    P[i][j] = true;
                    D[i] = Math.min(D[i], D[j + 1] + 1);
                }
            }
        }
        return D[0] - 1;
    }

    int minCutII(String s) {
        int min = Integer.MAX_VALUE;
        DFS(s, 0, 0, min);
        return min;
    }

    void DFS(String s, int start, int depth, int min) {
        if (start == s.length()) {
            if (min > depth - 1) {
                min = depth - 1;
            }
            return;
        }
        for (int i = s.length() - 1; i >= start; i--) //find the biggest palindrome first
        {
            if (isPalindrome(s, start, i)) {
                DFS(s, i + 1, depth + 1, min);
            }
        }
    }

    boolean isPalindrome(String s, int start, int end) {
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
        PalindromePartitioningII p = new PalindromePartitioningII();
        System.out.println(p.minCut3("aab"));
        System.out.println(p.minCut3("aabaa"));
    }
}
