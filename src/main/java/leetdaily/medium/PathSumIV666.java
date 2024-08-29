package leetdaily.medium;

import java.util.HashMap;
import java.util.Map;

public class PathSumIV666 {
    Map<Integer, Integer> map = new HashMap<>();
    public static void main(String[] args) {
        PathSumIV666 p = new PathSumIV666();
        int[] nums = {113,215,221};
        System.out.println(p.pathSum(nums));
    }

//    dfs; time: O(n), space: O(n)
    public int pathSum(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
//        store the data with coordinates as key and node value as value
        for(int num : nums) {
            int key = num / 10;
            int value = num % 10;
            map.put(key, value);
        }
        return dfs(nums[0] / 10, 0);
    }

    private int dfs(int root, int preSum) {
//        find the level and the position coordinates from the root
        int level = root / 10;
        int pos = root % 10;
//        the left child and right child position in the tree
        int left = (level + 1) * 10 + pos * 2 - 1;
        int right = (level + 1) * 10 + pos * 2;
        int currSum = preSum + map.get(root);
//        if the node is a leaf node, return its node to leaf path
        if(!map.containsKey(left) && !map.containsKey(right))
             return currSum;
//        otherwise, iterate through left and right children recursively using depth first search
        int leftSum = map.containsKey(left) ? dfs(left, currSum) : 0;
        int rightSum = map.containsKey(right) ? dfs(right, currSum) : 0;

//        return the total path sum of the tree rooted at the current node
        return leftSum + rightSum;
    }
}

/*
If the depth of a tree is smaller than 5, then this tree can be represented by an array of three-digit integers. For each integer in this array:
The hundreds digit represents the depth d of this node where 1 <= d <= 4.
The tens digit represents the position p of this node in the level it belongs to where 1 <= p <= 8. The position is the same as that in a full binary tree.
The units digit represents the value v of this node where 0 <= v <= 9.
Given an array of ascending three-digit integers nums representing a binary tree with a depth smaller than 5, return the sum of all paths from the root towards the leaves.
It is guaranteed that the given array represents a valid connected binary tree.

Example 1:
Input: nums = [113,215,221]
Output: 12
Explanation: The tree that the list represents is shown.
The path sum is (3 + 5) + (3 + 1) = 12.
Example 2:
Input: nums = [113,221]
Output: 4
Explanation: The tree that the list represents is shown.
The path sum is (3 + 1) = 4.

Constraints:
1 <= nums.length <= 15
110 <= nums[i] <= 489
nums represents a valid binary tree with depth less than 5.
 */