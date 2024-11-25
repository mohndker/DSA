package leetcode.binarysearch;

import java.util.Arrays;

public class FindFirstAndLastElementIndexInSortedArray {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findBinarySearch(new int[]{5, 7, 7, 8, 8, 10}, 8)));
        System.out.println(Arrays.toString(findBinarySearch(new int[]{5, 7, 7, 8, 8, 10}, 6)));
        System.out.println(Arrays.toString(findBinarySearch(new int[]{ }, 0)));
        System.out.println(Arrays.toString(findBinarySearch(new int[]{1, 2}, 2)));
        System.out.println(Arrays.toString(findBinarySearch(new int[]{2, 2, 2, 2, 2, 2}, 2)));
    }

    /**
     * Time O(n)
     * Space O(1)
     * Brute force to find indices of starting and ending positions of target
     */
    private static int[] findBruteForce(int[] nums, int target) {
        if (nums == null) return new int[] {-1, -1};

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                int j = i;
                while (j < nums.length && nums[j] == target) j++;
                return new int[] {i, j - 1};
            }
        }

        return new int[] {-1, -1};
    }

    /**
     * Time O(log(n))
     * Space O(1)
     * Use binary search to find each index position.
     */
    private static int[] findBinarySearch(int[] nums, int target) {
        if (nums == null) return new int[] {-1, -1};
        int n = nums.length;
        int[] res = new int[] {-1, -1};

        int low = 0, high = n - 1;
        while (low < high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) low = mid + 1;
            else high = mid;
        }
        // If starting pos not found then target is not found.
        if (nums[low] != target) return res;
        else res[0] = low;
        // Notice low is the start position we found above no need to set it to 0.
        high = n - 1;
        while (low < high) {
            int mid = (low + high) / 2 + 1;
            if (nums[mid] > target) high = mid - 1;
            else low = mid;
        }

        res[1] = high;

        return res;
    }
}
