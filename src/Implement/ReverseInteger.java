package Implement;

import java.util.Stack;

public class ReverseInteger {

    public static void main(String[] args) {
        ReverseInteger r = new ReverseInteger();
        System.out.println(r.reverse(123));
    }

    public int reverse(int x) {
        int r = 0;
        while(x != 0){
            r = r * 10 + x % 10;
            x = x / 10;
        }
        return r;
    }
}
