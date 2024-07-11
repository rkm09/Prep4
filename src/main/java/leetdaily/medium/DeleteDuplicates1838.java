package leetdaily.medium;

import common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class DeleteDuplicates1838 {
    public static void main(String[] args) {
        ListNode next3 = new ListNode(2);
        ListNode next2 = new ListNode(3, next3);
        ListNode next1 = new ListNode(2, next2);
        ListNode head = new ListNode(1, next1);
        System.out.println(deleteDuplicatesUnsorted(head).val);
    }

    public static ListNode deleteDuplicatesUnsorted(ListNode head) {
        Set<Integer> seen = new HashSet<>();
        ListNode prev = new ListNode(-1, head);
        ListNode temp = prev;
        ListNode current = prev.next;

        while(current != null) {
            if(!seen.contains(current.val)) {
                seen.add(current.val);
                prev = prev.next;
            }
            else {
                prev.next = current.next;
            }
            current = current.next;
        }
        return temp.next;
    }
}

/*
Given the head of a linked list, find all the values that appear more than once in the list and delete the nodes that have any of those values.
Return the linked list after the deletions.
Example 1:
Input: head = [1,2,3,2]
Output: [1,3]
Explanation: 2 appears twice in the linked list, so all 2's should be deleted. After deleting all 2's, we are left with [1,3].
Example 2:
Input: head = [2,1,1,2]
Output: []
Explanation: 2 and 1 both appear twice. All the elements should be deleted.
Example 3:
Input: head = [3,2,2,1,3,2,4]
Output: [1,4]
Explanation: 3 appears twice and 2 appears three times. After deleting all 3's and 2's, we are left with [1,4].

Constraints:
The number of nodes in the list is in the range [1, 105]
1 <= Node.val <= 105
 */