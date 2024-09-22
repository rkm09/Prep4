package leetdaily.hard;

public class KthSmallest440 {
    public static void main(String[] args) {
        System.out.println(findKthNumber(13,6));
    }

//    prefix tree; time: O(log(n)^2), space: O(1)
    public static int findKthNumber(int n, int k) {
        int curr = 1;
        k--;
        while(k > 0) {
            int steps = countSteps(n, curr, curr + 1);
//            if the steps are less than or equal to k, we skip this prefix's subtree
            if(steps <= k) {
//                move to the next prefix and decrement k by the number of steps we skip
                curr++;
                k -= steps;
            } else {
//                else, move to the next level and decrement k by 1
                curr *= 10;
                k--;
            }
        }
        return curr;
    }

//    count the number of elements between prefix1 and prefix2 [note: values of prefix can be large, take long]
    private static int countSteps(int n, long prefix1, long prefix2) {
        int steps = 0;
        while(prefix1 <= n) {
            steps += Math.min(n + 1, prefix2) - prefix1;
            prefix1 *= 10;
            prefix2 *= 10;
        }
        return steps;
    }
}

/*
Given two integers n and k, return the kth lexicographically smallest integer in the range [1, n].
Example 1:
Input: n = 13, k = 2
Output: 10
Explanation: The lexicographical order is [1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9], so the second smallest number is 10.
Example 2:
Input: n = 1, k = 1
Output: 1

Constraints:
1 <= k <= n <= 109
 */