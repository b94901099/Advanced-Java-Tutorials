/*
 * Implement regular expression matching with support for '.' and '*'.
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 * Some examples:
 * isMatch("aa","a") → false
 * isMatch("aa","aa") → true
 * isMatch("aaa","aa") → false
 * isMatch("aa", "a*") → true
 * isMatch("aa", ".*") → true
 * isMatch("ab", ".*") → true
 * isMatch("aab", "c*a*b") → true
 */
package String;

public class RegularExpressionMatching {

    public static boolean isMatch(String s, String p) {
        return isM(s, p, 0, 0);
    }

    public static boolean isM(String s, String p, int i, int j) {
        if (j >= p.length()) {         // pattern已经用光  
            return i >= s.length();  // 如果s已经走完则匹配，否则不匹配  
        }
        if (j == p.length() - 1) {  // 如果pattern的j已经走到最后一个字符，如果想匹配，则s的i也必须在最后一个，且相等  
            return (i == s.length() - 1) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        }

        // 如果pattern的下一个字符(j+1)不是*  
        if (j + 1 < p.length() && p.charAt(j + 1) != '*') {
            if (i == s.length()) {    // 如果s已经走完，则说明不匹配  
                return false;
            }
            if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.') {   // 如果当前字符匹配  
                return isM(s, p, i + 1, j + 1);     // 继续下一个字符判断  
            } else {  // 如果当前字符不匹配，直接返回false  
                return false;
            }
        }

        // 如果下一个字符(j+1)是* 且 当前字符匹配，则进行搜索：  
        while (i < s.length() && j < p.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.')) {
            // 因为*可以取0,1,2,...所以i=i,i+1,i+2,...对所有可能进行测试  
            // 最后能否匹配取决于剩下的匹配是否成功  
            if (isM(s, p, i, j + 2)) {  // 只要找到一个匹配成功即可  
                return true;
            }
            i++;
        }

        // 如果下一个字符(j+1)是* 且 当前字符不匹配，则pattern跳过两个，继续比较  
        // 还有一种情况到这里是上面的最后一次尝试（i==s.length()）  
        return isM(s, p, i, j + 2);
    }

    
    /* SOL 2 DP
     * 题目意图很简单，就是实现一个正则表达式匹配的判定函数。　特别要说一下的是　.* 这个格式，
     * 因为不是经常用，最开始想错了，以为是先匹配"." 然后 "." 匹配成什么，后面的 * 就扩展什么。
     * 其实核心的思路是一个动态规划
     * dp[i][j]表示字串 s[i...len(s)], p[j...len(p)] 是否可以匹配。
     * 那么状态转移方程如下：
     * dp[i][j] = c1. p[j+1] != *.
     *                if s[i] == p[j]  dp[i][j] = dp[i+1][j+1] 
     *                else dp[i][j] = false
     *            c2 p[j+1] == '*'  (这个情况下，要扩展 *, dp[i][j] 从拓展的情况下，选择一个是真的结果）
     *               if( s[i] ==  p[j] || p[j] == '.' && (*s) != 0)  当s[i] 和 p[j] 一样的时候，
     *                                例如 aba, a*b这个时候，i = 0, j = 0, 自然可以匹配a a
     *                                如果p[j] == .  因为他可以匹配任何字符，所以和相等关系有基本一样的方式。
     * 并且每一步匹配都要递增 i 的值，如果有成立的，则返回true，否则到匹配终了，返回通配符匹配完成后的结果。
     */
    public static void main(String[] args) {
    }
}
