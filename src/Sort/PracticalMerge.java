package Sort;

import java.util.Scanner;

public class PracticalMerge {

    private static final int CUTOFF = 7;

    private PracticalMerge() { }

    public static void sort(Comparable[] a) {
        Comparable[] aux = a.clone();
        sort(aux, a, 0, a.length - 1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] src, Comparable[] des, int lo, int hi) {
        if (hi <= lo + CUTOFF) {
            insertionSort(des, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(des, src, lo, mid);
        sort(des, src, mid + 1, hi);

        if (!less(src[mid + 1], src[mid])) {
            System.arraycopy(src, lo, des, lo, hi - lo + 1);
            return;
        }

        merge(src, des, lo, mid, hi);
    }

    public static void merge(Comparable[] src, Comparable[] des, int lo, int mid, int hi) {
        assert isSorted(des, lo, mid);
        assert isSorted(des, mid + 1, hi);

        int i = lo, j = mid + 1;
        for (int k = lo; k <= hi; k++) {
            if       (i > mid)              des[k] = src[j++];
            else if  (j > hi)               des[k] = src[i++];
            else if  (less(src[j], src[i])) des[k] = src[j++];
            else                            des[k] = src[i++];
        }

        assert isSorted(des, lo, hi);
    }

    private static void insertionSort(Comparable[] a, int lo, int hi) {
        for (int i = lo; i <= hi; i++)
            for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
                exch(a, j, j - 1);
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i - 1])) return false;
        }
        return true;
    }

    private static void show(Comparable[] a) {
        for (Comparable comparable : a)
            System.out.println(comparable);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] a = input.split(" ");
        PracticalMerge.sort(a);
        PracticalMerge.show(a);
    }
}
