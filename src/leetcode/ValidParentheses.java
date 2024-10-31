package leetcode;

import java.util.Stack;

public class ValidParentheses {
    public static void main(String[] args) {
        String s = "(){}[]";
        System.out.println(isValidParentheses(s));
    }

    private static boolean isValidParentheses(String s) {
        if (s == null) return false;
        int n = s.length();
        if (n % 2 != 0) return false;
        char[] ch = new char[n];
        int open = 0;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '{' || c == '[') {
                ch[open++] = c;
            } else if (open > 0 && c == ')' && ch[open - 1] == '(') {
                open--;
            } else if (open > 0 && c == '}' && ch[open - 1] == '{') {
                open--;
            } else if (open > 0 && c == ']' && ch[open - 1] == '[') {
                open--;
            } else ch[open++] = c;
        }

        return open == 0;
    }

    private static boolean isValidParentheses1(String s) {
        int n = s.length();

        if (n % 2 != 0) return false;

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if      (c == '(') stack.push(')');
            else if (c == '{') stack.push('}');
            else if (c == '[') stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }

        return stack.isEmpty();
    }
}
