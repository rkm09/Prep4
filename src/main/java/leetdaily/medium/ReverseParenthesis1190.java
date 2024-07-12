package leetdaily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class ReverseParenthesis1190 {
    public static void main(String[] args) {
        String s = "(u(love)i)";
        System.out.println(reverseParentheses(s));
    }

    //    simulation using stack ; time: O(n^2), space: O(n)
    public static String reverseParentheses(String s) {
        Deque<String> stack = new ArrayDeque<>();
        for(char c : s.toCharArray()) {
            if(c != ')')
                stack.push(String.valueOf(c));
            else {
                StringBuilder sb = new StringBuilder();
                while(!stack.peek().equals("(")) {
                    sb.append(stack.pop());
                }
                stack.pop();
                for(int i = 0 ; i < sb.length() ; i++) {
                    stack.push(String.valueOf(sb.charAt(i)));
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty())
            sb.append(stack.pop());

        return reverse(sb.toString());
    }

    private static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        int n = str.length();
        for(int i = 0 ; i < n / 2 ; i++) {
            char temp = str.charAt(i);
            sb.setCharAt(i, str.charAt(n - 1 - i));
            sb.setCharAt(n - 1 - i, temp);
        }
        return sb.toString();
    }
}

/*
You are given a string s that consists of lower case English letters and brackets.
Reverse the strings in each pair of matching parentheses, starting from the innermost one.
Your result should not contain any brackets.
Example 1:
Input: s = "(abcd)"
Output: "dcba"
Example 2:
Input: s = "(u(love)i)"
Output: "iloveu"
Explanation: The substring "love" is reversed first, then the whole string is reversed.
Example 3:
Input: s = "(ed(et(oc))el)"
Output: "leetcode"
Explanation: First, we reverse the substring "oc", then "etco", and finally, the whole string.

Constraints:
1 <= s.length <= 2000
s only contains lower case English characters and parentheses.
It is guaranteed that all parentheses are balanced.
 */
