/*
 * Given an absolute path for a file (Unix-style), simplify it.
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * click to show corner cases.
 * Corner Cases:
 * Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 */
package Implement;

import java.util.*;

public class SimplifyPath {

    public String simplifyPath(String path) {
        if (path.length() == 0) {
            return path;
        }

        String[] splits = path.split("/");
        Stack<String> stack = new Stack<String>();
        for (String s : splits) {
            if (s.length() == 0 || s.equals(".")) {
                continue;
            } else if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(s);
            }
        }

        if (stack.isEmpty()) {
            stack.push("");
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, "/" + stack.pop());
        }

        return result.toString();
    }

    public static void main(String[] args) {
        SimplifyPath s = new SimplifyPath();
        String t1 = "/home//foo/";
        String t2 = "/a/./b/../../c/";
        String t3 = "/../";
        System.out.println(s.simplifyPath(t1));
        System.out.println(s.simplifyPath(t2));
        System.out.println(s.simplifyPath(t3));
    }
}
