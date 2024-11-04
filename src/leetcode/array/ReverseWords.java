package leetcode.array;

public class ReverseWords {

    public static void main(String[] args) {
        String s = "   Hello   World  from    Java  ";
        System.out.println("'" + reverseWords(s) + "'");
    }

    public static String reverseWords(String s) {
        char[] strArray = s.toCharArray();

        // Step 1: Reverse the entire string
        reverse(strArray, 0, strArray.length - 1);

        // Step 2: Reverse each word in the reversed string
        int start = 0, end = 0;
        while (start < strArray.length) {
            // Skip leading spaces
            while (start < strArray.length && strArray[start] == ' ') {
                start++;
            }

            end = start;
            // Find the end of the word
            while (end < strArray.length && strArray[end] != ' ') {
                end++;
            }

            // Reverse the current word
            reverse(strArray, start, end - 1);
            start = end;
        }

        // Step 3: Clean up spaces (remove leading, trailing, and extra spaces)
        return cleanSpaces(strArray);
    }

    // Helper function to reverse a portion of the array
    public static void reverse(char[] array, int start, int end) {
        while (start < end) {
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    // Helper function to clean up spaces
    public static String cleanSpaces(char[] array) {
        int n = array.length;
        int i = 0, j = 0;

        // Skip leading spaces
        while (j < n && array[j] == ' ') {
            j++;
        }

        // Process the rest of the characters
        while (j < n) {
            // Copy non-space characters
            while (j < n && array[j] != ' ') {
                array[i++] = array[j++];
            }

            // Skip multiple spaces
            while (j < n && array[j] == ' ') {
                j++;
            }

            // Add a single space between words
            if (j < n) {
                array[i++] = ' ';
            }
        }

        // Remove trailing space
        return new String(array).substring(0, i);
    }
}
