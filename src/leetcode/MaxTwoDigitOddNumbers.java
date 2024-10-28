package leetcode;

import java.util.*;

public class MaxTwoDigitOddNumbers {
    public static void main(String[] args) {
        String s = "126138944335";
        // 41, 83, 29, 41

        System.out.println(maxTwoDigitOddNumbers(s));
    }

    private static int maxTwoDigitOddNumbers(String s) {
        // Separate digits into odd and even lists
        List<Character> oddDigits = new ArrayList<>();
        List<Character> evenDigits = new ArrayList<>();

        // Separate the digits in `s` into odd and even lists
        for (char c : s.toCharArray()) {
            if ((c - '0') % 2 != 0) { // If the digit is odd
                oddDigits.add(c);
            } else { // If the digit is even
                evenDigits.add(c);
            }
        }

        int maxPairs = 0;
        if (oddDigits.size() < evenDigits.size()) {
            maxPairs = oddDigits.size();
        } else {
            maxPairs = evenDigits.size() + ((oddDigits.size() - evenDigits.size()) / 2);
        }
        return maxPairs;
    }
}
