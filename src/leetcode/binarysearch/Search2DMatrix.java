package leetcode.binarysearch;

public class Search2DMatrix {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };

        System.out.println(searchMatrix(matrix, 3));                //true
        System.out.println(searchFlattenedMatrix(matrix, 3));       // true

        System.out.println(searchMatrix(matrix, 12));               // false
        System.out.println(searchFlattenedMatrix(matrix, 12));      // false
    }

    /**
     * Time O(log(m*n))
     * Space O(1)
     * Use binary search to find an element in matrix,
     * locate the row in which the element might be then search this row.
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int low = 0;
        int high = matrix.length - 1;

        // Binary search on rows
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (matrix[mid][0] == target) return true;
            else if (matrix[mid][0] > target) high = mid - 1;
            else if (matrix[mid][0] < target && matrix[mid][matrix[mid].length - 1] >= target) {
                // Call generic binary search on the specific row
                return binarySearch(matrix[mid], target);
            } else {
                low = mid + 1;
            }
        }

        return false;
    }

    /**
     * Time O(log(n))
     * Space O(1)
     * Binary search method.
     */
    private static boolean binarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (array[mid] == target) return true;
            else if (array[mid] < target) low = mid + 1;
            else high = mid - 1;
        }

        return false;
    }

    /**
     * Time O(log(m*n))
     * Space O(1)
     * Use binary search on a flattened matrix.
     * Treat the matrix as an array.
     */
    private static boolean searchFlattenedMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int low = 0, high = m * n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = matrix[mid / n][mid % n];
            if (midVal < target) low = mid + 1;
            else if (midVal > target) high = mid - 1;
            else return true;
        }

        return false;
    }
}
