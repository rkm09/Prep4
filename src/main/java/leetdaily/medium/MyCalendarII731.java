package leetdaily.medium;

import java.util.ArrayList;
import java.util.List;

public class MyCalendarII731 {
    public static void main(String[] args) {
        CalendarII c = new CalendarII();
        c.book(10,20);
    }
}

// using overlapped intervals, time: O(n), space: O(n)
class CalendarII {
    private List<int[]> bookings;
    private List<int[]> overlapBookings;
    CalendarII() {
        bookings = new ArrayList<>();
        overlapBookings = new ArrayList<>();
    }
    boolean book(int start, int end) {
//      return false if the new booking has an overlap with the existing double-booked bookings
        for(int[] booking : overlapBookings) {
            if(doesOverlap(booking[0], booking[1], start, end))
                return false;
        }
//        add the double overlap bookings (if any) with the existing bookings
        for(int[] booking : bookings) {
            if(doesOverlap(booking[0], booking[1], start, end)) {
                overlapBookings.add(getOverlapped(booking[0], booking[1], start, end));
            }
        }
//        add the new booking to the list of bookings
        bookings.add(new int[] {start, end});
        return true;
    }

//    return true if the intervals [start1, end1) and [start2, end2) overlap
    private boolean doesOverlap(int start1, int end1, int start2, int end2) {
        return Math.max(start1, start2) < Math.min(end1, end2);
    }

//    return overlapping bookings between [start1, end1) and [start2, end2)
    private int[] getOverlapped(int start1, int end1, int start2, int end2) {
        return new int[] {Math.max(start1, start2), Math.min(end1, end2)};
    }
}

/*
You are implementing a program to use as your calendar. We can add a new event if adding the event will not cause a triple booking.
A triple booking happens when three events have some non-empty intersection (i.e., some moment is common to all the three events.).
The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end), the range of real numbers x such that start <= x < end.
Implement the MyCalendarTwo class:
MyCalendarTwo() Initializes the calendar object.
boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking. Otherwise, return false and do not add the event to the calendar.

Example 1:
Input
["MyCalendarTwo", "book", "book", "book", "book", "book", "book"]
[[], [10, 20], [50, 60], [10, 40], [5, 15], [5, 10], [25, 55]]
Output
[null, true, true, true, false, true, true]
Explanation
MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
myCalendarTwo.book(10, 20); // return True, The event can be booked.
myCalendarTwo.book(50, 60); // return True, The event can be booked.
myCalendarTwo.book(10, 40); // return True, The event can be double booked.
myCalendarTwo.book(5, 15);  // return False, The event cannot be booked, because it would result in a triple booking.
myCalendarTwo.book(5, 10); // return True, The event can be booked, as it does not use time 10 which is already double booked.
myCalendarTwo.book(25, 55); // return True, The event can be booked, as the time in [25, 40) will be double booked with the third event, the time [40, 50) will be single booked, and the time [50, 55) will be double booked with the second event.


Constraints:

0 <= start < end <= 109
At most 1000 calls will be made to book.
 */