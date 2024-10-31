package leetcode;

import java.util.Arrays;

public class MinArrowsNumToBurstBalloons {
    public static void main(String[] args) {

    }

    private static int findMinArrowShots(int[][] points) {
        if (points == null) return 0;
        int n = points.length;
        if (n <= 1) return n;

        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int end = points[0][1];
        int arrows = 1;

        for (int i = 1; i < n; i++) {
            if (end < points[i][0]) {
                end = points[i][1];
                arrows++;
            }
        }

        return arrows;
    }
}
