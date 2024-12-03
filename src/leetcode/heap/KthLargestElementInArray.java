package leetcode.heap;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KthLargestElementInArray {
    /**
     * Time O(n log(n))
     * Space O(1)
     * Sort the array.
     * Find the kth largest element in constant time.
     */
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);

        return nums[nums.length - k];
    }

    /**
     * Time O(N log(k))
     * Space O(k)
     * Use min priority queue to store the kth largest elements.
     */
    public static int findKthLargestHeap(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int val : nums) {
            pq.offer(val);
            if (pq.size() > k)
                pq.poll();
        }

        return pq.peek();
    }
}
