package leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
    public static void main(String[] args) {

    }

    public static int longestSubString(String s) {
        Set<Character> chs = new HashSet<>();

        int n = s.length();
        int left = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            while (chs.contains(c))
                chs.remove(s.charAt(left++));
            chs.add(c);
            max = Math.max(max, i - left + 1);
        }

        return max;
    }
}
