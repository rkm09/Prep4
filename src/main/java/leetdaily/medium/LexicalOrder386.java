package leetdaily.medium;

import java.util.ArrayList;
import java.util.List;

public class LexicalOrder386 {
    public static void main(String[] args) {
        System.out.println(lexicalOrder(13));
    }

//    dfs (recursion); time : O(n), space: O(log10n) [~ O(1) based on constraint]   [faster]
    public static List<Integer> lexicalOrder(int n) {
        List<Integer> lexicographicalNumbers = new ArrayList<>();
//        start generating numbers from 1 to 9
        for(int startDigit = 1 ; startDigit <= 9 ; startDigit++)
            generateLexicalNumbers(startDigit, n, lexicographicalNumbers);
        return lexicographicalNumbers;
    }

    private static void generateLexicalNumbers(int currentNumber, int limit, List<Integer> result) {
//        if the current number exceeds limit, stop recursion
        if(currentNumber > limit) return;
//        add the current number to the result
        result.add(currentNumber);
//        try to append digits from 0 to 9 to the current number
        for(int nextDigit = 0 ; nextDigit <= 9 ; nextDigit++) {
            int nextNumber = currentNumber * 10 + nextDigit;
//            if the next number is within limit, continue recursion
            if(nextNumber <= limit)
                generateLexicalNumbers(nextNumber, limit, result);
            else
                break; // no need to continue recursion if the next number exceeds limit
        }
    }

//    iterative approach; time: O(n), space: O(1)
    public static List<Integer> lexicalOrder1(int n) {
        List<Integer> result = new ArrayList<>();
        int currentNumber = 1;
//        generate numbers from 1 to n
        for(int i = 0 ; i < n ; i++) {
            result.add(currentNumber);
//            if multiplying the current number by 10 is within limit, do it
            if(currentNumber * 10 <= n)
                currentNumber *= 10;
            else {
//                adjust the current number by moving up one digit
                while(currentNumber % 10 == 9 || currentNumber >= n)
                    currentNumber /= 10; // remove the last digit
                currentNumber++; // increment the number
            }
        }
        return result;
    }
}

/*
Given an integer n, return all the numbers in the range [1, n] sorted in lexicographical order.
You must write an algorithm that runs in O(n) time and uses O(1) extra space.
Example 1:
Input: n = 13
Output: [1,10,11,12,13,2,3,4,5,6,7,8,9]
Example 2:
Input: n = 2
Output: [1,2]

Constraints:
1 <= n <= 5 * 104

 */