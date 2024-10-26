package leetcode;

import java.util.ArrayList;
import java.util.List;

public class ThreeSum {
    public static void main(String[] args) {

    }



    public static int findComplement(int[] a, int key, int low, int high) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key < a[mid]) high = mid - 1;
            else if (key > a[mid]) low = mid + 1;
            else return mid;
        }

        return -1;
    }
}
