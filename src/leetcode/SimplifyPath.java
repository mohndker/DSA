package leetcode;

import java.util.Stack;

public class SimplifyPath {
    public static void main(String[] args) {
        String path = "/home/foo//../src/.";
        System.out.println(simplifyPath(path));
    }

    public static String simplifyPath(String path) {
        int n = path.length();
        if (n <= 1) return path;

        Stack<String> stack = new Stack<>();
        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < n; i++) {
            if (path.charAt(i) == '/') continue;
            int j = i;
            while (i < n && path.charAt(i) != '/') i++;
            String temp = path.substring(j, i);
            if (temp.equals(".")) continue;
            else if (temp.equals("..")) {
                if (!stack.empty())
                    stack.pop();
            } else stack.push(temp);
        }

        while (!stack.isEmpty())
            ans.insert(0,"/" + stack.pop());

        if (ans.isEmpty()) return "/";

        return ans.toString();
    }
}
