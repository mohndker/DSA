package leetcode.intervals;

import java.util.ArrayList;
import java.util.List;

public class SummaryRanges {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 4, 5, 7};
        System.out.println(summaryRanges(nums));
    }

    public static List<String> summaryRanges(int[] nums) {
        // int sorted unipue array, [a, b] range of integers inclusive
        // nums = {-2, 0, 1, 2, 4, 5, 7}
        int n = nums.length;
        int end = 0;
        String s = "";
        List<String> list = new ArrayList<>();

        for (int start = 0; start < n; start++) {
            end = start;
            while (end + 1 < n && nums[end + 1] == nums[end] + 1) end++;
            if (end > start) s = nums[start] + "->" + nums[end];
            else s = String.valueOf(nums[start]);
            list.add(s);
            start = end;
        }

        return list;
    }
}
