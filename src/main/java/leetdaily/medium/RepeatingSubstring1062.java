package leetdaily.medium;

public class RepeatingSubstring1062 {
    public static void main(String[] args) {
        String s = "abcd";
        System.out.println(longestRepeatingSubstring(s));
    }

//    dp; time: O(n^2), space: O(n^2)
    public static int longestRepeatingSubstring(String s) {
        int n = s.length(), maxLength = 0;
        int[][] dp = new int[n + 1][n + 1];
        for(int i = 1 ; i <= n ; i++) {
            for(int j = i + 1 ; j <= n ; j++) {
//                if the characters match, extend the length of the common substring
                if(s.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength;
    }
}

/*
Given a string s, return the length of the longest repeating substrings. If no repeating substring exists, return 0.
Example 1:
Input: s = "abcd"
Output: 0
Explanation: There is no repeating substring.
Example 2:
Input: s = "abbaba"
Output: 2
Explanation: The longest repeating substrings are "ab" and "ba", each of which occurs twice.
Example 3:
Input: s = "aabcaabdaab"
Output: 3
Explanation: The longest repeating substring is "aab", which occurs 3 times.

Constraints:

1 <= s.length <= 2000
s consists of lowercase English letters.
 */