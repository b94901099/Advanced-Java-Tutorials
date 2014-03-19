/*
 * Divide two integers without using multiplication, division and mod operator.
 */
package Implement;

public class DivideTwoIntegers {

    public static int divide(int dividend, int divisor) {
        int sign = 1;
        if (dividend < 0) {
            sign *= -1;
        }
        if (divisor < 0) {
            sign *= -1;
        }
        long a = dividend;
        long b = divisor;
        //must cast to long here for dealing with the Integer.MIN_VALUE  
        //because Math.abs(-2147483648) > Integer.MAX_VALUE  
        a = Math.abs(a);
        b = Math.abs(b);
        int count = 0;
        while (a >= b) {
            long temp = b;
            int multi = 1;
            while (a >= temp) {
                count += multi;
                a -= temp;
                temp += temp;
                multi += multi;
            }
        }
        return count * sign;
    }

    public static int divideOvertime(int dividend, int divisor) {
        int result = 0;
        int sign = 1;

        if (dividend < 0) {
            sign = -1 * sign;
        }
        if (divisor < 0) {
            sign = -1 * sign;
        }

        if (divisor == 1) {
            return dividend;
        }

        while (Math.abs(dividend) >= Math.abs(divisor)) {
            dividend = dividend - divisor;
            result++;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(divide(-1, 1));
    }
}
