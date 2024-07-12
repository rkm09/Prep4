package leetdaily.medium;

import java.util.ArrayDeque;
import java.util.Deque;

public class MaximumGain1717 {
    public static void main(String[] args) {
        String s = "cdbcbbaaabab";
        System.out.println(maximumGain(s, 4, 5));
    }

//    greedy (stack); time:O(n), space: O(n)
    public static int maximumGain(String s, int x, int y) {
        int totalScore = 0;
        String highPriorityPair = x > y ? "ab" : "ba";
        String lowPriorityPair = highPriorityPair.equals("ab") ? "ba" : "ab" ;

//        First pass: remove high priority pairs
        String stringAfterFirstPass = removeSubstring(s, highPriorityPair);
        int removedPairsCount = (s.length() - stringAfterFirstPass.length()) / 2;
        totalScore += removedPairsCount * Math.max(x, y);

//        Second pass: remove low priority pairs
        String stringAfterSecondPass = removeSubstring(stringAfterFirstPass, lowPriorityPair);
        removedPairsCount = (stringAfterFirstPass.length() - stringAfterSecondPass.length()) / 2;
        totalScore += removedPairsCount * Math.min(x, y);

        return totalScore;
    }

    private static String removeSubstring(String input, String targetPair) {
        Deque<Character> charStack = new ArrayDeque<>();
        for(int i = 0 ; i < input.length() ; i++) {
            char currentChar = input.charAt(i);
            if(currentChar == targetPair.charAt(1)
            && !charStack.isEmpty()
            && charStack.peek() == targetPair.charAt(0)) {
                charStack.pop();
            } else {
                charStack.push(currentChar);
            }
        }

        StringBuilder remainingChars = new StringBuilder();
        while(!charStack.isEmpty())
            remainingChars.append(charStack.pop());

        return remainingChars.reverse().toString();

    }
}

/*
You are given a string s and two integers x and y. You can perform two types of operations any number of times.
Remove substring "ab" and gain x points.
For example, when removing "ab" from "cabxbae" it becomes "cxbae".
Remove substring "ba" and gain y points.
For example, when removing "ba" from "cabxbae" it becomes "cabxe".
Return the maximum points you can gain after applying the above operations on s.
Example 1:
Input: s = "cdbcbbaaabab", x = 4, y = 5
Output: 19
Explanation:
- Remove the "ba" underlined in "cdbcbbaaabab". Now, s = "cdbcbbaaab" and 5 points are added to the score.
- Remove the "ab" underlined in "cdbcbbaaab". Now, s = "cdbcbbaa" and 4 points are added to the score.
- Remove the "ba" underlined in "cdbcbbaa". Now, s = "cdbcba" and 5 points are added to the score.
- Remove the "ba" underlined in "cdbcba". Now, s = "cdbc" and 5 points are added to the score.
Total score = 5 + 4 + 5 + 5 = 19.
Example 2:
Input: s = "aabbaaxybbaabb", x = 5, y = 4
Output: 20

Constraints:
1 <= s.length <= 105
1 <= x, y <= 104
s consists of lowercase English letters.
 */