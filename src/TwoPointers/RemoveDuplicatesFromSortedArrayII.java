/*
 * Follow up for "Remove Duplicates":
 * What if duplicates are allowed at most twice?
 * For example,
 * Given sorted array A = [1,1,1,2,2,3],
 * Your function should return length = 5, and A is now [1,1,2,2,3].
 */
package TwoPointers;

public class RemoveDuplicatesFromSortedArrayII {
    
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
