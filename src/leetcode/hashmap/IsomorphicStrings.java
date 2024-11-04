package leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class IsomorphicStrings {
    public static void main(String[] args) {
        String s = "egg";
        String t = "add";
        System.out.println(isIsomorphic2(s, t));
    }

    private static boolean isIsomorphic(String s, String t) {
        int sN = s.length();
        int tN = t.length();

        if (sN != tN) return false;

        Map<Character, Integer> sChars = new HashMap<>();
        Map<Character, Integer> tChars = new HashMap<>();

        for (int i = 0; i < sN; i++) {
            char sch = s.charAt(i);
            char tch = t.charAt(i);
            if (!sChars.containsKey(sch))
                sChars.put(sch, i);

            if (!tChars.containsKey(tch))
                tChars.put(tch, i);

            if (!sChars.get(sch).equals(tChars.get(tch)))
                return false;
        }

        return true;
    }

    private static boolean isIsomorphic2(String s, String t) {
        int sN = s.length();
        int tN = t.length();

        if (sN != tN) return false;

        int[] sChars = new int[200];
        int[] tChars = new int[200];

        for (int i = 0; i < sN; i++) {
            char sch = s.charAt(i);
            char tch = t.charAt(i);
            if (sChars[sch] != tChars[tch])
                return false;
            sChars[sch] = i + 1;
            tChars[tch] = i + 1;
        }

        return true;
    }
}
