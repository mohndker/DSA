package leetcode.intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeIntervals {
    public static void main(String[] args) {
        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        merge(intervals);
    }

    public static int[][] merge(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) return intervals;
        /*
          int[][] intervals = {
            {1, 3},
            {2, 6},
            {8, 10},
            {15, 18}
        };
        */
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> list = new ArrayList<>();

        int[] currentInterval = intervals[0];
        list.add(currentInterval);

        for (int i = 1; i < n; i++) {
            int currentEnd = currentInterval[1];
            int nextStart = intervals[i][0];
            int nextEnd = intervals[i][1];

            if (currentEnd >= nextStart)
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            else {
                currentInterval = intervals[i];
                list.add(currentInterval);
            }
        }

        return list.toArray(new int[list.size()][]);
    }
}
