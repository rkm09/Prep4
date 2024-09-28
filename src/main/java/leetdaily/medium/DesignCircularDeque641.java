package leetdaily.medium;

public class DesignCircularDeque641 {
    public static void main(String[] args) {
        MyCircularDeque d = new MyCircularDeque(3);
    }
}
//  linked list; time: O(1), space: O(k) [where k is the size of the list]
class Node {
    Node next;
    Node prev;
    int val;
    Node(int val, Node next, Node prev) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}

class MyCircularDeque {
    Node head;
    Node rear;
    int size;
    int capacity;

    MyCircularDeque(int k) {
        size = 0;
        capacity = k;
    }

    public boolean insertFront(int value) {
        if(isFull()) return false;
        if(head == null) {
//            first element in the list
            head = new Node(value, null, null);
            rear = head;
        } else {
            Node newHead = new Node(value, head, null);
            head.prev = newHead;
            head = newHead;
        }
        size++;
        return true;
    }

    public boolean insertLast(int value) {
        if(isFull()) return false;
        if(head == null) {
//            first element in the list
            head = new Node(value, null, null);
            rear = head;
        } else {
//            add new element to the end
            Node newRear = new Node(value, null, rear);
            rear.next = newRear;
            rear = newRear;
        }
        size++;
        return true;
    }

    public boolean deleteFront() {
        if(isEmpty()) return false;
        if(size == 1) {
            head = null;
            rear = null;
        } else {
            head = head.next;
        }
        size--;
        return true;
    }

    public boolean deleteLast() {
        if(isEmpty()) return false;
        if(size == 1) {
            head = null;
            rear = null;
        } else {
            rear = rear.prev;
        }
        size--;
        return true;
    }

    public int getFront() {
        if(isEmpty()) return -1;
        return head.val;
    }

    public int getRear() {
        if(isEmpty()) return -1;
        return rear.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }
}

/*
Design your implementation of the circular double-ended queue (deque).
Implement the MyCircularDeque class:
MyCircularDeque(int k) Initializes the deque with a maximum size of k.
boolean insertFront() Adds an item at the front of Deque. Returns true if the operation is successful, or false otherwise.
boolean insertLast() Adds an item at the rear of Deque. Returns true if the operation is successful, or false otherwise.
boolean deleteFront() Deletes an item from the front of Deque. Returns true if the operation is successful, or false otherwise.
boolean deleteLast() Deletes an item from the rear of Deque. Returns true if the operation is successful, or false otherwise.
int getFront() Returns the front item from the Deque. Returns -1 if the deque is empty.
int getRear() Returns the last item from Deque. Returns -1 if the deque is empty.
boolean isEmpty() Returns true if the deque is empty, or false otherwise.
boolean isFull() Returns true if the deque is full, or false otherwise.

Example 1:
Input
["MyCircularDeque", "insertLast", "insertLast", "insertFront", "insertFront", "getRear", "isFull", "deleteLast", "insertFront", "getFront"]
[[3], [1], [2], [3], [4], [], [], [], [4], []]
Output
[null, true, true, true, false, 2, true, true, true, 4]
Explanation
MyCircularDeque myCircularDeque = new MyCircularDeque(3);
myCircularDeque.insertLast(1);  // return True
myCircularDeque.insertLast(2);  // return True
myCircularDeque.insertFront(3); // return True
myCircularDeque.insertFront(4); // return False, the queue is full.
myCircularDeque.getRear();      // return 2
myCircularDeque.isFull();       // return True
myCircularDeque.deleteLast();   // return True
myCircularDeque.insertFront(4); // return True
myCircularDeque.getFront();     // return 4

Constraints:
1 <= k <= 1000
0 <= value <= 1000
At most 2000 calls will be made to insertFront, insertLast, deleteFront, deleteLast, getFront, getRear, isEmpty, isFull.
 */
