package leetdaily.medium;

public class TwoKeys650 {
    int n;
    public static void main(String[] args) {
        TwoKeys650 t = new TwoKeys650();
        System.out.println(t.minSteps(3));
    }

//    backtracking; time: O(2^n), space: O(n)
    public int minSteps(int n) {
        if(n == 1) return 0;
        this.n = n;
//        first step is always a copy all operation
        return 1 + minStepsHelper(1, 1);
    }

    private int minStepsHelper(int currLen, int pasteLen) {
//        base case I: reached n A's, don't need more operations
        if(currLen == n) return 0;
//        base case II: exceeded n A's, not a valid sequence, so return max value
        if(currLen > n) return 1000;
//        copy All + paste
        int opt1 = 2 + minStepsHelper(currLen * 2, currLen);
//        paste
        int opt2 = 1 + minStepsHelper(currLen + pasteLen, pasteLen);

        return Math.min(opt1, opt2);
    }
}

/*
There is only one character 'A' on the screen of a notepad. You can perform one of two operations on this notepad for each step:
Copy All: You can copy all the characters present on the screen (a partial copy is not allowed).
Paste: You can paste the characters which are copied last time.
Given an integer n, return the minimum number of operations to get the character 'A' exactly n times on the screen.

Example 1:
Input: n = 3
Output: 3
Explanation: Initially, we have one character 'A'.
In step 1, we use Copy All operation.
In step 2, we use Paste operation to get 'AA'.
In step 3, we use Paste operation to get 'AAA'.
Example 2:
Input: n = 1
Output: 0

Constraints:
1 <= n <= 1000
 */
