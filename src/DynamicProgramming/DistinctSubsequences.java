/*
 * Given a string S and a string T, count the number of distinct subsequences of T in S.
 * A subsequence of a string is a new string which is formed from the original 
 * string by deleting some (can be none) of the characters without disturbing the 
 * relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
 * Here is an example:
 * S = "rabbbit", T = "rabbit"
 * Return 3.
 * 
 * http://blog.csdn.net/u011095253/article/details/9248121
 * 这道题，同样可以用Recursion或者DP来解决。
 * 我们只要分析清Recursion的原理，DP也就很容易写出来了。
 * 举个例子，比如String T = "abc" String S = "abcabc"
 * 当T里的某一位，比如说第一位，"a" 匹配上S里的某一位，也比如说第一位里的"a"，那么有两种情况
 * 1. 用上这个匹配，我们再去递归求String T = "bc" 在String S ="bcabc"里出现的次数
 * 2. 或者不用这个匹配，我们递归去求String T = "abc"  在String S = "bcabc"里出现的次数
 * 当然，如果要比较的两个字符不等，就只能用T剩下的全部，去和S里的下一位进行比较
 * 
 * 怎么将String用DP矩阵表示出来我们已经很熟悉了，dp[i][j]代表String T的前i位与String S前j位存在序列的个数
 * (不熟悉的可以复习下这两个题喔，Interleaving String ，Edit Distance)
 * 用我们刚才Recursion做出的归纳：当S中某个字符和T中某个字符匹配的时候：
 * 1. 用这个字符，在矩阵里的表示便为累加上之前dp[i-1][j-1]的值
 * 2. 不用这个字符，在矩阵里的表示便为累加上之前dp[i][j-1]的值
 */

package DynamicProgramming;

public class DistinctSubsequences {

    public static int numDistinct(String S, String T) {
        if (S == null || S.length() == 0 || T == null && T.length() == 0) {
            return 0;
        }

        if (S.length() < T.length()) {
            return 0;
        }

        int[][] nums = new int[T.length() + 1][S.length() + 1];
        for (int row = 0; row < nums.length; row++) {
            // appear numbers of String T in String "";
            nums[row][0] = 0;
        }

        for (int col = 0; col < nums[0].length; col++) {
            // appears numbers of String "" in String S;

            nums[0][col] = 1;
        }

        for (int row = 1; row < nums.length; row++) {
            for (int col = 1; col < nums[0].length; col++) {
                // no matter if current char in S equal to current char in T
                // current matched times is at least equal to the times matched between T and S.substring(0, index(current Char))
                nums[row][col] = nums[row][col - 1];

                // if current char in T matched current char in S, then nums[row][col] should also plus
                // the times matched between T.substring(0,index(current char) ) and S.substring(0, index(current char));

                if (S.charAt(col - 1) == T.charAt(row - 1)) {
                    nums[row][col] += nums[row - 1][col - 1];
                }
            }
        }

        return nums[T.length()][S.length()];
    }


    public static int numDistinctDP2(String S, String T) {
        if (S == null || S.length() == 0 || T == null && T.length() == 0) {
            return 0;
        }

        if (S.length() < T.length()) {
            return 0;
        }

        int[][] counts = new int[T.length()][S.length()];

        if (S.charAt(0) == T.charAt(0))
            counts[0][0] = 1;

        for (int j = 1; j < S.length(); j++) {
            counts[0][j] = counts[0][j - 1];
            if (T.charAt(0) == S.charAt(j))
                counts[0][j] += 1;
        }

        for (int i = 1; i < T.length(); i++) {
            counts[i][0] = 0;
        }

        for (int i = 1; i < T.length(); i++) {
            for (int j = 1; j < S.length(); j++) {
                counts[i][j] = counts[i][j - 1];
                if (T.charAt(i) == S.charAt(j))
                    counts[i][j] += counts[i-1][j-1];
            }
        }

        return counts[T.length() - 1][S.length() - 1];
    }

    public int numDistinctRecursion(String S, String T) {
        if (T.length()==0) return 1;  
        if (S.length()==0) return 0;  
        if (T.length()>S.length()) return 0;  
        if (T.charAt(0)!=S.charAt(0))  
            return numDistinct(S.substring(1),T);  
        else return numDistinct(S.substring(1),T) + numDistinct(S.substring(1),T.substring(1));  
    }  

    public static void main(String[] args) {
        String S = "ACCCE";
        String T = "ACE";
        System.out.println(numDistinct(S, T));
        System.out.println(numDistinctDP2(S, T));
    }
}
