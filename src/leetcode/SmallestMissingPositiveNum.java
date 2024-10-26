package leetcode;

import java.util.Arrays;

public class SmallestMissingPositiveNum {
    public static void main(String[] args) {

        int[] arr = {-1, 0, 1, 2, 3, 4, 5, 6};
        System.out.println(findSmallestPositiveMissingNum(arr));
    }

    public static int findSmallestPositiveMissingNum(int[] a) {
        int n = a.length;
        if (n <= 1) return 1;
        for (int i = 0; i < n; i++) {
            while (a[i] >= 1 && a[i] <= n && a[i] != a[a[i] - 1]) {
                int temp = a[i];
                a[i] = a[a[i] - 1];
                a[temp - 1] = temp;
            }
        }

        for (int i = 1; i <= n; i++)
            if (i != a[i - 1]) return i;

        return n + 1;
    }
}
