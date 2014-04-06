/*
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
package Implement;

import java.util.Arrays;

public class RemoveElement {

    int removeElement(int A[], int n, int elem) {
        int num = 0;
        for (int i = 0; i < n; ++i) {
            if (A[i] != elem) {
                A[num++] = A[i];
            }
        }
        return num;
    }

    public static void main(String[] args) {
        RemoveElement r = new RemoveElement();
        int[] test = {3, 1, 1, 2, 1, 3, 1};
        System.out.println(r.removeElement(test, test.length, 1));
    }

}
