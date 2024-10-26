package assignments.pq;

import java.util.PriorityQueue;

public class taxicabNumbers {
    static class CubeSum implements Comparable<CubeSum> {
        int a, b, sum;

        CubeSum(int a, int b) {
            this.a = a;
            this.b = b;
            this.sum = a * a * a + b * b * b;
        }

        @Override
        public int compareTo(CubeSum other) {
            return Integer.compare(this.sum, other.sum);
        }
    }

    public static void findTaxicabNumbers(int n) {
        PriorityQueue<CubeSum> pq = new PriorityQueue<>();

        // Initialize the heap with pairs (a, 1) for a from 1 to n
        for (int a = 1; a <= n; a++) {
            pq.add(new CubeSum(a, 1));
        }

        CubeSum prev = null;

        while (!pq.isEmpty()) {
            CubeSum current = pq.poll();

            // Check for taxicab number
            if (prev != null && prev.sum == current.sum) {
                System.out.println(prev.a + "^3 + " + prev.b + "^3 = " + current.a + "^3 + " + current.b + "^3 = " + current.sum);
            }

            // Update previous sum
            prev = current;

            // Insert the next pair (a, b + 1) if b < n
            if (current.b < n) {
                pq.add(new CubeSum(current.a, current.b + 1));
            }
        }
    }

    public static void main(String[] args) {
        int n = 12;  // Example value for n
        findTaxicabNumbers(n);
    }
}
