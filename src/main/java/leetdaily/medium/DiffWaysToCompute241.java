package leetdaily.medium;

import java.util.ArrayList;
import java.util.List;

public class DiffWaysToCompute241 {
    public static void main(String[] args) {
        String ex = "2-1-1";
        System.out.println(diffWaysToCompute(ex));
    }

//    recursion; time: O(n.2^n), space: O(n.2^n)
    public static List<Integer> diffWaysToCompute(String expression) {
        List<Integer> results = new ArrayList<>();
//        base case: if the string is empty return an empty list
        if(expression.length() == 0) return results;
//        base case: if the string is a single character, treat it as a number and return it
        if(expression.length() == 1) {
            results.add(Integer.parseInt(expression));
            return results;
        }
//        if the expression has only two characters and the first character is a digit, parse it as a number
        if(expression.length() == 2 && Character.isDigit(expression.charAt(0))) {
            results.add(Integer.parseInt(expression));
            return results;
        }
//        recursive case: iterate through each character
        for(int i = 0 ; i < expression.length() ; i++) {
            char currentChar = expression.charAt(i);
//            skip if the current char is a digit
            if(Character.isDigit(currentChar)) continue;
//            split the expression into left and right parts
            List<Integer> leftResults = diffWaysToCompute(expression.substring(0, i));
            List<Integer> rightResults = diffWaysToCompute(expression.substring(i + 1));
//            combine results from left and right parts
            for(int leftValue : leftResults) {
                for(int rightValue : rightResults) {
                    int computedResult = 0;
//                    perform operation based on the current character
                    switch(currentChar) {
                        case '+' : computedResult = leftValue + rightValue; break;
                        case '-' : computedResult = leftValue - rightValue; break;
                        case '*' : computedResult = leftValue * rightValue; break;
                    }
                    results.add(computedResult);
                }
            }
        }

        return results;
    }
}

/*
Given a string expression of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. You may return the answer in any order.
The test cases are generated such that the output values fit in a 32-bit integer and the number of different results does not exceed 104.

Example 1:
Input: expression = "2-1-1"
Output: [0,2]
Explanation:
((2-1)-1) = 0
(2-(1-1)) = 2
Example 2:
Input: expression = "2*3-4*5"
Output: [-34,-14,-10,-10,10]
Explanation:
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10

Constraints:
1 <= expression.length <= 20
expression consists of digits and the operator '+', '-', and '*'.
All the integer values in the input expression are in the range [0, 99].
 */