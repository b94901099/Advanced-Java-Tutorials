/*
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1; 'B' -> 2 ...; 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * The number of ways decoding "12" is 2.
 * 
 * DFS 超時解法
 * 1.  每次dfs里面我们先从1位开始取，然后再取2位，要小心越界的问题，到后面字符的长度可能只有1位，所以i < s.length()
 * 2.  检查字符是否Valid的时候和Restore IP Address一样，要小心0的情况，不过这道题比那道题简单，如果出现0直接返回False
 * 
 * DP 解法
 * 从头到尾扫这个String，比如我们想知道到，从第一位到dp[i]这一位组成的String，有多少种解码组合，那么有两种情况
 * 第一：如果dp[i]所对应的的单个字符可以解码，那么dp[i]就包括前dp[i-1]位所积累的组合数 dp[i] = dp[i-1] 
 * 第二：如果不仅dp[i]所对应的的单个字符可以解码，dp[i-1] － dp[i]，两个字符组成的也可以解码，
 * 那么不仅包括dp[i-1]积累的组合数，也包括dp[i-2]位积累的组合数 dp[i] = dp[i-1] + dp[i-2]
 * 我们建一个n+1的数组，为了程序简洁，我们在最前面放一个1。这样一来要注意数组里的index -1==String里的index 
 */
package DynamicProgramming;

public class DecodeWays {

    public int numDecodingsDP(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }

        int[] count = new int[s.length() + 1];
        count[0] = 1;
        
        if (isValid(s.charAt(0))) {
            count[1] = 1;
        } else {
            count[1] = 0;
        }

        for (int i = 2; i <= s.length(); i++) {
            if (isValid(s.charAt(i - 1))) {
                count[i] = count[i - 1];
            }
            if (isValid(s.substring(i - 2, i))){
                count[i] = count[i] + count[i - 2];
            }
        }
        
        return count[s.length()];
    }

    private boolean isValid(char c) {
        return c - '0' > 0;
    }
    
    
    // DFS sol, time exceeds
    int count;

    public int numDecodingsDFS(String s) {
        if (s == null || s.length() < 1) {
            return count;
        }
        count = 0;
        dfsHelper(s, 0);
        return count;
    }

    private void dfsHelper(String s, int pos) {
        if (pos == s.length()) {
            count++;
            return;
        }
        for (int i = pos + 1; i <= s.length(); i++) {
            String substr = s.substring(pos, i);
            if (isValid(substr)) {
                dfsHelper(s, i);
            }
        }
    }

    public boolean isValid(String s) {
        if (s.charAt(0) == '0') {
            return false;
        }
        int code = Integer.parseInt(s);
        return code >= 1 && code <= 26;
    }

    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        System.out.println(d.numDecodingsDFS("15234"));
        System.out.println(d.numDecodingsDP("15234"));
    }
}
