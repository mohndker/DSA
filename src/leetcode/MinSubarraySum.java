package leetcode;

public class MinSubarraySum {
    public static void main(String[] args) {
        int [] nums = {2, 3, 1, 2, 4, 3};
        System.out.println(minSubArrayLength(nums, 7));
    }

    public static int minSubArrayLength(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int sum = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            sum += nums[i];

            while (sum >= target) {
                if (i - left + 1 < min) min = i - left + 1;

                sum -= nums[left++];
            }
        }

        return min != Integer.MAX_VALUE ? min : 0;
    }
}
