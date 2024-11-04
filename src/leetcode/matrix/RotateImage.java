package leetcode.matrix;

import java.util.Arrays;

public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = getBoard();
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    private static void rotate(int[][] matrix) {
        int rows = matrix.length;
        int cols = rows;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (i < j) {
                    int temp = matrix[i][j];
                    matrix[i][j] = matrix[j][i];
                    matrix[j][i] = temp;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][cols - 1 - j];
                matrix[i][cols - 1 - j] = temp;
            }
        }
    }

    private static int[][] getBoard() {
        return new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
    }
}
