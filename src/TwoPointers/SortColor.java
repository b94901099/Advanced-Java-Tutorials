/*
 * Given an array with n objects colored red, white or blue, sort them so that objects 
 * of the same color are adjacent, with the colors in the order red, white and blue.
 * Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
 * Note: You are not suppose to use the library's sort function for this problem.
 * 
 * 1. 計算出現頻率
 * 2. 類似 quick sort, front pointer 對應 0, back pointer 對應 2, i 在中間跑對應 1
 */
package TwoPointers;

import java.util.Arrays;

public class SortColor {

    public void sortColors(int[] A) {
        if (A == null || A.length < 2) {
            return;
        }

        for (int out = 1; out < A.length; out++) {
            int tmp = A[out];
            int in = out;
            while (in > 0 && A[in - 1] > tmp) {
                A[in] = A[in - 1];
                in--;
            }
            A[in] = tmp;
        }
    }

    public static void sortColors1(int[] A) {
        int red = 0;
        int white = 0;
        int blue = 0;

        for (int i = 0; i < A.length; i++) {
            switch (A[i]) {
                case 0:
                    red++;
                    break;
                case 1:
                    white++;
                    break;
                case 2:
                    blue++;
                    break;
            }
        }

        int i;
        for (i = 0; i < red; i++) {
            A[i] = 0;
        }
        for (; i < red + white; i++) {
            A[i] = 1;
        }
        for (; i < A.length; i++) {
            A[i] = 2;
        }
    }

    public void sortColors2(int[] A) {
        // front指针指向数组的前部，back指针指向数组的后部  
        int i = 0, front = 0, back = A.length - 1;

        while (i < A.length) {
            if (i > back || i < front) {    // 始终要保持front<=i<=back  
                break;
            }
            switch (A[i]) {
                case 1:     // 跳过1  
                    i++;
                    break;
                case 0:     // 与front交换，然后front和i都往后移  
                    swap(A, i, front);
                    front++;
                    i++;
                    break;
                case 2:     // 与back交换，back往前移  
                    swap(A, i, back);
                    back--;
                    break;
            }
        }
    }

    private void swap(int[] A, int i, int toSwap) {
        int tmp = A[i];
        A[i] = A[toSwap];
        A[toSwap] = tmp;
    }

    public static void main(String[] args) {
        int[] A = {0, 2, 1, 2, 0, 2, 0, 2, 0, 2, 0, 1, 1, 0, 2, 1};
        SortColor s = new SortColor();
        s.sortColors2(A);
        System.out.println(Arrays.toString(A));
    }
}
