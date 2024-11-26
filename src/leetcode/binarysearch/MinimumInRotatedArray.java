package leetcode.binarysearch;

public class MinimumInRotatedArray {
    public static void main(String[] args) {
        System.out.println(findMin(new int[] {1}));
        System.out.println(findMin(new int[] {1, 2}));
        System.out.println(findMin(new int[] {1, 2, 3, 4, 5, 6}));
        System.out.println(findMin(new int[] {5, 6, 1, 2, 3, 4}));
        System.out.println(findMin(new int[] {5, 6, 7, 1, 2, 3}));
        System.out.println(findMin(new int[] {3, 5, 6, 7, 1, 2}));
        System.out.println(findMin(new int[] {3, 5, 6, 7, 8, 1}));
    }

    /**
     * Time O(log(n))
     * Space O(1)
     * Use binary search to find the min in rotated array.
     */
    private static int findMin(int[] nums) {
        int n = nums.length;
        if (n < 1) return -1;
        int low = 0, high = n - 1;

        while (low < high) {
            if (nums[low] < nums[high]) return nums[low];

            int mid = low + (high - low) / 2;

            if (nums[mid] >= nums[low]) low = mid + 1;
            else high = mid;
        }

        return nums[low];
    }

    /**
     * Time O(n)
     * Space O(1)
     * Brute force to find min in rotated array
     */
    private static int findMinBruteForce(int[] nums) {
        int n = nums.length;
        
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (nums[i] < min) min = nums[i];
        }
        
        return min;
    }
}
