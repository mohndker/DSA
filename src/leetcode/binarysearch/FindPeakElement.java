package leetcode.binarysearch;

public class FindPeakElement {
    public static void main(String[] args) {
        System.out.println(findPeak(new int[] {1, 2, 3, 4}));   // 3
        System.out.println(findPeak(new int[] {1}));            // 0
        System.out.println(findPeak(new int[] {1, 2}));         // 1
        System.out.println(findPeak(new int[] {1, 2, 4, 3}));   // 2
        System.out.println(findPeak(new int[] {1, 2, 1, 3, 5, 6, 4}));  // 1 || 5
    }

    /**
     * Time O(log(n))
     * Space O(1)
     * Use binary search to find the peak value.
     * This works only if the array doesn't contain consecutive equal values.
     */
    private static int findPeak(int[] nums) {
        int n = nums.length;
        if (n == 1) return 0;
        if (nums[0] > nums[1]) return 1;
        if (nums[n - 1] > nums[n - 2]) return n - 1;

        int low = 1;
        int high = n - 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midVal = nums[mid];
            int prevVal = nums[mid - 1];
            int afterVal = nums[mid + 1];
            if (midVal > prevVal && midVal > afterVal)
                return mid;
            else if (midVal < prevVal) high = mid - 1;
            else low = mid + 1;
        }

        return -1;
    }
}
