package leetcode.math;

public class SqrtX {
    public static void main(String[] args) {
        System.out.println(mySqrt(4));
        System.out.println(mySqrtBinary(4));
    }

    /**
     * Time O(loglog(n))
     * Space O(1)
     * Using Newton's method (Babylonian)
     * It's very efficient and accurate.
     * The ugly cast on method return is due to leetcode requirements to return int,
     * the common approach is to use binary search see below.
     */
    private static int mySqrt(int x) {
        if (x < 2) return x;

        double x0 = x / 2.0;
        double x1;
        int count = 0;
        while (true) {
            x1 = (x0 + (x / x0)) / 2;
            if (x0 - x1 < 0.001) break;
            x0 = x1;
            count++;
        }
        System.out.println(count);
        return (int) x1;
    }

    /**
     * Time O(log(n))
     * Space O(1)
     * Use binary search.
     */
    private static int mySqrtBinary(int x) {
        if (x < 2) return x;
        int low = 0;
        int high = x;
        int count = 0;
        while (low < high) {
            int mid = low + (high - low) / 2;

            if (mid == x / mid) return mid;
            else if (mid < x / mid) low = mid + 1;
            else high = mid - 1;
            count++;
        }
        System.out.println(count);
        return high;
    }
}
