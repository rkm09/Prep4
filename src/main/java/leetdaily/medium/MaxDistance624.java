package leetdaily.medium;

import java.util.Arrays;
import java.util.List;

public class MaxDistance624 {
    public static void main(String[] args) {
        Integer[][] array = {{1,2,3},{4,5},{1,2,3}};
        List<List<Integer>> arrays = Arrays.stream(array).map(Arrays::asList).toList();
        System.out.println(maxDistance(arrays));
    }

//    Single scan [keep track of min & max and update accordingly]; time: O(n), space: O(1)
    public static int maxDistance(List<List<Integer>> arrays) {
        int size = arrays.size(), res = 0;
        int n = arrays.get(0).size();
        int minVal = arrays.get(0).get(0);
        int maxVal = arrays.get(0).get(n - 1);
        for(int i = 1 ; i < size ; i++) {
            n = arrays.get(i).size();
            res = Math.max(res, Math.max(Math.abs(arrays.get(i).get(0) - maxVal),
                    Math.abs(arrays.get(i).get(n - 1) - minVal)));
            minVal = Math.min(minVal, arrays.get(i).get(0));
            maxVal = Math.max(maxVal, arrays.get(i).get(n - 1));
        }
        return res;
    }

//    brute force [TLE]; time: O(n^2), space: O(1)
    public static int maxDistance1(List<List<Integer>> arrays) {
        int n = arrays.size(), maxDist = 0;
        List<Integer> array1, array2;
        for(int i = 0 ; i < n ; i++) {
            for(int j = i + 1 ; j < n ; j++) {
                array1 = arrays.get(i);
                array2 = arrays.get(j);
                maxDist = Math.max(maxDist, Math.abs(array1.get(0) - array2.get(array2.size() - 1)));
                maxDist = Math.max(maxDist, Math.abs(array1.get(array1.size() - 1) - array2.get(0)));
            }
        }
        return maxDist;
    }
}

/*
You are given m arrays, where each array is sorted in ascending order.
You can pick up two integers from two different arrays (each array picks one) and calculate the distance. We define the distance between two integers a and b to be their absolute difference |a - b|.
Return the maximum distance.
Example 1:
Input: arrays = [[1,2,3],[4,5],[1,2,3]]
Output: 4
Explanation: One way to reach the maximum distance 4 is to pick 1 in the first or third array and pick 5 in the second array.
Example 2:
Input: arrays = [[1],[1]]
Output: 0

Constraints:
m == arrays.length
2 <= m <= 105
1 <= arrays[i].length <= 500
-104 <= arrays[i][j] <= 104
arrays[i] is sorted in ascending order.
There will be at most 105 integers in all the arrays.
 */