package leetcode.array;

import java.util.Hashtable;
import java.util.Map;

public class IntegerToRoman {
    private static Map<Integer, String> roman;

    static {
        roman = new Hashtable<>();

        roman.put(1, "I");
        roman.put(5, "V");
        roman.put(10, "X");
        roman.put(50, "L");
        roman.put(100, "C");
        roman.put(500, "D");
        roman.put(1000, "M");
    }

    public IntegerToRoman() { }

    public String intToRoman(int num) {
        if (roman.containsKey(num))
            return roman.get(num);

        StringBuilder sb = new StringBuilder();
        int digit = 0;
        int digitPlace = 1;

        while (num > 0) {
            digit = num % 10;
            if (digit == 4)
                sb.insert(0, roman.get(digitPlace) + roman.get(digitPlace * 5));
            else if (digit == 9)
                sb.insert(0, roman.get(digitPlace) + roman.get(digitPlace * 10));
            else
                //TODO not implemented yet, for cases where digit is not 4 or 9
                sb.insert(0, roman.get(digitPlace));

            num /= 10;
            digitPlace *= 10;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman integerToRoman = new IntegerToRoman();

        System.out.println(integerToRoman.intToRoman(3749));
    }
}
