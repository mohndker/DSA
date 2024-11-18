package leetcode.math;

public class PalindromeNumber {
    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindromeReminder(121));
        System.out.println(isPalindromeCompareHalf(121));
    }

    /**
     * Time O(n) where n is number of digits in x(Since x is a 32 bit int, Time is O(1))
     * Space O(1)
     * Check the number if negative, or it's reminder is 0 then it is not palindrome.
     * Initialize two variables, rev(to hold the reversed num), num(copy of x to use it for comparison).
     * While the number is grater than 0, accumulate rev as follows,
     * (rev * 10) -> shift one digit to left
     * (num % 10) -> extracts the last digit
     */
    private static boolean isPalindromeReminder(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int rev = 0;
        int num = x;
        while (num > 0) {
            rev = (rev * 10) + (num % 10);
            num /= 10;
        }

        return rev == x;
    }

    /**
     * time O(n/2) n is number of digits in x. Same as above it's actually O(1).
     * Space O(1)
     * Same as above, the difference is to stop half way
     * Example:
     * 1221 is palindrome, reverse the second half starting from right will result in
     * rev = 12 and first half is in (x) after dividing it x = 12
     */
    private static boolean isPalindromeCompareHalf(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;

        int rev = 0;
        while (x > rev) {
            rev = (rev * 10) + (x % 10);
            x /= 10;
        }

        return x == rev || x == rev / 10;
    }

    /**
     * Time O(n)
     * Space O(n)
     * Naive approach
     * Convert x to string and reverse it.
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) return false;

        String num = String.valueOf(x);
        StringBuilder st = new StringBuilder(num).reverse();

        return num.contentEquals(st);
    }
}
