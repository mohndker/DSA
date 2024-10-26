package algorithmanalysis;

import assignments.collinear.In;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Random;

public class ThreeSum {
    public static void main(String[] args) {
//        writeRandomNumbersToFile(args[0]);

        int[] a = new In(args[0]).readAllInts();
        Instant start =  Instant.now();
        System.out.println(Arrays.toString(a));
        Instant end = Instant.now();
        System.out.println(count(a));
        System.out.println(Duration.between(start, end));
    }

    private static void writeRandomNumbersToFile(String path) {

        // Create an instance of Random class to generate random numbers
        Random random = new Random();
        File file = new File(path);
        System.out.println(file.exists());
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
//
//            // Generate random numbers and write them to the file
//            for (int i = 0; i < 1000; i++) {
//                int randomNumber = random.nextInt(1000);  // Generate a random number between 0 and 99
//                writer.write(Integer.toString(randomNumber));  // Convert the number to string and write it
//                writer.newLine();  // Write a new line after each number
//            }
//            System.out.println("Random numbers written to the file: " + path);
//        } catch (IOException e) {
//            System.err.println("Error while writing to the file: " + e.getMessage());
//        }
    }

    public static int threeSum(int[] a) {
        int n = a.length;
        Arrays.sort(a);
        int i = 0, j = 1, count = 0;
        while (j < n && i < n) {
            int sum = -(a[i++] + a[j++]);
            if (isComplementFound(a, sum))
                count++;

            if (j == n - 1) {
                j = 0;
                i++;
            }
        }

        return count;
    }

    private static boolean isComplementFound(int[] a, int sum) {
        int n = a.length - 1;
        int low = 0, high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (sum < a[mid]) high = mid - 1;
            else if (sum > a[mid]) low = mid + 1;
            else return true;
        }

        return false;
    }

    private static int count(int[] a) {
        int n = a.length;
        int count = 0;

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    if (a[i] + a[j] + a[k] == 0)
                        count++;
        return count;
    }
}
