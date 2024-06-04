package leetdaily.easy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LongestPalindrome409 {
    public static void main(String[] args) {
        String s = "abccccdd";
        System.out.println(longestPalindrome(s));
    }

//     greedy (hashset); time: O(n), space: O(1) [O(56) ~ O(1)]  ; fastest
    public static int longestPalindrome1(String s) {
        Set<Character> charSet = new HashSet<>();
        int res = 0;
        for(char c : s.toCharArray()) {
            if(charSet.contains(c)) {
                charSet.remove(c);
                res += 2;
            }
            else
                charSet.add(c);
        }

        return !charSet.isEmpty() ? res + 1 : res;
    }

//    greedy way(hashmap); time: O(n), space: O(1) [O(56) ~ O(1)]
    public static int longestPalindrome(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        int res = 0;
        boolean hasOddFrequency = false;
        for(int freq : freqMap.values()) {
            if(freq % 2 == 0)
                res += freq;
            else {
                res += freq - 1;
                hasOddFrequency = true;
            }
        }
        if(hasOddFrequency) return res + 1;
        return res;
    }


//    greedy way(optimized hashmap); time: O(n), space: O(1) [O(56) ~ O(1)] [in practise slower]
    public static int longestPalindrome2(String s) {
        int oddFreqCount = 0;
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            if(freqMap.get(c) % 2 == 1)
                oddFreqCount++;
            else
                oddFreqCount--;
        }
        if(oddFreqCount > 0)
            return s.length() - oddFreqCount + 1;
        else
            return s.length();
    }
}

/*
Given a string s which consists of lowercase or uppercase letters, return the length of the longest palindrome that can be built with those letters.
Letters are case sensitive, for example, "Aa" is not considered a palindrome.
Example 1:
Input: s = "abccccdd"
Output: 7
Explanation: One longest palindrome that can be built is "dccaccd", whose length is 7.
Example 2:
Input: s = "a"
Output: 1
Explanation: The longest palindrome that can be built is "a", whose length is 1.

Constraints:
1 <= s.length <= 2000
s consists of lowercase and/or uppercase English letters only.
 */