/*
 * Question:
 * Given an array of integers, every element appears twice except for one. Find that single one.
 *
 * Hint:
 * The key to solve this problem is bit manipulation.
 * XOR will return 1 only on two different bits.
 * So if two numbers are the same, XOR will return 0. Finally only one number left.
 */
package BitManipulation;

public class SingleNumber {

    public int singleNumber(int[] A) {
        int x = 0;

        for (int a : A) {
            x = x ^ a;
        }

        return x;
    }

    public static void main(String[] args) {
    }
}
