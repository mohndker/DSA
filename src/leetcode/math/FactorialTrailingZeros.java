package leetcode.math;

public class FactorialTrailingZeros {
    public static void main(String[] args) {
        System.out.println(trailingZeros(30));
    }

    /**
     * Time O(log<sub>5</sub>(n))
     * Space O(1)
     * Trailing zeros are a factor of 10. since 10 = 2 * 5,
     * ever pair of 2 & 5 in the factorial's factors contributes one trailing zero.
     * In n!, there are more factors of 2 than 5. Thus, the number of trailing zeros is determined
     * by number of 5s in 1 - n.
     */
    private static int trailingZeros(int n) {
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }

        return count;
    }
}
