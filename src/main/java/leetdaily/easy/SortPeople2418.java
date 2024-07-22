package leetdaily.easy;

import java.util.*;

public class SortPeople2418 {
    public static void main(String[] args) {
        String[] names = {"Mary","John","Emma"};
        int[] heights = {180,165,170};
        System.out.println(Arrays.toString(sortPeople(names, heights)));
    }

//    def; hashmap; time: O(nlogn), space: O(n)
    public static String[] sortPeople(String[] names, int[] heights) {
        Map<Integer, String> peopleMap = new HashMap<>();
        int n = names.length;
        String[] people = new String[n];
        for(int i = 0 ; i < n ; i++) {
            peopleMap.put(heights[i], names[i]);
        }
        Arrays.sort(heights);
        for(int i = n - 1 ; i >= 0 ; i--) {
            people[n - i - 1] = peopleMap.get(heights[i]);
        }
        return people;
    }
}

/*
You are given an array of strings names, and an array heights that consists of distinct positive integers. Both arrays are of length n.
For each index i, names[i] and heights[i] denote the name and height of the ith person.
Return names sorted in descending order by the people's heights.
Example 1:
Input: names = ["Mary","John","Emma"], heights = [180,165,170]
Output: ["Mary","Emma","John"]
Explanation: Mary is the tallest, followed by Emma and John.
Example 2:
Input: names = ["Alice","Bob","Bob"], heights = [155,185,150]
Output: ["Bob","Alice","Bob"]
Explanation: The first Bob is the tallest, followed by Alice and the second Bob.

Constraints:
n == names.length == heights.length
1 <= n <= 103
1 <= names[i].length <= 20
1 <= heights[i] <= 105
names[i] consists of lower and upper case English letters.
All the values of heights are distinct.
 */