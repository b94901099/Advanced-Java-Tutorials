/*
 * 整形数组加一
 */
package CodeChallange;

import java.util.Arrays;

public class PlusOne {

    public int[] plusOne(int[] num) {
        int carry = 1;
        for (int i = num.length - 1; i >= 0; i--) {
            int sum = num[i] + carry;
            num[i] = sum % 10;
            carry = sum / 10;
        }
        if (carry == 0) {
            return num;
        }
        int[] result = new int[num.length + 1];
        result[0] = carry;
        System.arraycopy(num, 0, result, 1, num.length);
        return result;
    }

    public static void main(String[] args) {
        PlusOne p = new PlusOne();
        int[] test = {9,9};
        int[] result = p.plusOne(test);
        System.out.println(Arrays.toString(result));
    }
}
