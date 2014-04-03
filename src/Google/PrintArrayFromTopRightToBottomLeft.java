/*
 * Give a N*N matrix, print it out diagonally. 
 Follow up, if it is a M*N matrix, how to print it out. 
 Example: 
 1 2 3 
 4 5 6 
 7 8 9 
 print: 
 1 
 2 4 
 3 5 7 
 6 8 
 9 
 */
package Google;

public class PrintArrayFromTopRightToBottomLeft {
    
    

    public static void main(String[] args) {
        int m = 4, n = 4;
        int arr[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 3, 6, 2}, {4, 7, 9, 0}};
        int diagonals = n + m - 1;
        int i = 0, j = 0;
        while (diagonals > 0) {
            int k = i, l = j;
            while (l >= 0 && k < m) {
                System.out.print(arr[k][l] + " ");
                l--;
                k++;
            }
            System.out.println();
            if (j < (n - 1)) {
                j++;
            } else {
                i++;
            }
            diagonals--;
        }

    }
}
