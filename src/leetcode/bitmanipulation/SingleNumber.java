package leetcode.bitmanipulation;

import java.util.HashMap;
import java.util.Map;

public class SingleNumber {
    public static void main(String[] args) {
        System.out.println(singleNumber(new int[] {2, 2, 1}));
        System.out.println(singleNumberHash(new int[] {2, 2, 1}));
        System.out.println(singleNumberBruteForce(new int[] {2, 2, 1}));
    }

    /**
     * Time O(n)
     * Space O(1)
     * Bitwise using XOR, we know that
     * 1 XOR 1 = 0
     * 1 XOR 0 = 1
     * 0 XOR 1 = 1
     * 0 XOR 0 = 0
     * XOR properties
     * A XOR A = 0 where A is any int
     * A XOR B XOR A = B
     * Since it is guaranteed the array has only one single number
     * and all other numbers appears twice, XORing all of them will
     * result in the single number.
     */
    private static int singleNumber(int[] nums) {
        int sum = 0;

        for (int n : nums) {
            sum ^= n;
        }

        return sum;
    }

    /**
     * Time O(n)
     * Space O(n)
     * Using HashMap, store key -> value pairs where key = num and value = frequency
     * Loop over the map key set and return the key where value is 1;
     */
    public static int singleNumberHash(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : map.keySet())
            if (map.get(num) == 1)
                return num;

        return -1;
    }

    /**
     * Time O(n^2)
     * Space O(1)
     * Use two for loops, brute fore.
     */
    public static int singleNumberBruteForce(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            boolean isSingle = true;
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
