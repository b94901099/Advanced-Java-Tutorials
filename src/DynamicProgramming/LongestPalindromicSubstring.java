/*
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000, 
 * and there exists one unique longest palindromic substring.
 */
package DynamicProgramming;

public class LongestPalindromicSubstring {

    private static String longestPalindrome3(String s) {

        if (s == null || s.length() < 2) {
            return s;
        }

        boolean[][] table = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            table[i][i] = true;
        }

        int maxLength = 1;
        int start = 0;
        int end = 0;

        for (int i = 0; i < s.length(); i++) {
            //substring 為偶數
            int left = i - 1;
            int right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    start = left;
                    end = right;
                }
                table[left--][right++] = true;
            }
            //substring為奇數
            left = i - 1;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > maxLength) {
                    maxLength = right - left + 1;
                    start = left;
                    end = right;
                }
                table[left--][right++] = true;
            }
        }
        return s.substring(start, end + 1);
    }

    public static String longestPalindrome2(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        int maxLength = 1;
        int subHead = 0;
        int subTail = 0;

        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                if (i == j) {
                    dp[i][j] = true;
                } else if (j - i == 1 && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    if (j - i + 1 > maxLength) {
                        maxLength = j - i + 1;
                        subHead = i;
                        subTail = j;
                    }
                }
            }
        }

        for (int k = 2; k < s.length(); k++) {
            for (int i = 0; i + k < s.length(); i++) {
                int j = i + k;
                if (s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                    if (k + 1 > maxLength) {
                        maxLength = k + 1;
                        subHead = i;
                        subTail = j;
                    }
                }
            }
        }
        return s.substring(subHead, subTail + 1);
    }

    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }

        boolean[][] iTojIsPalin = new boolean[s.length()][s.length()];
        int maxLength = 1; // 最短為1
        int subhead = 0;
        int subtail = 0;

        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if (i >= j) {
                    iTojIsPalin[i][j] = true; // i = j 必定為回文, 而 i > j 為 null, 亦是true
                } else {
                    iTojIsPalin[i][j] = false;
                }
            }
        }

        for (int k = 1; k < s.length(); k++) {
            for (int i = 0; i + k < s.length(); i++) {
                int j = i + k;
                if (s.charAt(i) != s.charAt(j)) {
                    iTojIsPalin[i][j] = false;
                } else { //s.charAt(i) == s.charAt(j)
                    iTojIsPalin[i][j] = iTojIsPalin[i + 1][j - 1];
                    if (iTojIsPalin[i][j]) { //若是回文, 則判斷字串長度 k + 1
                        if (k + 1 > maxLength) {
                            maxLength = k + 1;
                            subhead = i;
                            subtail = j;
                        }
                    }
                }
            }
        }
        return s.substring(subhead, subtail + 1);
    }

    public static void main(String[] args) {
        String s = "bb";
        System.out.println(longestPalindrome(s));
        System.out.println(longestPalindrome2(s));
        System.out.println(longestPalindrome3(s));
    }
}
