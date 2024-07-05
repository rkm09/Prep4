package leetdaily.medium;

import common.ListNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindMaxMin2058 {
    public static void main(String[] args) {
        ListNode next6 = new ListNode(2);
        ListNode next5 = new ListNode(1, next6);
        ListNode next4 = new ListNode(5, next5);
        ListNode next3 = new ListNode(2, next4);
        ListNode next2 = new ListNode(1, next3);
        ListNode next1 = new ListNode(3, next2);
        ListNode head = new ListNode(5, next1);
        System.out.println(Arrays.toString(nodesBetweenCriticalPoints(head)));
    }


//    One pass; time: O(n), space: O(1)
    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        int[] result = {-1, -1};
//        initialize node pointers and index
        ListNode prevNode = head;
        ListNode currentNode = head.next;
        int firstCriticalIndex = 0;
        int previousCriticalIndex = 0;
        int currentIndex = 1;

        int minDiff = Integer.MAX_VALUE;
        while(currentNode.next != null) {
//            if a local maxima or minima is found
            if((currentNode.val < currentNode.next.val && currentNode.val < prevNode.val)
                || (currentNode.val > currentNode.next.val && currentNode.val > prevNode.val)) {
//                if this is the first critical point found
                if(firstCriticalIndex == 0) {
                    firstCriticalIndex = currentIndex;
                } else {
                    minDiff = Math.min(minDiff, currentIndex - previousCriticalIndex);
                }
                previousCriticalIndex = currentIndex;
            }
            currentIndex++;
            prevNode = currentNode;
            currentNode = currentNode.next;
        }

//        if at least two critical points were found
        if(minDiff != Integer.MAX_VALUE) {
            int maxDiff = previousCriticalIndex - firstCriticalIndex;
            result = new int[] {minDiff, maxDiff};
        }

        return result;
    }
}

/*
A critical point in a linked list is defined as either a local maxima or a local minima.
A node is a local maxima if the current node has a value strictly greater than the previous node and the next node.
A node is a local minima if the current node has a value strictly smaller than the previous node and the next node.
Note that a node can only be a local maxima/minima if there exists both a previous node and a next node.
Given a linked list head, return an array of length 2 containing [minDistance, maxDistance] where minDistance is the minimum distance between any two distinct critical points and maxDistance is the maximum distance between any two distinct critical points. If there are fewer than two critical points, return [-1, -1].
Example 1:
Input: head = [3,1]
Output: [-1,-1]
Explanation: There are no critical points in [3,1].
Example 2:
Input: head = [5,3,1,2,5,1,2]
Output: [1,3]
Explanation: There are three critical points:
- [5,3,1,2,5,1,2]: The third node is a local minima because 1 is less than 3 and 2.
- [5,3,1,2,5,1,2]: The fifth node is a local maxima because 5 is greater than 2 and 1.
- [5,3,1,2,5,1,2]: The sixth node is a local minima because 1 is less than 5 and 2.
The minimum distance is between the fifth and the sixth node. minDistance = 6 - 5 = 1.
The maximum distance is between the third and the sixth node. maxDistance = 6 - 3 = 3.
Example 3:
Input: head = [1,3,2,2,3,2,2,2,7]
Output: [3,3]
Explanation: There are two critical points:
- [1,3,2,2,3,2,2,2,7]: The second node is a local maxima because 3 is greater than 1 and 2.
- [1,3,2,2,3,2,2,2,7]: The fifth node is a local maxima because 3 is greater than 2 and 2.
Both the minimum and maximum distances are between the second and the fifth node.
Thus, minDistance and maxDistance is 5 - 2 = 3.
Note that the last node is not considered a local maxima because it does not have a next node.

Constraints:

The number of nodes in the list is in the range [2, 105].
1 <= Node.val <= 105
 */