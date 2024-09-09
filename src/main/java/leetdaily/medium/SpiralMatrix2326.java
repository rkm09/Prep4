package leetdaily.medium;

import common.ListNode;

import java.util.Arrays;

public class SpiralMatrix2326 {
    public static void main(String[] args) {
//        3,0,2,6,8,1,7,9,4,2,5,5,0
        ListNode next1 = new ListNode(0);
        ListNode head = new ListNode(3, next1);
        int[][] res = spiralMatrix(3,5,head);
    }

//    simulation; time: O(m.n), space: O(1)
    public static int[][] spiralMatrix(int m, int n, ListNode head) {
//        store the east, south, west and north movements in a matrix
        int i = 0, j = 0, currentDirection = 0;
        int[][] movement = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        int[][] res = new int[m][n];
        for(int[] row : res)
            Arrays.fill(row, -1);

        while(head != null) {
            res[i][j] = head.val;
            int nextI = i + movement[currentDirection][0];
            int nextJ = j + movement[currentDirection][1];
//            if we bump into an edge or already filled cell, change the direction
            if(Math.min(nextI, nextJ) < 0 || nextI >= m || nextJ >= n || res[nextI][nextJ] != -1)
                currentDirection = (currentDirection + 1) % 4;
//            note: can't just use nextI and nextJ assignment as currentDirection may have got updated to avoid going out of bounds
            i += movement[currentDirection][0];
            j += movement[currentDirection][1];

            head = head.next;
        }

        return res;
    }
}

/*
You are given two integers m and n, which represent the dimensions of a matrix.
You are also given the head of a linked list of integers.
Generate an m x n matrix that contains the integers in the linked list presented in spiral order (clockwise), starting from the top-left of the matrix. If there are remaining empty spaces, fill them with -1.
Return the generated matrix.
Example 1:
Input: m = 3, n = 5, head = [3,0,2,6,8,1,7,9,4,2,5,5,0]
Output: [[3,0,2,6,8],[5,0,-1,-1,1],[5,2,4,9,7]]
Explanation: The diagram above shows how the values are printed in the matrix.
Note that the remaining spaces in the matrix are filled with -1.
Example 2:
Input: m = 1, n = 4, head = [0,1,2]
Output: [[0,1,2,-1]]
Explanation: The diagram above shows how the values are printed from left to right in the matrix.
The last space in the matrix is set to -1.

Constraints:
1 <= m, n <= 105
1 <= m * n <= 105
The number of nodes in the list is in the range [1, m * n].
0 <= Node.val <= 1000

 */