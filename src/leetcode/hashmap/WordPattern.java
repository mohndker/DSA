package leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class WordPattern {
    public static void main(String[] args) {
        String s = "cat dog cat dog";
        String pattern = "abba";

        System.out.println(wordPattern(pattern, s));
    }

    public static boolean wordPattern(String pattern, String s) {
        int patternLen = pattern.length();
        String[] words = s.split(" ");
        int wordsLen = words.length;

        if (wordsLen != patternLen) return false;

        Map<Character, String> patternToWord = new HashMap<>();

        for (int i = 0; i < patternLen; i++) {
            char c = pattern.charAt(i);
            String word = words[i];

            if (!patternToWord.containsKey(c) && !patternToWord.containsValue(word)) {
                patternToWord.put(c, word);
            } else if (patternToWord.containsKey(c)) {
                if (!patternToWord.get(c).equals(word)) return false;
            } else if (patternToWord.containsValue(word)) return false;
        }

        return true;
    }
}
