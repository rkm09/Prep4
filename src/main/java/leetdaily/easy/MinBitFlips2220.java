package leetdaily.easy;

public class MinBitFlips2220 {
    public static void main(String[] args) {
        System.out.println(minBitFlips(10, 7));
    }

//    XOR Rules; time: O(numberOfBits), space: O(1)
//    The general rule of the XOR operation is that XOR between two bits returns 1 if the bits differ and 0 if they are the same.
//    This simplifies the entire process because we shift from comparing each bit individually to a single operation that captures all differences.
    public static int minBitFlips(int start, int goal) {
//        XOR to find differing bits
        int xorResult = start ^ goal;
        int count = 0;
//        count the number of 1s in the xor result (differing bits)
        while(xorResult != 0) {
            count += xorResult & 1; // increment if the last bit is set to 1
            xorResult >>= 1; // shift right to process the next bit
        }
        return count;
    }

    //    bk algo; time: O(numberOfBits), space: O(1)
    public static int minBitFlips1(int start, int goal) {
//        XOR to find differing bits
        int xorResult = start ^ goal;
        int count = 0;
//        bk algo to count 1s
        while(xorResult != 0) {
            xorResult &= (xorResult - 1); // clear the lowest set bit
            count++;
        }
        return count;
    }

//    brute force; time: O(maxbits) [max of 30 bits with the current constraint ~ O(1)], space: O(1)
    public static int minBitFlips2(int start, int goal) {
        int count = 0;
        while((start > 0 || goal > 0)) {
//            increment count if the current bits differ (isolate the lsb using &)
            if((start & 1) != (goal & 1))
                count++;
//            shift both numbers to the right to check the next set of bits
            start >>= 1;
            goal >>= 1;
        }
        return count;
    }

//    recursion; time: O(maxbits) [max of 30 bits with the current constraint ~ O(1)], space: O(1)
    public static int minBitFlips3(int start, int goal) {
//        base case: both numbers have been fully processed
        if(start == 0 && goal == 0) return 0;
//        flip the current least significant bit
        int flip = ((start & 1) != (goal & 1)) ? 1 : 0;
        return flip + minBitFlips3(start >> 1, goal >> 1);
    }


}

/*
A bit flip of a number x is choosing a bit in the binary representation of x and flipping it from either 0 to 1 or 1 to 0.
For example, for x = 7, the binary representation is 111 and we may choose any bit (including any leading zeros not shown) and flip it. We can flip the first bit from the right to get 110, flip the second bit from the right to get 101, flip the fifth bit from the right (a leading zero) to get 10111, etc.
Given two integers start and goal, return the minimum number of bit flips to convert start to goal.
Example 1:
Input: start = 10, goal = 7
Output: 3
Explanation: The binary representation of 10 and 7 are 1010 and 0111 respectively. We can convert 10 to 7 in 3 steps:
- Flip the first bit from the right: 1010 -> 1011.
- Flip the third bit from the right: 1011 -> 1111.
- Flip the fourth bit from the right: 1111 -> 0111.
It can be shown we cannot convert 10 to 7 in less than 3 steps. Hence, we return 3.
Example 2:
Input: start = 3, goal = 4
Output: 3
Explanation: The binary representation of 3 and 4 are 011 and 100 respectively. We can convert 3 to 4 in 3 steps:
- Flip the first bit from the right: 011 -> 010.
- Flip the second bit from the right: 010 -> 000.
- Flip the third bit from the right: 000 -> 100.
It can be shown we cannot convert 3 to 4 in less than 3 steps. Hence, we return 3.

Constraints:
0 <= start, goal <= 109
 */