package leetdaily.medium;

import common.TreeNode;

import java.util.*;

public class ReturnForest1110 {
    public static void main(String[] args) {
        TreeNode r2 = new TreeNode(7);
        TreeNode r1 = new TreeNode(6);
        TreeNode l2 = new TreeNode(5);
        TreeNode l1 = new TreeNode(4);
        TreeNode right = new TreeNode(3, r1, r2);
        TreeNode left = new TreeNode(2, l1, l2);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(delNodes(root, new int[]{3,5}));
    }

//    recursion(postOrder traversal); time: O(n), space: O(n)
    public static List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> toDeleteSet = new HashSet<>();
        for(Integer val : to_delete)
            toDeleteSet.add(val);
        List<TreeNode> forest = new ArrayList<>();
        root = processNode(root, toDeleteSet, forest);
        if(root != null)
            forest.add(root);
        return forest;
    }

    private static TreeNode processNode(TreeNode node, Set<Integer> toDeleteSet, List<TreeNode> forest) {
        if(node == null) return null;
        node.left = processNode(node.left, toDeleteSet, forest);
        node.right = processNode(node.right, toDeleteSet, forest);

        if(toDeleteSet.contains(node.val)) {
            if(node.left != null)
                forest.add(node.left);
            if(node.right != null)
                forest.add(node.right);
            return null;
        }

        return node;
    }

//    bfs forest formation (iteration); time: O(n), space: O(n)
    public static List<TreeNode> delNodes1(TreeNode root, int[] to_delete) {
        if(root == null) return null;
        Set<Integer> toDeleteSet = new HashSet<>();
        for(Integer val : to_delete)
            toDeleteSet.add(val);
        List<TreeNode> forest = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.left != null) {
                queue.offer(node.left);
                if(toDeleteSet.contains(node.left.val))
                    node.left = null;
            }
            if(node.right != null) {
                queue.offer(node.right);
                if(toDeleteSet.contains(node.right.val))
                    node.right = null;
            }
            if(toDeleteSet.contains(node.val)) {
                if(node.left != null)
                    forest.add(node.left);
                if(node.right != null)
                    forest.add(node.right);
            }
        }

        if(!toDeleteSet.contains(root.val))
            forest.add(root);

        return forest;
    }
}

/*
Given the root of a binary tree, each node in the tree has a distinct value.
After deleting all nodes with a value in to_delete, we are left with a forest (a disjoint union of trees).
Return the roots of the trees in the remaining forest. You may return the result in any order.
Example 1:
Input: root = [1,2,3,4,5,6,7], to_delete = [3,5]
Output: [[1,2,null,4],[6],[7]]
Example 2:
Input: root = [1,2,4,null,3], to_delete = [3]
Output: [[1,2,4]]

Constraints:
The number of nodes in the given tree is at most 1000.
Each node has a distinct value between 1 and 1000.
to_delete.length <= 1000
to_delete contains distinct values between 1 and 1000.
 */