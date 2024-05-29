package sort;

import java.util.Comparator;
import java.util.Scanner;

public class Insertion {

    private Insertion() { }

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
            assert isSorted(a, 0, i);
        }
        assert isSorted(a);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        int n = a.length;
        for (int i = lo; i < hi; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(a, j, j - 1);
            }
        }
        assert isSorted(a, lo, hi);
    }

    public static void sort(Object[] a, Comparator comparator) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1], comparator); j--) {
                exch(a, j, j - 1);
            }
            assert isSorted(a, 0, i, comparator);
        }
        assert isSorted(a, comparator);
    }

    public static void sort(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo + 1; i < hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], comparator); j--) {
                exch(a, j, j - 1);
            }
        }
        assert isSorted(a, lo, hi, comparator);
    }

    public static int[] indexSort(Comparable[] a) {
        int n = a.length;
        int[] index = new int[n];
        for (int i = 0; i < n; i++) {
            index[i] = i;
        }
        for (int i = 1; i < n; i ++) {
            for (int j = i; j > 0 && less(a[j], a[j - 1]); j--) {
                exch(index, j, j - 1);
            }
        }
        return index;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static boolean less(Object v, Object w, Comparator comparator) {
        return comparator.compare(v, w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i < hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static boolean isSorted(Object[] a, int lo, int hi, Comparator comparator) {
        for (int i = lo + 1; i < a.length; i ++)
            if (less(a[i], a[i - 1], comparator)) return false;
        return true;
    }

    private static boolean isSorted(Object[] a, Comparator comparator) {
        return isSorted(a, 0, a.length, comparator);
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++)
            System.out.println(a[i]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] a = input.split(" ");
        Insertion.sort(a);
        Insertion.show(a);
    }
}
