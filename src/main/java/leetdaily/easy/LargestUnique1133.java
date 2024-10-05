package leetdaily.easy;

import java.util.*;

public class LargestUnique1133 {
    public static void main(String[] args) {
        int[] nums = {5,7,3,9,4,9,8,3,1};
        System.out.println(largestUniqueNumber(nums));
    }

//    def; treemap; time: O(nlogn), space: O(n)
    public static int largestUniqueNumber(int[] nums) {
        TreeMap<Integer, Integer> freq = new TreeMap<>(Collections.reverseOrder());
        for(int num : nums)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        for(int key : freq.keySet()) {
            if(freq.get(key) == 1)
                return key;
        }
        return -1;
    }
}

/*
Given an integer array nums, return the largest integer that only occurs once. If no integer occurs once, return -1.
Example 1:
Input: nums = [5,7,3,9,4,9,8,3,1]
Output: 8
Explanation: The maximum integer in the array is 9 but it is repeated. The number 8 occurs only once, so it is the answer.
Example 2:
Input: nums = [9,9,8,8]
Output: -1
Explanation: There is no number that occurs only once.

Constraints:
1 <= nums.length <= 2000
0 <= nums[i] <= 1000
 */