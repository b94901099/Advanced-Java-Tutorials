/*
 * Given an input string, reverse the string word by word.
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * Clarification:
 * What constitutes a word?
 * A sequence of non-space characters constitutes a word.
 * Could the input string contain leading or trailing spaces?
 * Yes. However, your reversed string should not contain leading or trailing spaces.
 * How about multiple spaces between two words?
 * Reduce them to a single space in the reversed string.
 */
package String;

import java.util.Arrays;

public class ReverseWordsInAString {

    public static String reverseWords(String s) {
        int i = 0;
        StringBuilder result = new StringBuilder();
        while (i < s.length()) {
            while (i < s.length() && s.charAt(i) == ' ') {
                i++;
            }
            StringBuilder word = new StringBuilder();
            while (i < s.length() && s.charAt(i) != ' ') {
                word.append(s.charAt(i));
                i++;
            }
            if (word.length() > 0) {
                result.insert(0, word.toString() + " ");
            }
        }
        if (result.length() > 0) {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String s = "1 ";
        System.out.println("\"" + reverseWords(s) + "\"");
        System.out.println(Arrays.toString(s.split(" ")));
    }
}
