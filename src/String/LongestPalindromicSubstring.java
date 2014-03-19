/*
 * Given a string S, find the longest palindromic substring in S.
 * You may assume that the maximum length of S is 1000,
 * and there exists one unique longest palindromic substring.
 */
package String;

public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        if (s.length() < 2 || s == null) {
            return s;
        }
        int start = 0;
        int end = 1;
        int maxLength = 0;
        int maxStart = 0;
        int maxEnd = 0;
        while (start <= end && end <= s.length()) {
            String tmp = s.substring(start, end);
            if (isPalindrome(tmp)) {
                if (tmp.length() > maxLength) {
                    maxLength = tmp.length();
                    maxStart = start;
                    maxEnd = end;
                }
                end++;
            } else {
                start++;
            }
        }
        return s.substring(maxStart, maxEnd);
    }

    private boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "abcbasdsd";
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        System.out.println(l.longestPalindrome(s));
    }
}
