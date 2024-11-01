package leetcode;

import java.util.Stack;

public class EvalReversePolishNotation {

    public static void main(String[] args) {
        String[] tokens = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};

        System.out.println(evalRPn(tokens));
    }

    private static int evalRPn(String[] tokens) {
        Stack<Integer> nums = new Stack<>();

        for (String token : tokens) {
            match(token, nums);
        }

        return nums.pop();
    }

    private static void match(String token, Stack<Integer> nums) {
        switch (token) {
            case "+": add(nums); break;
            case "-": subtract(nums); break;
            case "*": multiply(nums); break;
            case "/": divide(nums); break;
            default: insert(nums, token); break;
        }
    }

    private static void insert(Stack<Integer> nums, String token) {
        nums.push(Integer.parseInt(token));
    }

    private static void divide(Stack<Integer> nums) {
        int divisor = nums.pop();
        if (divisor == 0) throw new ArithmeticException("Can't divide by zero");
        nums.push(nums.pop() / divisor);
    }

    private static void multiply(Stack<Integer> nums) {
        nums.push(nums.pop() * nums.pop());
    }

    private static void subtract(Stack<Integer> nums) {
        int temp = nums.pop();
        nums.push(nums.pop() - temp);
    }

    private static void add(Stack<Integer> nums) {
        nums.push(nums.pop() + nums.pop());
    }


}
