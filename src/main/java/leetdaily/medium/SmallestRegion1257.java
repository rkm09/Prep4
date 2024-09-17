package leetdaily.medium;

import java.util.*;

public class SmallestRegion1257 {
    public static void main(String[] args) {
        List<String> region1 = Arrays.asList("Earth","North America","South America");
        List<String> region2 = Arrays.asList("North America","United States","Canada");
        List<String> region3 = Arrays.asList("United States","New York","Boston");
        List<String> region4 = Arrays.asList("Canada","Ontario","Quebec");
        List<String> region5 = Arrays.asList("South America","Brazil");
        List<List<String>> regions = Arrays.asList(region1, region2, region3, region4, region5);
        System.out.println(findSmallestRegion(regions, "Quebec", "New York"));
    }

    public static String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
        Map<String, Set<String>> adjMap = new HashMap<>();
        for(List<String> regionList : regions) {

        }
        return "";
    }
}

/*
You are given some lists of regions where the first region of each list includes all other regions in that list.
Naturally, if a region x contains another region y then x is bigger than y. Also, by definition, a region x contains itself.
Given two regions: region1 and region2, return the smallest region that contains both of them.
If you are given regions r1, r2, and r3 such that r1 includes r3, it is guaranteed there is no r2 such that r2 includes r3.
It is guaranteed the smallest region exists.

Example 1:
Input:
regions = [["Earth","North America","South America"],
["North America","United States","Canada"],
["United States","New York","Boston"],
["Canada","Ontario","Quebec"],
["South America","Brazil"]],
region1 = "Quebec",
region2 = "New York"
Output: "North America"
Example 2:
Input: regions = [["Earth", "North America", "South America"],["North America", "United States", "Canada"],["United States", "New York", "Boston"],["Canada", "Ontario", "Quebec"],["South America", "Brazil"]], region1 = "Canada", region2 = "South America"
Output: "Earth"

Constraints:
2 <= regions.length <= 104
2 <= regions[i].length <= 20
1 <= regions[i][j].length, region1.length, region2.length <= 20
region1 != region2
regions[i][j], region1, and region2 consist of English letters.
 */