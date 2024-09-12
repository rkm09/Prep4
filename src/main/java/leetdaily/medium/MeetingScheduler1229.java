package leetdaily.medium;

import java.util.*;

public class MeetingScheduler1229 {
    public static void main(String[] args) {
        int[][] slots1 = {{10,50},{60,120},{140,210}};
        int[][] slots2 = {{0,15},{60,70}};
        System.out.println(minAvailableDuration(slots1, slots2, 8));
    }

//    heap; time: O((M+N)log(M+N)), space: O(M+N) [faster]
    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
//        note: maintaining a single heap is sufficient; check the constraint.
//        "It is guaranteed that no two availability slots of the same person intersect with each other."
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        for(int[] slot : slots1) {
            if(slot[1] - slot[0] >= duration)
                pq.offer(slot);
        }
        for(int[] slot : slots2) {
            if(slot[1] - slot[0] >= duration)
                pq.offer(slot);
        }
//        note: not using isEmpty() here, since we need a minimum of 2 elements in the queue always, not 1
        while(pq.size() > 1) {
            int[] slot1 = pq.poll();
            int[] slot2 = pq.peek();
            if(slot1[1] >= slot2[0] + duration)
                return new ArrayList<>(Arrays.asList(slot2[0], slot2[0] + duration));
        }
        return new ArrayList<>();
    }

//    two pointer; time: O((M+N)log(M+N)), space: O(logN) [a variant of quick sort in java]
    public static List<Integer> minAvailableDuration1(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(slots2, Comparator.comparingInt(a -> a[0]));
        int pointer1 = 0, pointer2 = 0;
        while(pointer1 < slots1.length && pointer2 < slots2.length) {
//            find boundaries of the intersection or the common slot
            int intersectLeft = Math.max(slots1[pointer1][0], slots2[pointer2][0]);
            int intersectRight = Math.min(slots1[pointer1][1], slots2[pointer2][1]);
            if(intersectRight - intersectLeft >= duration)
                return new ArrayList<>(Arrays.asList(intersectLeft, intersectLeft + duration));
//            always move the pointer of the one that ends earlier
            if(slots1[pointer1][1] < slots2[pointer2][1])
                pointer1++;
            else
                pointer2++;
        }
        return new ArrayList<>();
    }
}

/*
Given the availability time slots arrays slots1 and slots2 of two people and a meeting duration, return the earliest time slot that works for both of them and is of duration duration.
If there is no common time slot that satisfies the requirements, return an empty array.
The format of a time slot is an array of two elements [start, end] representing an inclusive time range from start to end.
It is guaranteed that no two availability slots of the same person intersect with each other. That is, for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
Example 1:
Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
Output: [60,68]
Example 2:
Input: slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
Output: []

Constraints:
1 <= slots1.length, slots2.length <= 104
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 109
1 <= duration <= 106
 */