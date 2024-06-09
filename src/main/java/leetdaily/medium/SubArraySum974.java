package leetdaily.medium;

public class SubArraySum974 {
    public static void main(String[] args) {
        int[] nums = {4,5,0,-2,-3,1};
        System.out.println(subarraysDivByK(nums, 5));
    }

//    prefix sum and counting; time: O(n + m), space: O(m+n)
    public static int subarraysDivByK(int[] nums, int k) {
        int n = nums.length;
        int result = 0, prefixMod = 0;

//      there are k mod groups from 0 to k - 1
        int[] modGroups = new int[k];
        modGroups[0] = 1;

        for(int num : nums) {
//      take mod twice to avoid negative remainders
            prefixMod = (prefixMod + num % k + k) % k;
//            add the count of the sub arrays that have the same count as the current to cancel out the remainders
            result += modGroups[prefixMod];
            modGroups[prefixMod]++;
        }

        return result;
    }
}

/*
Given an integer array nums and an integer k, return the number of non-empty subarrays that have a sum divisible by k.
A subarray is a contiguous part of an array.
Example 1:
Input: nums = [4,5,0,-2,-3,1], k = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by k = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
Example 2:
Input: nums = [5], k = 9
Output: 0

Constraints:
1 <= nums.length <= 3 * 104
-104 <= nums[i] <= 104
2 <= k <= 104
 */