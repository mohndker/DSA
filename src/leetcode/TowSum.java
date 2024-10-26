package leetcode;

import java.util.Arrays;

public class TowSum {
    public static void main(String[] args) {
        int[] a = {1,2,3,4,4,9,56,90};
        System.out.println(Arrays.toString(twoSum(a, 8)));
    }

    public static int[] twoSum(int[] numbers, int target) {
        // int array, sorted in increasing, find num1 + num2 = target, 1 <= indx1 <= indx2 <= array.length,
        // return indices of the two numbers as an int array. can't add the same number twice. space O(1)

        // example = [4, 1, 2, 3, 3, 4, 7, 10], target = 4, ans = [1,4]
        // example = [-2, -1, 3, 5, 8]

        int n = numbers.length;
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int total = numbers[left] + numbers[right];
            if (total == target) break;
            if (total > target) right--;
            else left++;
        }

        return new int[] {left + 1, right + 1};
    }
}
