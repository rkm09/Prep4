package leetdaily.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LargestNumber179 {
    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        System.out.println(largestNumber(nums));
    }

//    heap sort; time: O(nlogn), space: O(nlogk) [k is the length of each element]
//    The heap sorts these strings(originally integer) based on which concatenation yields a larger number
    public static String largestNumber(int[] nums) {
        PriorityQueue<String> maxHeap = new PriorityQueue<>((a, b) -> b.concat(a).compareTo(a.concat(b)));
        int totalLength = 0;
        for(int num : nums) {
            String numStr = String.valueOf(num);
            totalLength += numStr.length();
            maxHeap.offer(numStr);
        }
        StringBuilder res = new StringBuilder(totalLength);
        while(!maxHeap.isEmpty())
            res.append(maxHeap.poll());
//        handle edge case where the result might be "00000.."
        return res.charAt(0) == '0' ? "0" : res.toString();
    }

//    using built-in function; time: O(nlogn), space: O(n)
    public static String largestNumber1(int[] nums) {
        String[] numStrArr = new String[nums.length];
        for(int i = 0 ; i < nums.length ; i++)
            numStrArr[i] = String.valueOf(nums[i]);

        Arrays.sort(numStrArr, (a, b) -> b.concat(a).compareTo(a.concat(b)));

        if(numStrArr[0].equals("0")) return "0";
        StringBuilder res = new StringBuilder();
        for(String s : numStrArr)
            res.append(s);
        return res.toString();
    }
}

/*
Given a list of non-negative integers nums, arrange them such that they form the largest number and return it.
Since the result may be very large, so you need to return a string instead of an integer.
Example 1:
Input: nums = [10,2]
Output: "210"
Example 2:
Input: nums = [3,30,34,5,9]
Output: "9534330"

Constraints:
1 <= nums.length <= 100
0 <= nums[i] <= 109
 */