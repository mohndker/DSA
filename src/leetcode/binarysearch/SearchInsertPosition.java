package leetcode.binarysearch;

public class SearchInsertPosition {
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[] {1, 3, 5, 6, 9}, 5)); // out : 2
        System.out.println(searchInsert(new int[] {1, 3, 5, 6, 9}, 7)); // out : 4
        System.out.println(searchInsert(new int[] {0, 3, 5, 6, 9}, 1)); // out : 1
        System.out.println(searchInsert(new int[] {1, 3, 5, 6, 8}, 9)); // out : 5
    }

    /**
     * Time O(log n)
     * Space O(1)
     * Binary search to find the target position in an array.
     */
    private static int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (target < nums[mid]) high = mid - 1;
            else if (target > nums[mid]) low = mid + 1;
            else return mid;
        }

        return nums[low] < target ? low + 1 : low;
    }
}
