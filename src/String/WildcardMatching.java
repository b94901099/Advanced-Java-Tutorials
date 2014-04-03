/*
 * Implement wildcard pattern matching with support for '?' and '*'.

 '?' Matches any single character.
 '*' Matches any sequence of characters (including the empty sequence).

 The matching should cover the entire input string (not partial).

 The function prototype should be:
 bool isMatch(const char *s, const char *p)

 Some examples:
 isMatch("aa","a") → false
 isMatch("aa","aa") → true
 isMatch("aaa","aa") → false
 isMatch("aa", "*") → true
 isMatch("aa", "a*") → true
 isMatch("ab", "?*") → true
 isMatch("aab", "c*a*b") → false
 */
package String;

public class WildcardMatching {

    
    //有bug
    boolean isMatch2(String s, String p) {
        if (p.length() == 0) {
            return s.length() == 0;
        }

        int i = 0, j = 0;
        int star = -1;

        while (i < s.length() && j < p.length()) {
            char charS = s.charAt(i);
            char charP = p.charAt(j);

            if (charP == '?' || charP == charS) {
                i++;
                j++;
            } else if (charP == '*') {
                star = j;
                do {
                    j++;
                } while (j < p.length() && p.charAt(j) == '*');
                if (j == p.length()) {
                    return true;
                }
            } else {
                if (star == -1) {
                    return false;
                } else {
                    i++;
                }
            }
        }

        while (j < p.length() && p.charAt(j) == '*') {
            j++;
        }
        return j == p.length();
    }

    public static void main(String[] args) {
        WildcardMatching w = new WildcardMatching();
        //System.out.println(w.isMatch2("aaa", "*?"));
        System.out.println(w.isMatch2("aa", "a"));
    }
}
