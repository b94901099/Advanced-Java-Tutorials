/*
 * [1,9] + 1 = [2,0]
 * [9,9] + 1 = [1,0,0]
 */
package Implement;

import java.util.Arrays;

public class PlusOne {

    public static int[] plusOne(int[] digits) {
        int p = digits.length - 1;
        int carry = 1;
        while (p >= 0) {
            int sum = digits[p] + carry;
            carry = sum / 10;
            digits[p] = sum % 10;
            p--;
        }
        if (carry != 0) {
            int[] newArray = new int[digits.length + 1];
            newArray[0] = carry;
            System.arraycopy(digits, 0, newArray, 1, digits.length);
            return newArray;
        }
        return digits;
    }
    
    public static void main(String[] args) {
        int[] digits = {9,9};
        System.out.println(Arrays.toString(plusOne(digits)));
    }
}
