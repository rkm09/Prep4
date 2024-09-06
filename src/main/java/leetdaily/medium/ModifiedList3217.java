package leetdaily.medium;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class ModifiedList3217 {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        ListNode next5 = new ListNode(5);
        ListNode next3 = new ListNode(4, next5);
        ListNode next2 = new ListNode(3, next3);
        ListNode next1 = new ListNode(2, next2);
        ListNode head = new ListNode(1, next1);
        System.out.println(modifiedList(nums, head).val);
    }

//    hashset; time: O(m+n), space: O(n)
    public static ListNode modifiedList(int[] nums, ListNode head) {
//        create a hashset for efficient lookup of the values in nums
        Set<Integer> valuesToRemove = new HashSet<>();
        for(int num : nums)
            valuesToRemove.add(num);
//        handle the case where the head node needs to be removed
        while(head != null && valuesToRemove.contains(head.val))
            head = head.next;
//        if the list is empty after removing the head node, return null
        if(head == null) return null;
//        iterate through the list, removing nodes with values in the set
        ListNode current = head;
        while(current.next != null) {
            if(valuesToRemove.contains(current.next.val)) {
//                skip the next node by updating the next pointer
                current.next = current.next.next;
            } else {
//                move to the next node
                current = current.next;
            }
        }

        return head;
    }
}

/*
You are given an array of integers nums and the head of a linked list. Return the head of the modified linked list after removing all nodes from the linked list that have a value that exists in nums.
Example 1:
Input: nums = [1,2,3], head = [1,2,3,4,5]
Output: [4,5]
Explanation:
Remove the nodes with values 1, 2, and 3.
Example 2:
Input: nums = [1], head = [1,2,1,2,1,2]
Output: [2,2,2]
Explanation:
Remove the nodes with value 1.
Example 3:
Input: nums = [5], head = [1,2,3,4]
Output: [1,2,3,4]
Explanation:
No node has value 5.

Constraints:
1 <= nums.length <= 105
1 <= nums[i] <= 105
All elements in nums are unique.
The number of nodes in the given list is in the range [1, 105].
1 <= Node.val <= 105
The input is generated such that there is at least one node in the linked list that has a value not present in nums.
 */