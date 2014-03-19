package TwoPointers;

public class RemoveDupicatesFromSortedArray {

    public int removeDuplicates(int[] A) {
        if (A.length < 2) {
            return A.length;
        }
        int i = 0;
        int j = i + 1;

        while (i < A.length && j < A.length) {
            if (A[i] == A[j]) {
                j++;
            } else {
                A[++i] = A[j];
                j++;
            }
        }
        return i + 1;
    }

    public int removeDuplicates2(int[] A) {
        if (A.length < 2) {
            return A.length;
        }
        int i = 0;
        int j = i + 1;
        int count = 0;

        while (i < A.length && j < A.length) {
            if (A[i] == A[j]) {
                if (count == 0) {
                    i++;
                    j++;
                    count++;
                } else {
                    j++;
                }
            } else {
                count = 0;
                A[++i] = A[j];
                j++;
            }
        }
        return i + 1;
    }

    public static void main(String[] args) {
        
    }
}
