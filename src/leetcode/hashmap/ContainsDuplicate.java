package leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};

        System.out.println(containsNearByDuplicate(nums, 3));
    }


    /**
     * Time complexity O(n)
     * Spacy complexity O(n)
     */
    private static boolean containsNearByDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && i - map.get(nums[i]) <= k)
                return true;

            map.put(nums[i], i);
        }

        /*
        We can improve Space complexity to O(k) where k is the size of the window
        if (map.size() > k)
            map.remove(nums[i] - k);
         */

        return false;
    }
}
