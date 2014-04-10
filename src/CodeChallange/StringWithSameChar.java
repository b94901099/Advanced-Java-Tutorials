/*
 * 写一函数f(a,b)，它带有两个字符串参数并返回一串字符，该字符串只包含在两个串中都有的并按照在a中的顺序。
 * 写一个版本算法复杂度O(N^2)和一个O(N)
 */
package CodeChallange;

import java.util.*;

public class StringWithSameChar {

    public String f(String a, String b) {
        StringBuilder sb = new StringBuilder();
        HashSet<Character> set = new HashSet<Character>();
        for (char c : b.toCharArray()) {
            set.add(c);
        }

        for (int i = 0; i < a.length(); i++) {
            if (set.contains(a.charAt(i))) {
                sb.append(a.charAt(i));
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
    }
}
