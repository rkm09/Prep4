package leetdaily.easy;

import java.util.*;

public class IntersectII350 {
    public static void main(String[] args) {
        int[] nums1 = {1,2,2,1};
        int[] nums2 = {2,2};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }

//    hashmap; time: O(m + n), space: O(min(m,n))
    public static int[] intersect(int[] nums1, int[] nums2) {
        if(nums1.length > nums2.length)
           return intersect(nums2, nums1);
        Map<Integer, Integer> freq = new HashMap<>();
        for(int num : nums1)
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        int k = 0;
        for(int num : nums2) {
            int cnt = freq.getOrDefault(num, 0);
            if(cnt > 0) {
                nums1[k++] = num;
                freq.put(num, cnt - 1);
            }
        }
        return Arrays.copyOfRange(nums1, 0, k);
    }
}

/*
Given two integer arrays nums1 and nums2, return an array of their intersection. Each element in the result must appear as many times as it shows in both arrays and you may return the result in any order.
Example 1:
Input: nums1 = [1,2,2,1], nums2 = [2,2]
Output: [2,2]
Example 2:
Input: nums1 = [4,9,5], nums2 = [9,4,9,8,4]
Output: [4,9]
Explanation: [9,4] is also accepted.
Constraints:
1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000

Follow up:
What if the given array is already sorted? How would you optimize your algorithm?
What if nums1's size is small compared to nums2's size? Which algorithm is better?
What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 */