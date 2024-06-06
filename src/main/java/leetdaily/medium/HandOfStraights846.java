package leetdaily.medium;

import java.util.Map;
import java.util.TreeMap;

public class HandOfStraights846 {
    public static void main(String[] args) {
        int[] hand = {1,2,3,6,2,3,4,7,8};
        System.out.println(isNStraightHand(hand, 3));
    }

//    using map; time: O(nlogn), space: O(n)
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        int handLength = hand.length;
        if(handLength % groupSize != 0) return false;

//        TreeMap to store each card value
        Map<Integer, Integer> cardCount = new TreeMap<>();
        for(int i = 0 ; i < handLength ; i++) {
            cardCount.put(hand[i], cardCount.getOrDefault(hand[i], 0) + 1);
        }

        while(!cardCount.isEmpty()) {
//            get the smallest value next card
            int currentCard = cardCount.entrySet().iterator().next().getKey();
//            check each consecutive set of group size cards
            for(int i = 0 ; i < groupSize ; i++) {
//                if a card is missing or its occurrences are exhausted return false;
                if(!cardCount.containsKey(currentCard + i)) return false;
                cardCount.put(currentCard + i, cardCount.get(currentCard + i) - 1);
//                remove the card if its occurrences are exhausted
                if(cardCount.get(currentCard + i) == 0)
                    cardCount.remove(currentCard + i);
            }
        }

        return true;
    }
}


/*
Alice has some number of cards and she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
Given an integer array hand where hand[i] is the value written on the ith card and an integer groupSize, return true if she can rearrange the cards, or false otherwise.
Example 1:
Input: hand = [1,2,3,6,2,3,4,7,8], groupSize = 3
Output: true
Explanation: Alice's hand can be rearranged as [1,2,3],[2,3,4],[6,7,8]
Example 2:
Input: hand = [1,2,3,4,5], groupSize = 4
Output: false
Explanation: Alice's hand can not be rearranged into groups of 4.
Constraints:
1 <= hand.length <= 104
0 <= hand[i] <= 109
1 <= groupSize <= hand.length

Note: This question is the same as 1296: https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/
 */