package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class CanArrange1497 {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,10,6,7,8,9};
        System.out.println(canArrange(arr, 5));
    }

//    hashmap; time: O(n), space: O(n)
    public static boolean canArrange(int[] arr, int k) {
        Map<Integer, Integer> remainderCount = new HashMap<>();
        for(int a : arr) {
            int rem = ((a % k) + k) % k;
            remainderCount.put(rem, remainderCount.getOrDefault(rem, 0) + 1);
        }
        for(int a : arr) {
            int rem = ((a % k) + k) % k;
            if(rem == 0) {
                if(remainderCount.get(rem) % 2 != 0)
                     return false;
            } else {
                if(!remainderCount.get(rem).equals(remainderCount.get(k - rem)))
                    return false;
            }
        }
        return true;
    }
}

/*
Given an array of integers arr of even length n and an integer k.
We want to divide the array into exactly n / 2 pairs such that the sum of each pair is divisible by k.
Return true If you can find a way to do that or false otherwise.
Example 1:
Input: arr = [1,2,3,4,5,10,6,7,8,9], k = 5
Output: true
Explanation: Pairs are (1,9),(2,8),(3,7),(4,6) and (5,10).
Example 2:
Input: arr = [1,2,3,4,5,6], k = 7
Output: true
Explanation: Pairs are (1,6),(2,5) and(3,4).
Example 3:
Input: arr = [1,2,3,4,5,6], k = 10
Output: false
Explanation: You can try all possible pairs to see that there is no way to divide arr into 3 pairs each with sum divisible by 10.

Constraints:
arr.length == n
1 <= n <= 105
n is even.
-109 <= arr[i] <= 109
1 <= k <= 105
 */