package leetdaily.medium;

import java.util.Arrays;
import java.util.PriorityQueue;

public class LargestNumber179 {
    public static void main(String[] args) {
        int[] nums = {3,30,34,5,9};
        System.out.println(largestNumber2(nums));
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

//    using built-in function; time: O(nlogn), space: O(n + logn)
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

//    merge sort; time: O(nlogn), space: O(n)
//    TODO: is not functional now
    public static String largestNumber2(int[] nums) {
        int[] temp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, temp);
        StringBuilder res = new StringBuilder();
        return res.toString().charAt(0) == '0' ? "0" : res.toString();
    }

    private static void mergeSort(int[] nums, int left, int right, int[] temp) {
        if(left >= right) return;
        int mid = (left + right) / 2;
        mergeSort(nums, left, mid, temp);
        mergeSort(nums, mid + 1, right, temp);
        merge(nums, left, mid, right, temp);
    }

    private static void merge(int[] nums, int left, int mid, int right, int[] temp) {
        int start1 = left;
        int start2 = mid + 1;
        int n1 = mid - left + 1;
        int n2 = right - mid;
//        copy elements of both halves into temp array
        for(int i = 0 ; i < n1 ; i++)
            temp[start1 + i] = nums[start1 + i];
        for(int i = 0 ; i < n2 ; i++)
            temp[start2 + i] = nums[start2 + i];
//        merge the sorted sub arrays
        int i = 0, j = 0, k = left;
        while(i < n1 && j < n2) {
            if(temp[start1 + i] <= temp[start2 + j])
                nums[k++] = temp[start1 + i++];
            else
                nums[k++] = temp[start2 + j++];
        }
//        copy remaining elements if any
        while(i < n1)
            nums[k++] = temp[start1 + i++];
        while(j < n2)
            nums[k++] = temp[start2 + j++];
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