package leetdaily.medium;

import common.ListNode;

public class MergeNodes2181 {
    public static void main(String[] args) {
        ListNode next5 = new ListNode(0);
        ListNode next4 = new ListNode(1, next5);
        ListNode next3 = new ListNode(0, next4);
        ListNode next2 = new ListNode(3, next3);
        ListNode next1 = new ListNode(2, next2);
        ListNode head = new ListNode(0, next1);
        System.out.println(mergeNodes(head));
    }

//    [def]; time: O(n), space: O(1)
    public static ListNode mergeNodes(ListNode head) {
        ListNode res = new ListNode(0);
        ListNode r = res;
        ListNode temp = head.next;
        while(temp != null) {
            int sum = 0;
            while(temp.val != 0) {
                sum += temp.val;
                temp = temp.next;
            }
            ListNode node = new ListNode(sum);
            res.next = node;
            res = res.next;
            temp = temp.next;
        }
        return r.next;
    }
}

/*
You are given the head of a linked list, which contains a series of integers separated by 0's. The beginning and end of the linked list will have Node.val == 0.
For every two consecutive 0's, merge all the nodes lying in between them into a single node whose value is the sum of all the merged nodes. The modified list should not contain any 0's.
Return the head of the modified linked list.
Input: head = [0,3,1,0,4,5,2,0]
Output: [4,11]
Explanation:
The above figure represents the given linked list. The modified list contains
- The sum of the nodes marked in green: 3 + 1 = 4.
- The sum of the nodes marked in red: 4 + 5 + 2 = 11.
Input: head = [0,1,0,3,0,2,2,0]
Output: [1,3,4]
Explanation:
The above figure represents the given linked list. The modified list contains
- The sum of the nodes marked in green: 1 = 1.
- The sum of the nodes marked in red: 3 = 3.
- The sum of the nodes marked in yellow: 2 + 2 = 4.

Constraints:
The number of nodes in the list is in the range [3, 2 * 105].
0 <= Node.val <= 1000
There are no two consecutive nodes with Node.val == 0.
The beginning and end of the linked list have Node.val == 0.

 */