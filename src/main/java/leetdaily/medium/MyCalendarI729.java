package leetdaily.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class MyCalendarI729 {
    public static void main(String[] args) {
        MyCalendar m = new MyCalendar();
        boolean b = m.book(10,20);
    }
}

//  sorted map and binary search; time: O(nlogn), space: O(n)
//  If we maintained our events in sorted order, we could check whether an event could be booked in O(logN) time (where N is the number of events already booked)
//  by binary searching for where the event should be placed. We would also have to insert the event in our sorted structure.
class MyCalendar {
    TreeMap<Integer, Integer> eventMap;
    MyCalendar() {
        eventMap = new TreeMap<>();
    }
    public boolean book(int start, int end) {
        Integer prev = eventMap.floorKey(start);
        Integer next = eventMap.ceilingKey(start);
        if((prev == null || eventMap.get(prev) <= start) &&
                (next == null || end <= next)) {
            eventMap.put(start, end);
            return true;
        }
        return false;
    }
}

// brute force; time: O(n^2), space: O(n)
class MyCalendar1 {
    List<int[]> events;
    MyCalendar1() {
        events = new ArrayList<>();
    }

//     for no overlap: e1 <= s2 OR e2 <= s1; By Demorgan's law: => s2 < e1 AND s1 < e2
    public boolean book1(int start, int end) {
        for(int[] event : events) {
            if(event[0] < end && start < event[1])
                return false;
        }
        events.add(new int[]{start, end});
        return true;
    }
}

/*
You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a double booking.
A double booking happens when two events have some non-empty intersection (i.e., some moment is common to both events.).
The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
Implement the MyCalendar class:
MyCalendar() Initializes the calendar object.
boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a double booking. Otherwise, return false and do not add the event to the calendar.

Example 1:
Input
["MyCalendar", "book", "book", "book"]
[[], [10, 20], [15, 25], [20, 30]]
Output
[null, true, false, true]
Explanation
MyCalendar myCalendar = new MyCalendar();
myCalendar.book(10, 20); // return True
myCalendar.book(15, 25); // return False, It can not be booked because time 15 is already booked by another event.
myCalendar.book(20, 30); // return True, The event can be booked, as the first event takes every time less than 20, but not including 20.

Constraints:
0 <= start < end <= 109
At most 1000 calls will be made to book.
 */