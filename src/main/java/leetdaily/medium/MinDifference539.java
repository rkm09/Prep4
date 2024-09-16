package leetdaily.medium;

import java.util.Arrays;
import java.util.List;

public class MinDifference539 {
    public static int BUCKET_SIZE = 24 * 60;
    public static void main(String[] args) {
        List<String> timePoints = Arrays.asList("23:59","00:00");
        System.out.println(findMinDifference(timePoints));
    }

//    sorting; time: O(nlogn), space: O(n)
    public static int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
//        convert the input to minutes
        int[] minutes = new int[n];
        for(int i = 0 ; i < n ; i++) {
            String time = timePoints.get(i);
            int h = Integer.parseInt(time.substring(0,2));
            int m = Integer.parseInt(time.substring(3));
            minutes[i] = h * 60 + m;
        }
//        sort minutes in ascending order
        Arrays.sort(minutes);
//        find the minimum difference between adjacent elements
        int ans = Integer.MAX_VALUE;
        for(int i = 1 ; i < n ; i++)
            ans = Math.min(ans, minutes[i] - minutes[i - 1]);
//        consider the difference between the last and the first element
        return Math.min(ans, 24 * 60 - minutes[n - 1] + minutes[0]);
    }

//    bucket sort; time: O(n), space: O(1)  [faster]
    public static int findMinDifference1(List<String> timePoints) {
//        create a bucket array for the time converted to minutes
        boolean[] minutes = new boolean[BUCKET_SIZE];
        for(String time : timePoints) {
            int h = Integer.parseInt(time.substring(0, 2));
            int m = Integer.parseInt(time.substring(3));
            int min = h * 60 + m;
            if(minutes[min]) return 0;
            minutes[min] = true;
        }
        int ans = Integer.MAX_VALUE;
        int prevIndex = Integer.MAX_VALUE;
        int firstIndex = Integer.MAX_VALUE;
        int lastIndex = Integer.MAX_VALUE;
        for(int i = 0 ; i < BUCKET_SIZE ; i++) {
            if(minutes[i]) {
                if(prevIndex != Integer.MAX_VALUE)
                    ans = Math.min(ans, i - prevIndex);
                prevIndex = i;
                if(firstIndex == Integer.MAX_VALUE)
                    firstIndex = i;
                lastIndex = i;
            }
        }
        return Math.min(ans, 24 * 60 - lastIndex + firstIndex);
    }
    /*
    Bucket sort is typically completed in three steps:
    We initialize an array buckets whose size is equal to the total number of possible values.
    We process the input array so that each element buckets[i] contains the frequency count of the value i in the input array.
    We can finally produce the sorted array by iterating through each element/bucket buckets[i] in buckets and append the value i buckets[i] times to a new array.
    For our purposes, we can use a modified bucket sort for minutes where minutes[i] will contain a boolean value for whether or not the input array has value i.
     */
}

/*
Given a list of 24-hour clock time points in "HH:MM" format, return the minimum minutes difference between any two time-points in the list.
Example 1:
Input: timePoints = ["23:59","00:00"]
Output: 1
Example 2:
Input: timePoints = ["00:00","23:59","00:00"]
Output: 0

Constraints:
2 <= timePoints.length <= 2 * 104
timePoints[i] is in the format "HH:MM".
 */