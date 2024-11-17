package leetcode.bitmanipulation;

import java.util.HashMap;
import java.util.Map;

public class SingleNumberII {
    public static void main(String[] args) {
        System.out.println(singleNumberBit(new int[] {2, 2, 2, 1, 1, 1, 5}));
        System.out.println(singleNumber(new int[] {2, 2, 2, 1, 1, 1, 5}));
        System.out.println(singleNumberBrute(new int[] {2, 2, 2, 1, 1, 1, 5}));
    }

    /**
     * Time O(n)
     * Space O(n)
     * Use Hash map to store each number with its frequencies.
     * return the number that has a freq of 1.
     */
    private static int singleNumber(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums)
            map.put(num, map.getOrDefault(num, 0) + 1);
        for (int num : map.keySet())
            if (map.get(num) == 1)
                return num;

        return -1;
    }

    /**
     * Time O(n)
     * Space O(1)
     * Keep track of the bit occurrences in ones and twos vars
     * when a bit appears fot the first time, it's added to ones
     * when a bit appears for the second time, it's moved from ones to twos
     * when a bit appears for the third time, it's removed from both
     * finally the ones will contain bits from the number that appear exactly once.
     */
    private static int singleNumberBit(int[] nums) {
        int ones = 0;
        int twos = 0;
        for (int num : nums) {
            ones ^= (num & ~twos);
            twos ^= (num & ~ones);
        }

        return ones;
    }

    /**
     * Time O(n^2)
     * Space O(1)
     * Loop twice, for each number check if it appears more than once break the second loop
     * and move to next number, if you find a number that appears only once return it otherwise return -1.
     */
    private static int singleNumberBrute(int[] nums) {
        boolean isSingle = false;
        for (int i = 0; i < nums.length; i++) {
            isSingle = true;
            for (int j = 0; j < nums.length; j++) {
                if ((i != j) && nums[i] == nums[j]) {
                    isSingle = false;
                    break;
                }
            }
            if (isSingle)
                return nums[i];
        }

        return -1;
    }
}
