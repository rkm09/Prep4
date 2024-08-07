package leetdaily.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;


public class MinPushes3016 {
    public static void main(String[] args) {
        String word = "aabbccddeeffgghhiiiiii";
        System.out.println(minimumPushes(word));
    }

//    hashmap + pq ; time: O(n), space: O(1)
    public static int minimumPushes(String word) {
        Map<Character, Integer> freq = new HashMap<>();
//        count frequencies
        for(char c : word.toCharArray())
            freq.put(c, freq.getOrDefault(c, 0) + 1);
//        priority queue to store frequencies in descending order
        PriorityQueue<Integer> frequencyQueue = new PriorityQueue<>((a,b) -> b - a);
        frequencyQueue.addAll(freq.values());

        int totalPushes = 0, index = 0;
//        calculate total number of presses
        while(!frequencyQueue.isEmpty()) {
            totalPushes += (index++ / 8 + 1) * frequencyQueue.poll();
        }

        return totalPushes;
    }

//    greedy sorting; time: O(n), space: O(1); faster
    public static int minimumPushes1(String word) {
        int[] freq = new int[26];
        for(char c : word.toCharArray())
            freq[c - 'a']++;

        //Integer[] sortedFreq = Arrays.stream(freq).boxed().toArray(Integer[]::new); // slower with this approach
        //Arrays.sort(sortedFreq, (a,b) -> b - a);

        Arrays.sort(freq);
        int[] sortedFreq = new int[26];
//        descending order
        for(int i = 0 ; i < 26 ; i++)
            sortedFreq[i] = freq[25 - i];

        int totalPushes = 0;
        for(int i = 0 ; i < 26 ; i++) {
            if(sortedFreq[i] == 0) break;
            totalPushes += (i / 8 + 1) * sortedFreq[i];
        }
        return totalPushes;
    }
}

/*
You are given a string word containing lowercase English letters.
Telephone keypads have keys mapped with distinct collections of lowercase English letters, which can be used to form words by pushing them. For example, the key 2 is mapped with ["a","b","c"], we need to push the key one time to type "a", two times to type "b", and three times to type "c" .
It is allowed to remap the keys numbered 2 to 9 to distinct collections of letters. The keys can be remapped to any amount of letters, but each letter must be mapped to exactly one key. You need to find the minimum number of times the keys will be pushed to type the string word.
Return the minimum number of pushes needed to type word after remapping the keys.
An example mapping of letters to keys on a telephone keypad is given below. Note that 1, *, #, and 0 do not map to any letters.

Example 1:
Input: word = "abcde"
Output: 5
Explanation: The remapped keypad given in the image provides the minimum cost.
"a" -> one push on key 2
"b" -> one push on key 3
"c" -> one push on key 4
"d" -> one push on key 5
"e" -> one push on key 6
Total cost is 1 + 1 + 1 + 1 + 1 = 5.
It can be shown that no other mapping can provide a lower cost.
Example 2:
Input: word = "xyzxyzxyzxyz"
Output: 12
Explanation: The remapped keypad given in the image provides the minimum cost.
"x" -> one push on key 2
"y" -> one push on key 3
"z" -> one push on key 4
Total cost is 1 * 4 + 1 * 4 + 1 * 4 = 12
It can be shown that no other mapping can provide a lower cost.
Note that the key 9 is not mapped to any letter: it is not necessary to map letters to every key, but to map all the letters.
Example 3:

Input: word = "aabbccddeeffgghhiiiiii"
Output: 24
Explanation: The remapped keypad given in the image provides the minimum cost.
"a" -> one push on key 2
"b" -> one push on key 3
"c" -> one push on key 4
"d" -> one push on key 5
"e" -> one push on key 6
"f" -> one push on key 7
"g" -> one push on key 8
"h" -> two pushes on key 9
"i" -> one push on key 9
Total cost is 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 1 * 2 + 2 * 2 + 6 * 1 = 24.
It can be shown that no other mapping can provide a lower cost.

Constraints:
1 <= word.length <= 105
word consists of lowercase English letters.
 */
