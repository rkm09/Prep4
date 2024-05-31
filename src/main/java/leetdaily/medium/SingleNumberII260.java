package leetdaily.medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SingleNumberII260 {
    public static void main(String[] args) {
        int[] nums = {1,2,1,3,2,5};
        System.out.println(Arrays.toString(singleNumber(nums)));
    }

//    hashmap; time: O(n), space: O(n) [not valid based on constraints]
    public static int[] singleNumber(int[] nums) {
        int n = nums.length, index = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int i = 0 ; i < n ; i++) {
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }
        int[] res = new int[2];
        for(int key : freq.keySet()) {
            if(freq.get(key) == 1) {
                res[index++] = key;
                if(index == 2) break;
            }
        }

        return res;
    }
}

/*
Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
Example 1:
Input: nums = [1,2,1,3,2,5]
Output: [3,5]
Explanation:  [5, 3] is also a valid answer.
Example 2:
Input: nums = [-1,0]
Output: [-1,0]
Example 3:
Input: nums = [0,1]
Output: [1,0]


Constraints:
2 <= nums.length <= 3 * 104
-231 <= nums[i] <= 231 - 1
Each integer in nums will appear twice, only two integers will appear once.
 */