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

//    LCA + dfs; time: O(n), space: O(n)
    public static int findDistance(TreeNode root, int p, int q) {
//        find the lowest common ancestor of p & q
        TreeNode lca =  findLCA(root, p, q);
        return depth(lca, p) + depth(lca, q);
    }

    private static TreeNode findLCA(TreeNode root, int p, int q) {
        if(root == null || root.val == p || root.val == q)
            return root;
        TreeNode left = findLCA(root.left, p, q);
        TreeNode right = findLCA(root.right, p, q);
        if(left != null && right != null)
            return root;
        return left == null ? right : left;
    }

    private static int depth(TreeNode root, int target) {
        return depth(root, target, 0);
    }

    private static int depth(TreeNode root, int target, int currentDepth) {
//        if node not found
        if(root == null) return -1;
        if(root.val == target) return currentDepth;
//        check left subtree
        int leftDepth = depth(root.left, target, currentDepth + 1);
//        if not in the left subtree, it is guaranteed to be in the right subtree
        return leftDepth != - 1 ? leftDepth : depth(root.right, target, currentDepth + 1);
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
