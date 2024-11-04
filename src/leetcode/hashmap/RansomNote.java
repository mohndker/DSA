package leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class RansomNote {
    public static void main(String[] args) {

    }

    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;

        int[] count = new int[26];

        for (char c : magazine.toCharArray())
            count[c - 'a']++;

        for (char c : ransomNote.toCharArray()) {
            if (count[c - 'a'] == 0) return false;
            count[c - 'a']--;
        }

        return true;
    }

    public boolean canConstruct2(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;

        Map<Character, Integer> map = new HashMap<>();

        for (char c : magazine.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (char c : ransomNote.toCharArray()) {
            if (!map.containsKey(c) || map.get(c) <= 0) return false;
            map.put(c, map.get(c) - 1);
        }

        return true;
    }
}
