package leetdaily.easy;

import java.util.*;

public class KthDistinct2053 {
    public static void main(String[] args) {
        String[] arr = {"d","b","c","b","c","a"};
        System.out.println(kthDistinct(arr, 2));
    }

//    hashmap; time: O(n), space: O(n)
    public static String kthDistinct(String[] arr, int k) {
        Map<String, Integer> freq = new HashMap<>();
        for(String s : arr)
            freq.put(s, freq.getOrDefault(s, 0) + 1);
        for(String s : arr) {
            if(freq.get(s) == 1) k--;
            if(k == 0) return s;
        }
        return "";
    }

//    hashset; time: O(n), space: O(n)
    public static String kthDistinct1(String[] arr, int k) {
        Set<String> duplicateStrings = new HashSet<>();
        Set<String> distinctStrings = new HashSet<>();
        for(String s : arr) {
            if(duplicateStrings.contains(s)) continue;
            if(distinctStrings.contains(s)) {
                duplicateStrings.add(s);
                distinctStrings.remove(s);
            } else
                distinctStrings.add(s);
        }
        for(String s : arr) {
            if(distinctStrings.contains(s)) k--;
            if(k == 0) return s;
        }
        return "";
    }
}

/*
A distinct string is a string that is present only once in an array.
Given an array of strings arr, and an integer k, return the kth distinct string present in arr. If there are fewer than k distinct strings, return an empty string "".
Note that the strings are considered in the order in which they appear in the array.

Example 1:
Input: arr = ["d","b","c","b","c","a"], k = 2
Output: "a"
Explanation:
The only distinct strings in arr are "d" and "a".
"d" appears 1st, so it is the 1st distinct string.
"a" appears 2nd, so it is the 2nd distinct string.
Since k == 2, "a" is returned.
Example 2:
Input: arr = ["aaa","aa","a"], k = 1
Output: "aaa"
Explanation:
All strings in arr are distinct, so the 1st string "aaa" is returned.
Example 3:
Input: arr = ["a","b","a"], k = 3
Output: ""
Explanation:
The only distinct string is "b". Since there are fewer than 3 distinct strings, we return an empty string "".

Constraints:
1 <= k <= arr.length <= 1000
1 <= arr[i].length <= 5
arr[i] consists of lowercase English letters.
 */