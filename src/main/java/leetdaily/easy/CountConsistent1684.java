package leetdaily.easy;

import java.util.HashSet;
import java.util.Set;

public class CountConsistent1684 {
    public static void main(String[] args) {
        String[] words = {"ad","bd","aaab","baa","badab"};
        System.out.println(countConsistentStrings("ab", words));
    }

//    hashset; time: O(m + n.k), space: O(m) [m - allowed length, n - words length, k - each word length]
    public static int countConsistentStrings(String allowed, String[] words) {
        Set<Character> lookup = new HashSet<>();
        for(char c : allowed.toCharArray())
            lookup.add(c);
        int count = 0;
        for(String word : words) {
            boolean isConsistent = true;
            for(char c : word.toCharArray()) {
                if(!lookup.contains(c)) {
                    isConsistent = false;
                    break;
                }
            }
            if(isConsistent) count++;
        }
        return count;
    }

//    bitmask; time: O(m + n.k), space: O(1)
    public static int countConsistentStrings1(String allowed, String[] words) {
//        allowedBits will represent the bitmask for allowed characters
        int allowedBits = 0;
//        set the corresponding bit for each character in allowed
        for(char c : allowed.toCharArray())
            allowedBits |= (1 << c - 'a');
        int consistentCount = 0;
//        iterate through each word in the word array
        for(String word : words) {
            boolean isConsistent = true;
            for(char c : word.toCharArray()) {
                int bit = (allowedBits >> c - 'a') & 1;
                if(bit == 0) {
                    isConsistent = false;
                    break;
                }
            }
            if(isConsistent) consistentCount++;
        }
        return consistentCount;
    }

}

/*
You are given a string allowed consisting of distinct characters and an array of strings words. A string is consistent if all characters in the string appear in the string allowed.
Return the number of consistent strings in the array words.
Example 1:
Input: allowed = "ab", words = ["ad","bd","aaab","baa","badab"]
Output: 2
Explanation: Strings "aaab" and "baa" are consistent since they only contain characters 'a' and 'b'.
Example 2:
Input: allowed = "abc", words = ["a","b","c","ab","ac","bc","abc"]
Output: 7
Explanation: All strings are consistent.
Example 3:
Input: allowed = "cad", words = ["cc","acd","b","ba","bac","bad","ac","d"]
Output: 4
Explanation: Strings "cc", "acd", "ac", and "d" are consistent.

Constraints:
1 <= words.length <= 104
1 <= allowed.length <= 26
1 <= words[i].length <= 10
The characters in allowed are distinct.
words[i] and allowed contain only lowercase English letters.

 */