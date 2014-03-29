/*
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

 Below is one possible representation of s1 = "great":

 great
 /    \
 gr    eat
 / \    /  \
 g   r  e   at
 / \
 a   t
 To scramble the string, we may choose any non-leaf node and swap its two children.

 For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

 rgeat
 /    \
 rg    eat
 / \    /  \
 r   g  e   at
 / \
 a   t
 We say that "rgeat" is a scrambled string of "great".

 Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

 rgtae
 /    \
 rg    tae
 / \    /  \
 r   g  ta  e
 / \
 t   a
 We say that "rgtae" is a scrambled string of "great".

 Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 * 
 * 
 * 方法是递归。观察到要是两个串相等，则必须满足：1含有相同的字母；
 * 2当把两个串分别拆成两部分后，第一串的两部分分别跟后一串的两部分相比，只要比得上一次就相等。
 * 了解规律之后，容易编写程序如下：
 * 
 * 解釋2
 * 简单的说，就是s1和s2是scramble的话，那么必然存在一个在s1上的长度l1，将s1分成s11和s12两段，同样有s21和s22。
 * 那么要么s11和s21是scramble的并且s12和s22是scramble的；
 * 要么s11和s22是scramble的并且s12和s21是scramble的。
 */
package BinaryTree;

public class ScrambleString {

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) {
            return true;
        }
        int[] A = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            A[s1.charAt(i) - 'a']++;
            A[s2.charAt(i) - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (A[i] != 0) {
                return false;
            }
        }

        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) {
                return true;
            }
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        String s1 = "great";
        String s2 = "rgeat";
        String s3 = "rgtae";
        String s4 = "aegrt";
        String s5 = "egatr";
        
        ScrambleString ss = new ScrambleString();
        System.out.println(ss.isScramble(s1, s2));
        System.out.println(ss.isScramble(s1, s3));
        System.out.println(ss.isScramble(s1, s4));
        System.out.println(ss.isScramble(s1, s5));
    }
}
