package leetdaily.medium;

import java.util.Arrays;

public class LongestSubstring1371 {
    public static void main(String[] args) {
        String s = "eleetminicoworep";
        System.out.println(findTheLongestSubstring(s));
    }


//    bitmasking; time: O(n), space: O(1)
//    We need five bits to track the parity of all five vowels (a, e, i, o, u), resulting in 2^5 = 32 possible states.
    public static int findTheLongestSubstring(String s) {
        int prefixXOR = 0;
        int[] characterMap = new int[26];
        characterMap['a' - 'a'] = 1;
        characterMap['e' - 'a'] = 2;
        characterMap['i' - 'a'] = 4;
        characterMap['o' - 'a'] = 8;
        characterMap['u' - 'a'] = 16;

//        'mp' will store the index of the first occurrence of each of the prefix xor combinations
        int[] mp = new int[32];
        Arrays.fill(mp, -1);
//        note: mp[0] will be a positive gain of 1, as we fill the tracking array 'mp' with -1; i - mp[x];

        int longestSubstring = 0;
        for(int i = 0 ; i < s.length() ; i++) {
            prefixXOR ^= characterMap[s.charAt(i) - 'a'];
            if(mp[prefixXOR] == -1 && prefixXOR != 0)
                mp[prefixXOR] = i;
            longestSubstring = Math.max(longestSubstring, i - mp[prefixXOR]);
        }
        return longestSubstring;
    }
}


/*
Given the string s, return the size of the longest substring containing each vowel an even number of times. That is, 'a', 'e', 'i', 'o', and 'u' must appear an even number of times.
Example 1:
Input: s = "eleetminicoworoep"
Output: 13
Explanation: The longest substring is "leetminicowor" which contains two each of the vowels: e, i and o and zero of the vowels: a and u.
Example 2:
Input: s = "leetcodeisgreat"
Output: 5
Explanation: The longest substring is "leetc" which contains two e's.
Example 3:
Input: s = "bcbcbc"
Output: 6
Explanation: In this case, the given string "bcbcbc" is the longest because all vowels: a, e, i, o and u appear zero times.

Constraints:
1 <= s.length <= 5 x 10^5
s contains only lowercase English letters.
 */
