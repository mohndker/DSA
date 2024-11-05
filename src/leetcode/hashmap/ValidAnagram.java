package leetcode.hashmap;

import java.util.Arrays;

public class ValidAnagram {
    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";

        System.out.println(isAnagram1(s, t));
    }

    private static boolean isAnagram(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) return false;
        char[] sSequence = s.toCharArray();
        char[] tSequence = t.toCharArray();

        Arrays.sort(sSequence);
        Arrays.sort(tSequence);

        for (int i = 0; i < sSequence.length; i++) {
            if (sSequence[i] != tSequence[i]) return false;
        }

        return true;
    }

    private static boolean isAnagram1(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if (sLen != tLen) return false;
        int[] freq = new int[26];

        for (char c : s.toCharArray())
            freq[c - 'a']++;

        for (char c : t.toCharArray())
            freq[c - 'a']--;

        for (int val : freq)
            if (val != 0)
                return false;

        return true;
    }
}
