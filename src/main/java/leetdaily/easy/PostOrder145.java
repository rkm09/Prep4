package leetdaily.easy;

import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PostOrder145 {
    public static void main(String[] args) {
        PostOrder145 p = new PostOrder145();
        TreeNode right1 = new TreeNode(3);
        TreeNode right = new TreeNode(2, right1, null);
        TreeNode root = new TreeNode(1,null,right);
        System.out.println(p.postorderTraversal(root));
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(res, root);
        return res;
    }

//    recursion;def; time: O(n), space: O(n)
    private void postOrder(List<Integer> curr, TreeNode node) {
        if(node == null) return;
        postOrder(curr, node.left);
        postOrder(curr, node.right);
        curr.add(node.val);
    }
}

/*
Given the root of a binary tree, return the postorder traversal of its nodes' values.
Example 1:
Input: root = [1,null,2,3]
Output: [3,2,1]
Example 2:
Input: root = []
Output: []
Example 3:
Input: root = [1]
Output: [1]

Constraints:
The number of the nodes in the tree is in the range [0, 100].
-100 <= Node.val <= 100
 */