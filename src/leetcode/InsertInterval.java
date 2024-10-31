package leetcode;

import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},
                {6, 9}
        };

        int[] newInterval = {2, 5};

        insert(intervals, newInterval);
    }

    private static int[][] insert(int[][] intervals, int[] newInterval) {
        int n = intervals.length;
        if (n == 0) return new int[][]{newInterval};

        List<int[]> list = new ArrayList<>();
        int i = 0;
        while (i < n && intervals[i][1] < newInterval[0])
            list.add(intervals[i++]);

        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        list.add(newInterval);

        while (i < n) list.add(intervals[i++]);

        return list.toArray(new int[list.size()][]);
    }
}
