package BinarySearch;

public class SearchA2DMatrix {

    public static void main(String[] args) {
        SearchA2DMatrix s = new SearchA2DMatrix();
        int[][] i = {{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 50}};
        System.out.println(s.searchMatrix(i, 3));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        int numRow = matrix.length;
        int numCol = matrix[0].length;
        int start = 0;
        int end = numRow * numCol - 1;
        int mid;

        while (start + 1 < end) {
            mid = start + (end - start) / 2;
            if (matrix[mid / numCol][mid % numCol] == target) {
                return true;
            }
            if (matrix[mid / numCol][mid % numCol] > target) {
                end = mid;
            } else if (matrix[mid / numCol][mid % numCol] < target) {
                start = mid;
            }
        }

        if (matrix[start / numCol][start % numCol] == target) {
            return true;
        }
        if (matrix[end / numCol][end % numCol] == target) {
            return true;
        }
        return false;
    }
}
