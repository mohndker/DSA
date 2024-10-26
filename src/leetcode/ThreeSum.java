package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {

    }

    public static List<List<Integer>> threeSum(int[] nums) {
        // int array, return all triplets at i, j, k such i!=j, i != k, j != k, i + j + k = 0;
        // nums = {-1, 0, 1, 2, -1, -4}
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int total = nums[i] + nums[j] + nums[k];

                if (total > 0) k--;
                else if (total < 0) j++;
                else {
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;

                    while (nums[j] == nums[j-1] && j < k) j++;
                }
            }
        }

        return res;
    }
}
