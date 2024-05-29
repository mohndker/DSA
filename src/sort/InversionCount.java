package sort;

public class InversionCount {

    private static int INVERSIONS_COUNT = 0;

    private InversionCount() { }

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[a.length];

        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n - len; lo += 2 * len) {
                int mid = lo + len - 1;
                int hi = Math.min(lo + (2 * len) - 1, n - 1);
                merge(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo, j = mid + 1;
        for (int k = i; k <= hi; k++) {
            if      (i > mid)               a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i])){
                a[k] = aux[j++];
                INVERSIONS_COUNT += mid - i + 1;
            }
            else                            a[k] = aux[i++];
        }
        assert  isSorted(a, lo, hi);
    }

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i - 1])) return false;
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void show(Comparable[] a) {
        for (Comparable item : a)
            System.out.print(item + " ");
    }

    public static void main(String[] args) {
        Comparable[] arr = {6, 5, 4, 3, 2, 1};
        InversionCount.show(arr);
        System.out.println();
        InversionCount.sort(arr);
        System.out.println("Number of inversions: " + INVERSIONS_COUNT); // Expected output: 15
        InversionCount.show(arr);
    }
}
