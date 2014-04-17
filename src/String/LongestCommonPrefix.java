package String;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 */

public class LongestCommonPrefix {

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }

        for (int k = 0; k < strs[0].length(); k++) {
            char tempChar = strs[0].charAt(k);
            for (int i = 1; i < strs.length; i++) {// if such character not exist or unequal
                if (strs[i].length() <= k || strs[i].charAt(k) != tempChar) {
                    if (k == 0) {
                        return "";// no common prefix
                    } else {
                        return strs[0].substring(0, k);
                    }
                }
            }
        }
        return strs[0];
    }

    public static void main(String[] args) {
        String s = "abc";
        System.out.println(s.substring(0, 0));
    }
}
