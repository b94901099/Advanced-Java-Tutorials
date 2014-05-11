package String;

/**
 * Given an input string, reverse the string word by word. For example, Given s
 * = "the sky is blue", return "blue is sky the". Clarification: What
 * constitutes a word? A sequence of non-space characters constitutes a word.
 * Could the input string contain leading or trailing spaces? Yes. However, your
 * reversed string should not contain leading or trailing spaces. How about
 * multiple spaces between two words? Reduce them to a single space in the
 * reversed string.
 */
import java.util.*;

public class ReverseWordsInAString {

    /**
     * 反轉每個字, 再反轉整個String, 算法巧妙, 但碰到空格不會過
     * @param s
     * @return
     */
    public static String reverseWords(String s) {
        if (s.length() < 2)
            return s;

        char[] arr = s.toCharArray();
        int left = 0;
        int count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == ' ' && count != 0) {
                swap(arr, left, i - 1);
                left = i + 1;
                count = 0;
            } else {
                count++;
            }
        }

        swap(arr, 0, arr.length - 1);
        return new String(arr);
    }


    private static void swap(char[] arr, int p, int q) {
        while (p < q) {
            char c = arr[p];
            arr[p] = arr[q];
            arr[q] = c;
            p++;
            q--;
        }
    }



    public static String reverseWords2(String s) {
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
        String s = "aa    b   cc";
        System.out.println("\"" + reverseWords(s) + "\"");
        System.out.println("\"" + reverseWords3(s) + "\"");
        System.out.println(Arrays.toString(s.split(" ")));
    }

    public static String reverseWords3(String s) {

        StringBuilder result = new StringBuilder();
        int left = 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ' && i != s.length() - 1) {
                count++;
            } else if (s.charAt(i) != ' ' && i == s.length() - 1) {
                String str = s.substring(left, i + 1);
                result.insert(0, str + " ");
            } else {
                if (count == 0) {
                    left = i + 1;
                } else {
                    String str = s.substring(left, i);
                    result.insert(0, str + " ");
                    left = i + 1;
                    count = 0;
                }
            }
        }

        if (result.length() == 0) {
            return "";
        }

        result.deleteCharAt(result.length() - 1);

        return result.toString();
    }
}
