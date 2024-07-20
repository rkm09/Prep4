package leetdaily.medium;

import common.TreeNode;

public class FindDistance1740 {
    public static void main(String[] args) {
        TreeNode r2 = new TreeNode(4);
        TreeNode r1 = new TreeNode(6);
        TreeNode l1 = new TreeNode(3);
        TreeNode right = new TreeNode(2, r1, r2);
        TreeNode left = new TreeNode(1, l1, null);
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(findDistance(root, 3, 2));
    }

    public static int findDistance(TreeNode root, int p, int q) {
        return 0;
    }
}

/*
Given the root of a binary tree and two integers p and q, return the distance between the nodes of value p and value q in the tree.
The distance between two nodes is the number of edges on the path from one to the other.
Example 1:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 0
Output: 3
Explanation: There are 3 edges between 5 and 0: 5-3-1-0.
Example 2:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 7
Output: 2
Explanation: There are 2 edges between 5 and 7: 5-2-7.
Example 3:
Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 5
Output: 0
Explanation: The distance between a node and itself is 0.

Constraints:
The number of nodes in the tree is in the range [1, 104].
0 <= Node.val <= 109
All Node.val are unique.
p and q are values in the tree.

 */
