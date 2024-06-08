package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class SubArrSum523 {
    public static void main(String[] args) {
        SubArrSum523 s = new SubArrSum523();
        int[] nums = {23,2,4,6,7};
        System.out.println(s.checkSubarraySum(nums, 6));
    }

//    prefix sum & hashmap; time: O(n), space: O(n)
//    prefix sum is helpful in calculating the sum of sub arrays;
//    if sum is a multiple of k somewhere then: prefix sum until 'index j mod k' should be equal to prefix sum until 'index i mod k';
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> modSeen = new HashMap<>();
        int prefixMod = 0;
        modSeen.put(0, -1);
        for(int i = 0 ; i < nums.length ; i++) {
            prefixMod = (prefixMod + nums[i]) % k;
            if(modSeen.containsKey(prefixMod)) {
                if (i - modSeen.get(prefixMod) > 1)
                    return true;
            } else {
                modSeen.put(prefixMod, i);
            }
        }
        return false;
    }

}

/*
Given an integer array nums and an integer k, return true if nums has a good subarray or false otherwise.
A good subarray is a subarray where its length is at least two, and
the sum of the elements of the subarray is a multiple of k.
Note that:
A subarray is a contiguous part of the array.
An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
Example 1:
Input: nums = [23,2,4,6,7], k = 6
Output: true
Explanation: [2, 4] is a continuous subarray of size 2 whose elements sum up to 6.
Example 2:
Input: nums = [23,2,6,4,7], k = 6
Output: true
Explanation: [23, 2, 6, 4, 7] is an continuous subarray of size 5 whose elements sum up to 42.
42 is a multiple of 6 because 42 = 7 * 6 and 7 is an integer.
Example 3:
Input: nums = [23,2,6,4,7], k = 13
Output: false

Constraints:
1 <= nums.length <= 105
0 <= nums[i] <= 109
0 <= sum(nums[i]) <= 231 - 1
1 <= k <= 231 - 1
 */
