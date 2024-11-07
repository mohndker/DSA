package leetcode.hashmap;

import java.util.HashSet;
import java.util.Set;

public class HappyNmber {
    public static void main(String[] args) {
        int num = 2;
        int num1 = 19;

        System.out.println(isHappyNumberSet(num1));
    }

    private static boolean isHappyNumberSet(int num) {
        if (num == 1) return true;

        Set<Integer> set = new HashSet<>();

        while (!set.contains(num)) {
            set.add(num);
            num = calculateSum(num);
            if (num == 1) return true;
        }

        return false;
    }

    private static boolean isHappyNumber(int num) {
        if (num == 1) return true;

        int slow = calculateSum(num);
        int fast = calculateSum(calculateSum(num));

        while (slow != fast) {
            if (fast == 1) return true;
            slow = calculateSum(slow);
            fast = calculateSum(calculateSum(fast));
        }

        return slow == 1;
    }

    private static int calculateSum(int num) {
        int happyNum = 0;

        while (num > 0) {
            int reminder = num % 10;
            happyNum += reminder * reminder;
            num /= 10;
        }

        return happyNum;
    }
}
