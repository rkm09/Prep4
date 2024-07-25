package leetdaily.medium;

import java.util.Arrays;

public class SortArray912 {
    public static void main(String[] args) {
        int[] nums = {5,2,3,1};
        System.out.println(Arrays.toString(sortArray(nums)));
    }

//    merge sort; time: O(nlogn), space: O(n)
    public static int[] sortArray(int[] nums) {
        int[] tempArr = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1, tempArr);
        return nums;
    }

    private static void mergeSort(int[] nums, int left, int right, int[] tempArr) {
        if(left >= right) return;
        int mid = (left + right) / 2;
//        sort the first and the second halves recursively
        mergeSort(nums, left, mid, tempArr);
        mergeSort(nums, mid + 1, right, tempArr);
//        merge the sorted halves
        merge(nums, left, mid, right, tempArr);
    }

    private static void merge(int[] arr, int left, int mid, int right, int[] tempArr) {
//        calculate the start and the size of each of the halves
        int start1 = left;
        int start2 = mid + 1;
        int n1 = mid - left + 1;
        int n2 = right - mid;
//        copy elements of both halves into a temporary array
        for(int i = 0 ; i < n1 ; i++)
            tempArr[start1 + i] = arr[start1 + i];
        for(int i = 0 ; i < n2 ; i++)
            tempArr[start2 + i] = arr[start2 + i];
//       merge the sorted sub arrays in temporary array in sorted order
        int k = left, i = 0, j = 0;
        while(i < n1 && j < n2) {
            if(tempArr[start1 + i] <= tempArr[start2 + j]) {
                arr[k++] = tempArr[start1 + i++];
            } else {
                arr[k++] = tempArr[start2 + j++];
            }
        }
//        copy remaining elements
        while(i < n1)
            arr[k++] = tempArr[start1 + i++];
        while(j < n2)
            arr[k++] = tempArr[start2 + j++];
    }
}

/*
Given an array of integers nums, sort the array in ascending order and return it.
You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
Example 1:
Input: nums = [5,2,3,1]
Output: [1,2,3,5]
Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
Example 2:
Input: nums = [5,1,1,2,0,0]
Output: [0,0,1,1,2,5]
Explanation: Note that the values of nums are not necessarily unique.


Constraints:
1 <= nums.length <= 5 * 104
-5 * 104 <= nums[i] <= 5 * 104
 */