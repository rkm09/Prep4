package leetdaily.medium;

import common.Pair;
import common.TreeNode;

import java.util.*;

public class GetDirections2096 {
    public static void main(String[] args) {
        TreeNode r2 = new TreeNode(4);
        TreeNode r1 = new TreeNode(6);
        TreeNode l1 = new TreeNode(3);
        TreeNode right = new TreeNode(2, r1, r2);
        TreeNode left = new TreeNode(1, l1, null);
        TreeNode root = new TreeNode(5, left, right);
        System.out.println(getDirections(root, 3, 6));
    }

//    bfs + dfs; time: O(n), space: O(n)
    public static String getDirections(TreeNode root, int startValue, int destValue) {
//      map to store parent nodes
        Map<Integer, TreeNode> parentMap = new HashMap<>();
//        find the start node and populate parent map
        TreeNode startNode = findStartNode(root, startValue);
        populateParentMap(root, parentMap);
//        perform bfs to find the shortest path
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(startNode);
        Set<TreeNode> visitedNodes = new HashSet<>();
        visitedNodes.add(startNode);
//      key: nextNode, value: <currentNode, direction>
        Map<TreeNode, Pair<TreeNode, String>> pathTracker = new HashMap<>();

        while(!queue.isEmpty()) {
            TreeNode currentNode = queue.poll();
//            if destination is reached, return the path
            if(currentNode.val == destValue)
                return backtrackPath(currentNode, pathTracker);
//            check and add the parent node
            if(parentMap.containsKey(currentNode.val)) {
                TreeNode parentNode = parentMap.get(currentNode.val);
                if(!visitedNodes.contains(parentNode)) {
                    queue.offer(parentNode);
                    pathTracker.put(parentNode, new Pair<>(currentNode, "U"));
                    visitedNodes.add(parentNode);
                }
            }
//            check and add the left child
            if(currentNode.left != null
            && !visitedNodes.contains(currentNode.left)) {
                queue.offer(currentNode.left);
                pathTracker.put(currentNode.left, new Pair<>(currentNode, "L"));
                visitedNodes.add(currentNode.left);
            }
//            check and add the right child
            if(currentNode.right != null
            && !visitedNodes.contains(currentNode.right)) {
                queue.offer(currentNode.right);
                pathTracker.put(currentNode.right, new Pair<>(currentNode, "R"));
                visitedNodes.add(currentNode.right);
            }
        }

//        this line should never be reached if the tree is valid
        return "";
    }

    private static String backtrackPath(TreeNode node, Map<TreeNode, Pair<TreeNode, String>> pathTracker) {
        StringBuilder path = new StringBuilder();
        while(pathTracker.containsKey(node)) {
            path.append(pathTracker.get(node).getValue());
            node = pathTracker.get(node).getKey();
        }
        path.reverse();
        return path.toString();
    }

    private static void populateParentMap(TreeNode node, Map<Integer, TreeNode> parentMap) {
        if(node == null) return;
        if(node.left != null) {
            parentMap.put(node.left.val, node);
            populateParentMap(node.left, parentMap);
        }
        if(node.right != null) {
            parentMap.put(node.right.val, node);
            populateParentMap(node.right, parentMap);
        }
    }

    private static TreeNode findStartNode(TreeNode node, int startValue) {
        if(node == null) return null;
        if(node.val == startValue) return node;
        TreeNode leftResult = findStartNode(node.left, startValue);
        if(leftResult != null) return leftResult;
        return findStartNode(node.right, startValue);
    }
}

/*
You are given the root of a binary tree with n nodes. Each node is uniquely assigned a value from 1 to n. You are also given an integer startValue representing the value of the start node s, and a different integer destValue representing the value of the destination node t.
Find the shortest path starting from node s and ending at node t. Generate step-by-step directions of such path as a string consisting of only the uppercase letters 'L', 'R', and 'U'. Each letter indicates a specific direction:
'L' means to go from a node to its left child node.
'R' means to go from a node to its right child node.
'U' means to go from a node to its parent node.
Return the step-by-step directions of the shortest path from node s to node t.
Example 1:
Input: root = [5,1,2,3,null,6,4], startValue = 3, destValue = 6
Output: "UURL"
Explanation: The shortest path is: 3 → 1 → 5 → 2 → 6.
Example 2:
Input: root = [2,1], startValue = 2, destValue = 1
Output: "L"
Explanation: The shortest path is: 2 → 1.

Constraints:
The number of nodes in the tree is n.
2 <= n <= 105
1 <= Node.val <= n
All the values in the tree are unique.
1 <= startValue, destValue <= n
startValue != destValue
 */