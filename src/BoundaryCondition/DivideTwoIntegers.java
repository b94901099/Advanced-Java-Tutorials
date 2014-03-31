/*
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * 解法: there's two while loop here, if the temp_divisor bigger than the current dividend,
 * set the temp_divisor to the initial value and start the loop again.
 */
package BoundaryCondition;

public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        int sign = 1;
        if ((dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)) {
            sign = 1;
        } else {
            sign = -1;
        }
        long dividend_pos = Math.abs(dividend);
        long divisor_pos = Math.abs(divisor);
        int count = 0;

        while (dividend_pos >= divisor_pos) {
            long temp = divisor_pos;
            int multi = 1;
            while (dividend_pos >= temp) {
                count = count + multi;
                dividend_pos = dividend_pos - temp;
                temp = temp + temp;
                multi = multi + multi;
                System.out.println("dividend: " + dividend_pos + ", temp: " + temp
                        + ", count: " + count);
            }
        }
        return sign * count;
    }

    public static void main(String[] args) {
        DivideTwoIntegers d = new DivideTwoIntegers();
        System.out.println(d.divide(100, 2));
    }
}
