package leetdaily.medium;

import common.ListNode;

public class InsertGCD2807 {
    public static void main(String[] args) {
        InsertGCD2807 i = new InsertGCD2807();
        ListNode next3 = new ListNode(3);
        ListNode next2 = new ListNode(10, next3);
        ListNode next1 = new ListNode(6, next2);
        ListNode head = new ListNode(18, next1);
        System.out.println(i.insertGreatestCommonDivisors(head).val);
    }

//    simulation; time: O(n.log(min(a,b))), space: O(1)
    public ListNode insertGreatestCommonDivisors(ListNode head) {
//        if the list contains only one node, return the head as no insertion is needed
        if(head.next == null) return head;
//        initialize pointers to traverse the list
        ListNode node1 = head;
        ListNode node2 = head.next;
//        traverse the linked list
        while(node2 != null) {
            int gcdValue = gcd(node1.val, node2.val);
            ListNode gcdNode = new ListNode(gcdValue);
//            insert the gcd node between node1 and node2
            node1.next = gcdNode;
            gcdNode.next = node2;
//            move to the next pair of nodes
            node1 = node2;
            node2 = node2.next;
        }
        return head;
    }

//    helper method to calculate gcd using euclidean algorithm
    private int gcd(int a, int b) {
        if(b == 0) return a;
        return gcd(b, a % b);
    }
}

/*
Given the head of a linked list head, in which each node contains an integer value.
Between every pair of adjacent nodes, insert a new node with a value equal to the greatest common divisor of them.
Return the linked list after insertion.
The greatest common divisor of two numbers is the largest positive integer that evenly divides both numbers.

Example 1:
Input: head = [18,6,10,3]
Output: [18,6,6,2,10,1,3]
Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after inserting the new nodes (nodes in blue are the inserted nodes).
- We insert the greatest common divisor of 18 and 6 = 6 between the 1st and the 2nd nodes.
- We insert the greatest common divisor of 6 and 10 = 2 between the 2nd and the 3rd nodes.
- We insert the greatest common divisor of 10 and 3 = 1 between the 3rd and the 4th nodes.
There are no more adjacent nodes, so we return the linked list.
Example 2:
Input: head = [7]
Output: [7]
Explanation: The 1st diagram denotes the initial linked list and the 2nd diagram denotes the linked list after inserting the new nodes.
There are no pairs of adjacent nodes, so we return the initial linked list.

Constraints:
The number of nodes in the list is in the range [1, 5000].
1 <= Node.val <= 1000
 */