/*
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 * For example, Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 * 
 * 这道题，可以用Recursion和DP两种方法解，我们先来看比较直观的Recursion的解法。
 * 我们从头到尾遍历这三个String，比如取名s1,s2,s3，然后取p1,p2,p3三个指针来对应每个String里当前遍历到的字符位置
 * 这么想，如果s1的p1位，和s2的p2位，和s3的p3位都相等，那么在s3挑选的字符的时候，
 * 我们可以挑s1的那一位，也可以挑s2的那一位，所以在递归的时候我们用或（｜｜）把两种情况连接起来，传入s1的下一位，或者s2的下一位
 * 如果只有s1的p1位和s3的p3位相等，那只能传入s1的下一位
 * 如果只有s2的p2位和s3的p3位相等，那只能传入s2的下一位
 * 如果没有发现相等，return false 退回到上一层
 * 此法超時
 * 
 * DP法（以下描述为 1 base）
 * dp[i][j]表示s1取前i位，s2取前j位，是否能组成s3的前i+j位
 * 举个列子，注意左上角那一对箭头指向的格子dp[1][1], 表示s1取第1位a, s2取第1位d，是否能组成s3的前两位aa
 * 从dp[0][1] 往下的箭头表示，s1目前取了0位，s2目前取了1位，我们添加s1的第1位，看看它是不是等于s3的第2位，( i + j 位）
 * 从dp[1][0] 往右的箭头表示，s1目前取了1位，s2目前取了0位，我们添加s2的第1位，看看它是不是等于s3的第2位，( i + j 位)
 * 那什么时候取True，什么时候取False呢？
 * False很直观，如果不等就是False了嘛。
 * 那True呢？首先第一个条件，新添加的字符，要等于s3里面对应的位( i + j 位)，第二个条件，之前那个格子也要等于True
 * 举个简单的例子s1 = ab, s2 = c, s3 = bbc ，假设s1已经取了2位，c还没取，此时是False（ab!=bb），我们取s2的新的一位c
 * ，即便和s3中的c相等，但是之前是False，所以这一位也是False
 * 同理，如果s1 = ab, s2 = c, s3=abc ，同样的假设，s1取了2位，c还没取，此时是True（ab==ab），
 * 我们取s2的新的一位c,和s3中的c相等，且之前这一位就是True，此时我们可以放心置True （abc==abc）
 * 还有一点需要注意的是，String 的index是0 base的, 我们以dp[m+1][n+1] 正序遍历字符创造的矩阵是1 base的
 */
package DynamicProgramming;

public class InterleavingString {

    public boolean isInterleaveDP(String s1, String s2, String s3) {

        if (s1 == null || s2 == null || s3 == null) {
            return false;
        }
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        // 用 s1 初始化 dp[row][0]
        for (int i = 1; i <= s1.length(); i++) {
            if (s1.charAt(i - 1) == s3.charAt(i - 1) && dp[i - 1][0] == true) {
                dp[i][0] = true;
            } else {
                dp[i][0] = false;
            }
        }
        // 用 s2 初始化 dp[0][col]
        for (int j = 1; j <= s2.length(); j++) {
            if (s2.charAt(j - 1) == s3.charAt(j - 1) && dp[0][j - 1] == true) {
                dp[0][j] = true;
            } else {
                dp[0][j] = false;
            }
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s3.charAt(i + j - 1) && dp[i - 1][j] == true) {
                    dp[i][j] = true;
                } else if (s2.charAt(j - 1) == s3.charAt(i + j - 1) && dp[i][j - 1] == true) {
                    dp[i][j] = true;
                } else {
                    dp[i][j] = false;
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    //超時
    public boolean isInterleaveRecursion(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) {
            return false;
        }
        return helper(s1, s2, s3, 0, 0, 0);
    }

    private boolean helper(String s1, String s2, String s3, int p1, int p2, int p3) {
        if (p3 == s3.length()) {
            return true;
        }
        if (p1 == s1.length()) {
            return s2.substring(p2).equals(s3.substring(p3));
        }
        if (p2 == s2.length()) {
            return s1.substring(p1).equals(s3.substring(p3));
        }

        if (s3.charAt(p3) == s1.charAt(p1) && s3.charAt(p3) == s2.charAt(p2)) {
            return helper(s1, s2, s3, p1 + 1, p2, p3 + 1) || helper(s1, s2, s3, p1, p2 + 1, p3 + 1);
        } else if (s3.charAt(p3) == s1.charAt(p1)) {
            return helper(s1, s2, s3, p1 + 1, p2, p3 + 1);
        } else if (s3.charAt(p3) == s2.charAt(p2)) {
            return helper(s1, s2, s3, p1, p2 + 1, p3 + 1);
        }
        return false;
    }

    public static void main(String[] args) {
        String s1 = "abc";
        String s2 = "ade";
        String s3 = "aabdec";
        InterleavingString i = new InterleavingString();
        System.out.println(i.isInterleaveDP(s1, s2, s3));
    }
}
