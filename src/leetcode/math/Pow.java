package leetcode.math;

public class Pow {
    public static void main(String[] args) {
        System.out.println(powX(2, 6));
    }

    /**
     * Time O(log n)
     * Space O(1)
     * Use bitwise ops.
     * Example: x = 2, n = 5
     * Binary rep for n: 0101
     * If the least significant bit is 1, multiply result by x.
     * Multiply x * x.
     * Shift n to right unsigned >>> by 1. to avoid overflow when n = - 2^31.
     * Repeat.
     */
    private static double powX(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        double pow = 1;
        while (n != 0) {
            if ((n & 1) != 0) pow *= x;
            x *= x;
            n >>>= 1;
        }

        return pow;
    }
}
