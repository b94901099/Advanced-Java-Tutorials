package Implement;

public class ReverseString {

    public static String reverse(String s) {
        int length = s.length(), last = length - 1;
        char[] chars = s.toCharArray();
        for (int i = 0; i < length / 2; i++) {
            char c = chars[i];
            chars[i] = chars[last - i];
            chars[last - i] = c;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        multTables(12);
    }

    public static void multTables(int max) {
        for (int i = 1; i <= max; i++) {
            for (int j = 1; j <= max; j++) {
                System.out.print(String.format("%5d", j * i));
            }
            System.out.println();
        }
    }
}
