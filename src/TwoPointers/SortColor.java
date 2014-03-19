package TwoPointers;

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

    public static void main(String[] args) {
    }
}
