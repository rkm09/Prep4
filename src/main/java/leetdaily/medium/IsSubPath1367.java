package leetdaily.medium;

import common.ListNode;
import common.TreeNode;

public class IsSubPath1367 {
    public static void main(String[] args) {
        IsSubPath1367 s = new IsSubPath1367();
        ListNode next2 = new ListNode(8);
        ListNode next1 = new ListNode(2, next2);
        ListNode head = new ListNode(4, next1);
        TreeNode root = new TreeNode(1);
        System.out.println(s.isSubPath(head, root));
    }

//    dfs; time: O(m * n), space: O(m + n)
    public boolean isSubPath(ListNode head, TreeNode root) {
       if(root ==  null) return false;
       return checkPath(root, head);
    }

    private boolean checkPath(TreeNode node, ListNode head) {
        if(node == null) return false;
//        if a matching path is found
        if(dfs(node, head)) return true;
//        recursively check left and right subtrees
        return checkPath(node.left, head) || checkPath(node.right, head);
    }

    private boolean dfs(TreeNode node, ListNode head) {
        if(head == null) return true;  // all nodes in the list matched
        if(node == null) return false; // reached end of tree without matching all nodes
        if(node.val != head.val) return false; // value mismatch
        return dfs(node.left, head.next) || dfs(node.right, head.next);
    }
}

/*
Given a binary tree root and a linked list with head as the first node.
Return True if all the elements in the linked list starting from the head correspond to some downward path connected in the binary tree otherwise return False.
In this context downward path means a path that starts at some node and goes downwards.

Example 1:
Input: head = [4,2,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true
Explanation: Nodes in blue form a subpath in the binary Tree.
Example 2:
Input: head = [1,4,2,6], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: true
Example 3:
Input: head = [1,4,2,6,8], root = [1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]
Output: false
Explanation: There is no path in the binary tree that contains all the elements of the linked list from head.

Constraints:
The number of nodes in the tree will be in the range [1, 2500].
The number of nodes in the list will be in the range [1, 100].
1 <= Node.val <= 100 for each node in the linked list and binary tree.
 */