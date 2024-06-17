package leetdaily.medium;

public class SumOfSquares633 {
    public static void main(String[] args) {
        System.out.println(judgeSquareSum3(5));
    }

//    sqrt function; time: O(sqrt(c).logc), space: O(1)
    public static boolean judgeSquareSum3(int c) {
        for(long a = 0 ; a * a <= c ; a++) {
            double b = Math.sqrt(c - a * a);
            if(b == (int)b)
                return true;
        }
        return false;
    }

//    better brute force (TLE); time: O(c), space: O(1)
    public static boolean judgeSquareSum4(int c) {
        for(long a = 0 ; a * a <= c ; a++) {
            int b = c - (int)(a * a);
            int sum = 0, i = 1;
            while(sum < b) {
                sum += i;
                i += 2;
            }
            if(sum == b)
                return true;
        }
        return false;
    }

//    brute force(TLE); time: O(c), space: O(1)
    public static boolean judgeSquareSum5(int c) {
        for(long a = 0 ; a * a <= c ; a++) {
            for(long b = 0 ; b * b <= c ; b++) {
                if(a * a + b * b == c)
                    return true;
            }
        }
        return false;
    }
}

/*
Given a non-negative integer c, decide whether there're two integers a and b such that a2 + b2 = c.
Example 1:
Input: c = 5
Output: true
Explanation: 1 * 1 + 2 * 2 = 5
Example 2:
Input: c = 3
Output: false

Constraints:
0 <= c <= 231 - 1
 */