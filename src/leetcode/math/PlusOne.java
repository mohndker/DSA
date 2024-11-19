package leetcode.math;

import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(plusOne(new int[]{9})));
        System.out.println(Arrays.toString(plusOne(new int[]{1})));
        System.out.println(Arrays.toString(plusOne(new int[]{1, 9, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9})));
    }

    private static int[] plusOne(int[] digits) {
        int n = digits.length;

        for (int i = n - 1; i >= 0; i--) {
            int digit = digits[i];
            if (digit < 9) {
                digits[i]++;
                return digits;
            }

            digits[i] = 0;
        }

        digits = new int[n + 1];
        digits[0] = 1;

        return digits;
    }
}
