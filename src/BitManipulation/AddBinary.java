/*
 * Given two binary strings, return their sum (also a binary string).
 * For example, a = "11", b = "1". Return "100".
 */
package BitManipulation;

import java.util.*;

public class AddBinary {

    public static String addBinary(String a, String b) {
        if (a.length() < 1 && b.length() < 1) {
            return "";
        }
        String ra = new StringBuilder(a).reverse().toString();
        String rb = new StringBuilder(b).reverse().toString();
        int[] digits = new int[ra.length() + rb.length()];
        int carry = 0;

        for (int i = 0; i < digits.length; i++) {
            int na = 0;
            int nb = 0;
            if (i < ra.length()) {
                na = ra.charAt(i) - '0';
            }
            if (i < rb.length()) {
                nb = rb.charAt(i) - '0';
            }

            int sum = na + nb + carry;
            carry = sum / 2;
            digits[i] = sum % 2;
        }

        StringBuilder sb = new StringBuilder();

        for (int i : digits) {
            sb.append(i);
        }

        sb = sb.reverse();

        while (sb.charAt(0) == '0' && sb.length() > 1) {
            sb.deleteCharAt(0);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("0", "0"));
    }
}
