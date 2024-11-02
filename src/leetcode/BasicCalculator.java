package leetcode;

import java.util.Stack;

public class BasicCalculator {
    public static void main(String[] args) {
        System.out.println(calculate1("1 + 1"));
        System.out.println(calculate1(" 2-1 + 2 "));
        System.out.println(calculate1("(1+(4+5+2)-3)+(6+8)"));
    }

    public static int calculate1(String s) {
        int n = s.length();

        Stack<Integer> ops = new Stack<>();
        int currentNumber = 0;
        int result = 0;
        int sign = 1;

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                currentNumber = (currentNumber * 10) + (c - '0');
            } else if (c == '-' || c == '+') {
                result += sign * currentNumber;
                sign = (c == '-') ? -1 : 1;
                currentNumber = 0;
            } else if (c == '(') {
                ops.push(result);
                ops.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * currentNumber;
                result *= ops.pop();
                result += ops.pop();
                currentNumber = 0;
            }
        }

        return result + currentNumber * sign;
    }

    public static int calculate(String s) {
        int n = s.length();

        Stack<Integer> stack = new Stack<>();
        int currentNumber = 0;
        char operation = '+';

        for (int i = 0; i < n; i++) {
            char currentChar = s.charAt(i);

            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }

            if (!Character.isDigit(currentChar) && currentChar != ' ' || i == n - 1) {
                if (currentChar == '(') {
                    int j = i + 1, braces = 1;
                    while (j < n) {
                        if (s.charAt(j) == '(') braces++;
                        else if (s.charAt(j) == ')') braces--;
                        if (braces == 0) break;
                        j++;
                    }
                    currentNumber = calculate(s.substring(i + 1, j));
                    i = j;
                }

                match(operation, stack, currentNumber);
                operation = currentChar;
                currentNumber = 0;
            }
        }

        int result = 0;
        for (int num : stack) {
            result += num;
        }

        return result;
    }

    private static void match(char operation, Stack<Integer> stack, int currentNumber) {
        switch (operation) {
            case '+': stack.push(currentNumber); break;
            case '-': stack.push(-currentNumber); break;
            case '*': stack.push(stack.pop() * currentNumber); break;
            case '/': stack.push(stack.pop() / currentNumber); break;
        }
    }
}
