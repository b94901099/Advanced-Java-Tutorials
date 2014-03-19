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
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            if (Character.isLetterOrDigit(s.charAt(i))) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private String reverse(String s) {
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
    }
}
