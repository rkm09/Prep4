package leetdaily.medium;

import java.util.*;

public class SmallestRegion1257 {
    public static void main(String[] args) {
        SmallestRegion1257 s = new SmallestRegion1257();
        List<String> region1 = Arrays.asList("Earth","North America","South America");
        List<String> region2 = Arrays.asList("North America","United States","Canada");
        List<String> region3 = Arrays.asList("United States","New York","Boston");
        List<String> region4 = Arrays.asList("Canada","Ontario","Quebec");
        List<String> region5 = Arrays.asList("South America","Brazil");
        List<List<String>> regions = Arrays.asList(region1, region2, region3, region4, region5);
        System.out.println(s.findSmallestRegion(regions, "Quebec", "New York"));
    }


//    lowest common ancestor of a generic tree; time: O(m*n), space: O(m*n) [m - number of region arrays, n - number of regions in each array]
    public String findSmallestRegion(List<List<String>> regions, String region1, String region2) {
//        map to store (child -> parent) relationships for each region
        Map<String, String> childParentMap = new HashMap<>();
//        populate the 'childParentMap' using the provided 'regions' list
        for(List<String> region : regions) {
            String parentNode = region.get(0);
            for(int i = 1 ; i < region.size() ; i++) {
                childParentMap.put(region.get(i), parentNode);
            }
        }
//        store paths from the root node to 'region1' and 'region2' in their respective list
        List<String> path1 = fetchPathForRegion(region1, childParentMap);
        List<String> path2 = fetchPathForRegion(region2, childParentMap);
//        traverse both paths simultaneously until the paths diverge
//        the last common node is the lowest common ancestor
        int i = 0, j = 0;
        String lowestCommonAncestor = "";
        while(i < path1.size() && j < path2.size() && path1.get(i).equals(path2.get(j))) {
            lowestCommonAncestor = path1.get(i);
            i++; j++;
        }
//        return the lowest common ancestor of 'region1' and 'region2'
        return lowestCommonAncestor;
    }

//    method that returns the path from the root node to the current node
    private List<String> fetchPathForRegion(String currNode, Map<String, String> childParentMap) {
        List<String> path = new ArrayList<>();
//        start by adding the current node to the list
        path.add(currNode);
//        traverse upward through the tree by finding the parent of the current node; continue until the root node is reached
        while(childParentMap.containsKey(currNode)) {
            String parentNode = childParentMap.get(currNode);
            path.add(parentNode);
            currNode = parentNode;
        }
//        reverse the path so that it starts from the root and ends at the current node
        Collections.reverse(path);
        return path;
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