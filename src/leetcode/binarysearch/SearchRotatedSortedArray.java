package leetcode.binarysearch;

public class SearchRotatedSortedArray {
    public static void main(String[] args) {
        System.out.println(searchRotatedArray(new int[] {1}, 1));                       // 0
        System.out.println(searchRotatedArray(new int[] {0}, 1));                       // -1
        System.out.println(searchRotatedArray(new int[] {1, 2}, 2));                    // 1
        System.out.println(searchRotatedArray(new int[] {2, 1}, 1));                    // 1
        System.out.println(searchRotatedArray(new int[] {1, 2}, 0));                    // -1
        System.out.println(searchRotatedArray(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 4));  // 3
        System.out.println(searchRotatedArray(new int[] {1, 2, 3, 4, 5, 6, 7, 8}, 9));  // -1
        System.out.println(searchRotatedArray(new int[] {5, 6, 7, 8, 1, 2, 3, 4}, 9));  // -1
        System.out.println(searchRotatedArray(new int[] {7, 8, 1, 2, 3, 4, 5, 6}, 8));  // 1
        System.out.println(searchRotatedArray(new int[] {7, 8, 1, 2, 3, 4, 5, 6}, 4));  // 5
        System.out.println(searchRotatedArray(new int[] {7, 8, 1, 2, 3, 4, 5, 6}, 2));  // 3
        System.out.println(searchRotatedArray(new int[] {7, 8, 1, 2, 3, 4, 5, 6}, 6));  // 7
    }

    /**
     * Time O(log(n))
     * Space O(1)
     * Use binary search to find the target.
     * The trick is to know whether to search in left or right part,
     * since the array might be rotated, first find where the target might be,
     * by checking if it falls into one of the ranges [low - mid] || [mid - high].
     */
    private static int searchRotatedArray(int[] nums, int target) {
        int n = nums.length;
        if (n == 1) return nums[0] == target ? 0 : -1;

        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = nums[mid];
            if (target == midVal) return mid;
            if (nums[low] <= midVal) {
                if (nums[low] <= target && target < midVal) high = mid - 1;
                else low = mid + 1;
            } else {
                if (target > midVal && target <= nums[high]) low = mid + 1;
                else high = mid - 1;
            }
        }

        return -1;
    }
}
