package leetdaily.medium;

import common.TreeNode;

import java.util.*;

public class CountPairs1530 {
    public static void main(String[] args) {
        TreeNode l2 = new TreeNode(4);
        TreeNode right = new TreeNode(3);
        TreeNode left = new TreeNode(2, null, l2);
        TreeNode root = new TreeNode(1, left, right);
        System.out.println(countPairs(root, 3));
    }

//    bfs + graph conversion; time: O(n^2), space: O(n)
    public static int countPairs(TreeNode root, int distance) {
        Map<TreeNode, List<TreeNode>> graph = new HashMap<>();
        Set<TreeNode> leafNodes = new HashSet<>();
        int pairs = 0;
        traverseTree(root, null, leafNodes, graph);

        for(TreeNode leaf : leafNodes) {
            Deque<TreeNode> queue = new ArrayDeque<>();
            Set<TreeNode> seen = new HashSet<>();
            queue.offer(leaf);
            seen.add(leaf);
//            go through all the nodes which are within the given distance of the current node
            for(int i = 0 ; i <= distance ; i++) {
//                clear all the nodes in the queue (distance i away from the leaf node)
//                add the nodes neighbours (distance i + 1 away from the leaf node)
                int size = queue.size();
                for(int j = 0 ; j < size ; j++) {
//                    if current node is a new leaf node add the found pair to the count
                    TreeNode currentNode = queue.poll();
                    if(leafNodes.contains(currentNode) && !leaf.equals(currentNode))
                        pairs++;
                    if(graph.containsKey(currentNode)) {
                        for(TreeNode neighbour : graph.get(currentNode)) {
                            if(!seen.contains(neighbour)) {
                                queue.offer(neighbour);
                                seen.add(neighbour);
                            }
                        }
                    }
                }
            }
        }

        return pairs / 2;
    }

    private static void traverseTree(TreeNode currentNode, TreeNode previousNode,
                                     Set<TreeNode> leafNodes, Map<TreeNode, List<TreeNode>> graph) {
        if(currentNode == null) return;
        if(currentNode.left == null && currentNode.right == null)
            leafNodes.add(currentNode);
        if(previousNode != null) {
            graph.computeIfAbsent(previousNode, a -> new ArrayList<>()).add(currentNode);
            graph.computeIfAbsent(currentNode, a -> new ArrayList<>()).add(previousNode);
        }
        traverseTree(currentNode.left, currentNode, leafNodes, graph);
        traverseTree(currentNode.right, currentNode, leafNodes, graph);
    }

}

/*
You are given the root of a binary tree and an integer distance. A pair of two different leaf nodes of a binary tree is said to be good if the length of the shortest path between them is less than or equal to distance.
Return the number of good leaf node pairs in the tree.
Example 1:
Input: root = [1,2,3,null,4], distance = 3
Output: 1
Explanation: The leaf nodes of the tree are 3 and 4 and the length of the shortest path between them is 3. This is the only good pair.
Example 2:
Input: root = [1,2,3,4,5,6,7], distance = 3
Output: 2
Explanation: The good pairs are [4,5] and [6,7] with shortest path = 2. The pair [4,6] is not good because the length of ther shortest path between them is 4.
Example 3:
Input: root = [7,1,4,6,null,5,3,null,null,null,null,null,2], distance = 3
Output: 1
Explanation: The only good pair is [2,5].

Constraints:
The number of nodes in the tree is in the range [1, 210].
1 <= Node.val <= 100
1 <= distance <= 10

 */