package algorithmanalysis;

public class BitonicArray {
    public static void main(String[] args) {
        int[] bitonic = {1, 3, 5, 6, 8, 10, 9, 7, 6, 4, 2, 1};

        System.out.println(findPeak(bitonic));
        System.out.println(bitonicBinarySearch(bitonic, 7));
    }

    private static int bitonicBinarySearch(int[] a, int key) {
        int peak = findPeak(a);
        int element = binarySearch(a, key, 0, peak - 1);

        if (element == -1) {
            element = binarySearch(a, key, peak, a.length - 1);
        }

        return element;
    }

    private static int findPeak(int[] bitonic) {
        int n = bitonic.length - 1;

        int low = 0, high = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if ((mid == 0 || bitonic[mid] > bitonic[mid - 1]) && (mid == n || bitonic[mid] > bitonic[mid + 1]))
                return mid;
            else if (mid > 0 && bitonic[mid] < bitonic[mid - 1])
                high = mid  - 1;
            else low = mid + 1;
        }

        return -1;
    }

    private static int binarySearch(int[] a, int key, int low, int high) {
         while (low <= high) {
             int mid = low + (high - low) / 2;
             if (key > a[mid]) low = mid + 1;
             else if (key < a[mid]) high = mid - 1;
             else return mid;
         }

         return -1;
    }
}
