package leetdaily.medium;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

public class UglyNumII264 {
    public static void main(String[] args) {
        System.out.println(nthUglyNumber(10));
    }

//    brute force using tree set; time: O(nlogm), space: O(n)
    public static int nthUglyNumber(int n) {
//        no duplicates, sorted
        TreeSet<Long> treeSet = new TreeSet<>();
        treeSet.add(1L);
        Long currentUNum = 1L;
        for(int i = 0 ; i < n ; i++) {
            currentUNum = treeSet.pollFirst();
            treeSet.add(currentUNum * 2);
            treeSet.add(currentUNum * 3);
            treeSet.add(currentUNum * 5);
        }
        return currentUNum.intValue();
    }

//    priority queue; time: O(nlogn), space: O(m) [n - given index, m - size of the set]
    public static int nthUglyNumber1(int n) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> seen = new HashSet<>();
        minHeap.offer(1L);
        seen.add(1L);
        int[] primeFactors = {2,3,5};
        Long uNum = 1L;
        for(int i = 0 ; i < n ; i++) {
//            get the smallest number
             uNum = minHeap.poll();
//            generate the next
            for(int prime : primeFactors) {
                Long nextU = uNum * prime;
//                avoid duplicates
                if(!seen.contains(nextU)) {
                    seen.add(nextU);
                    minHeap.offer(nextU);
                }
            }
        }
        return uNum.intValue();
    }

//    dp; time: O(n), space: O(n)
    public static int nthUglyNumber2(int n) {
        int[] uNums = new int[n];
        uNums[0] = 1;
        int indexMultipleOf2 = 0, indexMultipleOf3 = 0, indexMultipleOf5 = 0;
        int nextMultipleOf2 = 2, nextMultipleOf3 = 3, nextMultipleOf5 = 5;
        for(int i = 1 ; i < n ; i++) {
//            find the next number as the smallest among the multiples
            int nextUNum = Math.min(nextMultipleOf2, Math.min(nextMultipleOf3, nextMultipleOf5));
            uNums[i] = nextUNum;
//            update the corresponding pointer and the next uNum
            if(nextUNum == nextMultipleOf2) {
                indexMultipleOf2++;
                nextMultipleOf2 = uNums[indexMultipleOf2] * 2;
            }
            if(nextUNum == nextMultipleOf3) {
                indexMultipleOf3++;
                nextMultipleOf3 = uNums[indexMultipleOf3] * 3;
            }
            if(nextUNum == nextMultipleOf5) {
                indexMultipleOf5++;
                nextMultipleOf5 = uNums[indexMultipleOf5] * 5;
            }
        }

        return uNums[n - 1];
    }
}

/*
An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.
Given an integer n, return the nth ugly number.
Example 1:
Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
Example 2:
Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.

Constraints:
1 <= n <= 1690
 */