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
    }
}
