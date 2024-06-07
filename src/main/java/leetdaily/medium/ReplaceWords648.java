package leetdaily.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReplaceWords648 {
    public static void main(String[] args) {
        List<String> dictionary = List.of("a","b","c");
        String sentence = "aadsfasf absbs bbab cadsfafs";
        System.out.println(replaceWords(dictionary, sentence));
    }


//    hashset; time: O(d.w + s.w^2), space: O(d.w + s.w) [d - num of words in dictionary, s - num of words in sentence, w - average word length]
    public static String replaceWords(List<String> dictionary, String sentence) {
        String[] wordArray = sentence.split(" ");
        Set<String> dictSet = new HashSet<>(dictionary);
        for(int i = 0 ; i < wordArray.length ; i++) {
            wordArray[i] = shortestRoot(wordArray[i], dictSet);
        }
        return String.join(" ", wordArray);
    }

    private static String shortestRoot(String word, Set<String> dictSet) {
        for(int i = 1 ; i < word.length() ; i++) {
            String root = word.substring(0, i);
            if(dictSet.contains(root))
                return root;
        }
//        no match found
        return word;
    }
}

/*
In English, we have a concept called root, which can be followed by some other word to form another longer word - let's call this word derivative. For example, when the root 'help' is followed by the word 'ful', we can form a derivative 'helpful'.
Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces, replace all the derivatives in the sentence with the root forming it. If a derivative can be replaced by more than one root, replace it with the root that has the shortest length.
Return the sentence after the replacement.
Example 1:
Input: dictionary = ['cat','bat','rat'], sentence = 'the cattle was rattled by the battery'
Output: 'the cat was rat by the bat'
Example 2:
Input: dictionary = ['a','b','c'], sentence = 'aadsfasf absbs bbab cadsfafs'
Output: 'a a b c'
 

Constraints:
1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case letters.
1 <= sentence.length <= 106
sentence consists of only lower-case letters and spaces.
The number of words in sentence is in the range [1, 1000]
The length of each word in sentence is in the range [1, 1000]
Every two consecutive words in sentence will be separated by exactly one space.
sentence does not have leading or trailing spaces.
 */