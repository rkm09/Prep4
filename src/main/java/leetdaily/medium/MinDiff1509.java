package leetdaily.medium;

import java.util.*;

public class MinDiff1509 {
    public static void main(String[] args) {
        int[] nums = {1,5,0,10,14};
        System.out.println(minDifference1(nums));
    }

//    greedy & sorting; time: O(nlogn), space: O(logn)
    public static int minDifference(int[] nums) {
        int n = nums.length;
//        if array has four or fewer elements then return 0
        if(n <= 4) return 0;
//        sort the elements and consider the boundaries
        Arrays.sort(nums);
//        four scenarios to compute
        int diff = Integer.MAX_VALUE;
        for(int left = 0, right = n - 4 ; right < n ; left++, right++) {
            diff = Math.min(diff, nums[right] - nums[left]);
        }
        return diff;
    }

//    partial sort and greedy deletion; time: O(n), space: O(1)
    public static int minDifference1(int[] nums) {
        if(nums.length <= 4) return 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int num : nums) {
            maxHeap.offer(num);
            if(maxHeap.size() > 4)
                maxHeap.poll();
        }
        List<Integer> smallestFour = new ArrayList<>(maxHeap);
        Collections.sort(smallestFour);

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for(int num : nums) {
            minHeap.offer(num);
            if(minHeap.size() > 4)
                minHeap.poll();
        }
        List<Integer> largestFour = new ArrayList<>(minHeap);
        Collections.sort(largestFour);

        int minDiff = Integer.MAX_VALUE;
        for(int i = 0 ; i < 4 ; i++){
            minDiff = Math.min(minDiff, largestFour.get(i) - smallestFour.get(i));
        }

        return minDiff;
    }
}

/*
You are given an integer array nums.
In one move, you can choose one element of nums and change it to any value.
Return the minimum difference between the largest and smallest value of nums after performing at most three moves.
Example 1:
Input: nums = [5,3,2,4]
Output: 0
Explanation: We can make at most 3 moves.
In the first move, change 2 to 3. nums becomes [5,3,3,4].
In the second move, change 4 to 3. nums becomes [5,3,3,3].
In the third move, change 5 to 3. nums becomes [3,3,3,3].
After performing 3 moves, the difference between the minimum and maximum is 3 - 3 = 0.
Example 2:
Input: nums = [1,5,0,10,14]
Output: 1
Explanation: We can make at most 3 moves.
In the first move, change 5 to 0. nums becomes [1,0,0,10,14].
In the second move, change 10 to 0. nums becomes [1,0,0,0,14].
In the third move, change 14 to 1. nums becomes [1,0,0,0,1].
After performing 3 moves, the difference between the minimum and maximum is 1 - 0 = 1.
It can be shown that there is no way to make the difference 0 in 3 moves.
Example 3:
Input: nums = [3,100,20]
Output: 0
Explanation: We can make at most 3 moves.
In the first move, change 100 to 7. nums becomes [3,7,20].
In the second move, change 20 to 7. nums becomes [3,7,7].
In the third move, change 3 to 7. nums becomes [7,7,7].
After performing 3 moves, the difference between the minimum and maximum is 7 - 7 = 0.

Constraints:
1 <= nums.length <= 105
-109 <= nums[i] <= 109
 */