/*
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
 * "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 */
package String;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        s = filter(s);
        String sRev = reverse(s);
        return s.compareTo(sRev) == 0 ? true : false;
    }

    //删除非字母非数字的字符过滤字符串
    private String filter(String s) {
        return s.replaceAll("[^a-z0-9]", "");
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        ValidPalindrome v = new ValidPalindrome();
    }
}
