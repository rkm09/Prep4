package leetdaily.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonCharacters1002 {
    public static void main(String[] args) {
        String[] words = {"bella","label","roller"};
        System.out.println(commonChars(words));
    }

//    array and frequency intersection; time: O(n.k), space: O(1)
    public static List<String> commonChars(String[] words) {
        int[] commonCharCount = new int[26];
        int[] currentCharCount = new int[26];
        int n = words.length;
        List<String> result = new ArrayList<>();

//        initialize common character count
        for(char c : words[0].toCharArray())
            commonCharCount[c - 'a']++;

        for(int i = 1 ; i < n ; i++) {
            Arrays.fill(currentCharCount, 0);

//            count characters in the current word
            for(char c : words[i].toCharArray())
                currentCharCount[c - 'a']++;

//            update the common character count to the minimum of the current and common
            for(int letter = 0 ; letter < 26 ; letter++) {
                commonCharCount[letter] = Math.min(commonCharCount[letter], currentCharCount[letter]);
            }
        }

//        collect the common character count based on final counts
        for(int letter = 0 ; letter < 26 ; letter++) {
            for(int commonCount = 0 ; commonCount < commonCharCount[letter] ; commonCount++) {
                result.add(String.valueOf((char)(letter + 'a')));
            }
        }

        return result;
    }
}

/*
Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.
Example 1:
Input: words = ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:
Input: words = ["cool","lock","cook"]
Output: ["c","o"]

Constraints:
1 <= words.length <= 100
1 <= words[i].length <= 100
words[i] consists of lowercase English letters.
 */