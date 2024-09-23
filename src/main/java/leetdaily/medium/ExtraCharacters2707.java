package leetdaily.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ExtraCharacters2707 {
    private Integer[] memo;
    private Set<String> dictionarySet;
    public static void main(String[] args) {
        String s = "leetscode";
        String[] dictionary = {"leet","code","leetcode"};
        ExtraCharacters2707 e = new ExtraCharacters2707();
        System.out.println(e.minExtraChar(s, dictionary));
    }

//    top down dp; time: O(n^3), space: O(N + M.K) [N - total characters int he string, M - average length of each string in dictionary, K - size of dictionary]
    public int minExtraChar(String s, String[] dictionary) {
        int n = s.length();
        memo = new Integer[n + 1];
        dictionarySet = new HashSet<>(Arrays.asList(dictionary));
        return dp(0, n, s);
    }

    private int dp(int start, int n, String s) {
        if(start == n) return 0;
        if(memo[start] != null) return memo[start];
//      to count this character as a leftover character, move to the index 'start + 1'
        int ans = dp(start + 1, n , s) + 1;
        for(int end = start ; end < n ; end++) {
            String curr = s.substring(start, end + 1);
            if(dictionarySet.contains(curr)) {
                ans = Math.min(ans, dp(end + 1, n, s));
            }
        }
        return memo[start] = ans;
    }
}

/*
You are given a 0-indexed string s and a dictionary of words dictionary. You have to break s into one or more non-overlapping substrings such that each substring is present in dictionary. There may be some extra characters in s which are not present in any of the substrings.
Return the minimum number of extra characters left over if you break up s optimally.
Example 1:
Input: s = "leetscode", dictionary = ["leet","code","leetcode"]
Output: 1
Explanation: We can break s in two substrings: "leet" from index 0 to 3 and "code" from index 5 to 8. There is only 1 unused character (at index 4), so we return 1.
Example 2:
Input: s = "sayhelloworld", dictionary = ["hello","world"]
Output: 3
Explanation: We can break s in two substrings: "hello" from index 3 to 7 and "world" from index 8 to 12. The characters at indices 0, 1, 2 are not used in any substring and thus are considered as extra characters. Hence, we return 3.

Constraints:
1 <= s.length <= 50
1 <= dictionary.length <= 50
1 <= dictionary[i].length <= 50
dictionary[i] and s consists of only lowercase English letters
dictionary contains distinct words
 */