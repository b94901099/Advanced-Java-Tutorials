/*
 * 将一个数字的二进制形式以字符串的形式返回
 */
package Google;

public class IntegerToBitString {

    public String transfer(int n) {

        if (n < 0) {
            n = -n + 1;
        }

        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            sb.append(n % 2);
            n = n / 2;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        IntegerToBitString i = new IntegerToBitString();
        System.out.println(i.transfer((int) (Math.pow(2, 31) - 1)));
    }
}
