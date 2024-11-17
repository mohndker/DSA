package leetcode.bitmanipulation;

public class BitwiseAndOfNumbersRange {
    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(5, 7));
        System.out.println(rangeBitwiseAnd2(5, 7));
    }

    private static int rangeBitwiseAnd(int left, int right) {
        while (right > left)
            right &= right - 1;

        return right;
    }

    private static int rangeBitwiseAnd2(int left, int right) {
        int count = 0;
        while (left != right) {
            left >>= 1;
            right >>= 1;
            count++;
        }

        return left << count;
    }
}
