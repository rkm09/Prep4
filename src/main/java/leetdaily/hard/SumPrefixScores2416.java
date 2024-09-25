package leetdaily.hard;

import java.util.Arrays;

public class SumPrefixScores2416 {
//    initialize the root node of the trie
    TrieNode root = new TrieNode();
    public static void main(String[] args) {
        SumPrefixScores2416 s = new SumPrefixScores2416();
        String[] words = {"abc","ab","bc","b"};
        System.out.println(Arrays.toString(s.sumPrefixScores(words)));
    }

//    Tries; time: O(M.N), space: O(M.N) [M - size of the words array, N - average length of each word]
    public int[] sumPrefixScores(String[] words) {
        int n = words.length;
//        insert words into trie
        for(String word : words)
            insert(word);
        int[] prefixScores = new int[n];
        for(int i = 0 ; i < n ; i++)
            prefixScores[i] = count(words[i]);
        return prefixScores;
    }

//    insert function for the word
    private void insert(String word) {
        TrieNode node = root;
        for(char c : word.toCharArray()) {
//            if a new prefix, create a new trie node
            if(node.next[c - 'a'] == null) {
                node.next[c - 'a'] = new TrieNode();
            }
//            increment the count of the current prefix
            node.next[c - 'a'].cnt++;
            node = node.next[c - 'a'];
        }
    }

//    calculate the prefix count using this function
    private int count(String word) {
        TrieNode node = root;
        int totalCount = 0;
        for(char c : word.toCharArray()) {
            totalCount += node.next[c - 'a'].cnt;
            node = node.next[c - 'a'];
        }
        return totalCount;
    }
}

class TrieNode {
    TrieNode[] next;
    int cnt;
    TrieNode() {
        next = new TrieNode[26];
        cnt = 0;
    }
}

/*
You are given an array words of size n consisting of non-empty strings.
We define the score of a string word as the number of strings words[i] such that word is a prefix of words[i].
For example, if words = ["a", "ab", "abc", "cab"], then the score of "ab" is 2, since "ab" is a prefix of both "ab" and "abc".
Return an array answer of size n where answer[i] is the sum of scores of every non-empty prefix of words[i].
Note that a string is considered as a prefix of itself.

Example 1:
Input: words = ["abc","ab","bc","b"]
Output: [5,4,3,2]
Explanation: The answer for each string is the following:
- "abc" has 3 prefixes: "a", "ab", and "abc".
- There are 2 strings with the prefix "a", 2 strings with the prefix "ab", and 1 string with the prefix "abc".
The total is answer[0] = 2 + 2 + 1 = 5.
- "ab" has 2 prefixes: "a" and "ab".
- There are 2 strings with the prefix "a", and 2 strings with the prefix "ab".
The total is answer[1] = 2 + 2 = 4.
- "bc" has 2 prefixes: "b" and "bc".
- There are 2 strings with the prefix "b", and 1 string with the prefix "bc".
The total is answer[2] = 2 + 1 = 3.
- "b" has 1 prefix: "b".
- There are 2 strings with the prefix "b".
The total is answer[3] = 2.
Example 2:
Input: words = ["abcd"]
Output: [4]
Explanation:
"abcd" has 4 prefixes: "a", "ab", "abc", and "abcd".
Each prefix has a score of one, so the total is answer[0] = 1 + 1 + 1 + 1 = 4.

Constraints:
1 <= words.length <= 1000
1 <= words[i].length <= 1000
words[i] consists of lowercase English letters.
 */