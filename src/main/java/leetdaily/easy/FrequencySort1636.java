package leetdaily.easy;

import java.util.*;

public class FrequencySort1636 {
    public static void main(String[] args) {
        int[] nums = {1,1,2,2,2,3};
        System.out.println(Arrays.toString(frequencySort(nums)));
    }

//    customized sorting; time: O(nlogn), space: O(n) [in place sorting]
    public static int[] frequencySort(int[] nums) {
        int n = nums.length;
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        // Java's Arrays.sort method doesn't directly support sorting primitive arrays (int[]) with a lambda comparator.
        // We can convert the primitive int into Integer objects to get around this limitation.
        Integer[] numsObj = new Integer[n];
        for(int i = 0 ; i < n ; i++) {
            numsObj[i] = nums[i];
        }
        Arrays.sort(numsObj, (a,b) -> {
            if(freq.get(a).equals(freq.get(b))) {
                return Integer.compare(b,a);
            }
            return Integer.compare(freq.get(a),freq.get(b));
        });
//        Convert numsObj back to a primitive array to match return type
        for(int i = 0 ; i < n ; i++) {
            nums[i] = numsObj[i];
        }
        return nums;
    }
}

/*
Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If multiple values have the same frequency, sort them in decreasing order.
Return the sorted array.
Example 1:
Input: nums = [1,1,2,2,2,3]
Output: [3,1,1,2,2,2]
Explanation: '3' has a frequency of 1, '1' has a frequency of 2, and '2' has a frequency of 3.
Example 2:
Input: nums = [2,3,1,3,2]
Output: [1,3,3,2,2]
Explanation: '2' and '3' both have a frequency of 2, so they are sorted in decreasing order.
Example 3:
Input: nums = [-1,1,-6,4,5,-6,1,4,1]
Output: [5,-1,4,4,-6,-6,1,1,1]

Constraints:
1 <= nums.length <= 100
-100 <= nums[i] <= 100
 */