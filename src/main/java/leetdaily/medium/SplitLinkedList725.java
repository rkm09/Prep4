package leetdaily.medium;

import common.ListNode;

public class SplitLinkedList725 {
    public static void main(String[] args) {
        ListNode next2 = new ListNode(3);
        ListNode next1 = new ListNode(2, next2);
        ListNode head = new ListNode(1, next1);
        ListNode[] res = splitListToParts(head, 5);
    }

//    create new parts; time: O(n), space: O(n)
    public static ListNode[] splitListToParts(ListNode head, int k) {
        ListNode[] res = new ListNode[k];
//        get the size of the linked list
        int size = 0;
        ListNode current = head;
        while(current != null) {
            size++;
            current = current.next;
        }
//        minimum size for the k parts
        int splitSize = size / k;
//        remaining nodes after splitting the k parts evenly
//        these will be distributed to the first (size % k) nodes evenly
        int numRemainingParts = size % k;

        current = head;
        for(int i = 0 ; i < k ; i++) {
//            create the ith part
             ListNode newPart = new ListNode(0);
             ListNode tail = newPart;

             int currentSize = splitSize;
             if(numRemainingParts > 0) {
                numRemainingParts--;
                currentSize++;
             }

             int j = 0;
             while(j++ < currentSize) {
                 tail.next = new ListNode(current.val);
                 tail = tail.next;
                 current = current.next;
             }
             res[i] = newPart.next;
        }

        return res;
    }
}

/*
Given the head of a singly linked list and an integer k, split the linked list into k consecutive linked list parts.
The length of each part should be as equal as possible: no two parts should have a size differing by more than one. This may lead to some parts being null.
The parts should be in the order of occurrence in the input list, and parts occurring earlier should always have a size greater than or equal to parts occurring later.
Return an array of the k parts.
Example 1:
Input: head = [1,2,3], k = 5
Output: [[1],[2],[3],[],[]]
Explanation:
The first element output[0] has output[0].val = 1, output[0].next = null.
The last element output[4] is null, but its string representation as a ListNode is [].
Example 2:
Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
Output: [[1,2,3,4],[5,6,7],[8,9,10]]
Explanation:
The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.

Constraints:
The number of nodes in the list is in the range [0, 1000].
0 <= Node.val <= 1000
1 <= k <= 50
 */