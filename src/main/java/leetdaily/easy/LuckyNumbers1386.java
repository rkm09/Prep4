package leetdaily.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LuckyNumbers1386 {
    public static void main(String[] args) {
        int[][] matrix = {{3,7,8},{9,11,13},{15,16,17}};
        System.out.println(luckyNumbers(matrix));
    }

//    simulation; time: O(m*n), space: O(m + n)
    public static List<Integer> luckyNumbers (int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        Set<Integer> rowSet = new HashSet<>();
        Set<Integer> colSet = new HashSet<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int minRow, maxCol;
        for(int i = 0 ; i < m ; i++) {
            minRow = Integer.MAX_VALUE;
            for(int j = 0 ; j < n ; j++) {
                if(matrix[i][j] < minRow) {
                    minRow = matrix[i][j];
                }
            }
            rowSet.add(minRow);
        }
        for(int j = 0 ; j < n ; j++) {
            maxCol = 0;
            for(int i = 0 ; i < m ; i++) {
                if(matrix[i][j] > maxCol) {
                    maxCol = matrix[i][j];
                }
            }
            colSet.add(maxCol);
        }

        for(Integer elem : rowSet)
            if(colSet.contains(elem))
                res.add(elem);

        return res;
    }
}

/*
Given an m x n matrix of distinct numbers, return all lucky numbers in the matrix in any order.
A lucky number is an element of the matrix such that it is the minimum element in its row and maximum in its column.
Example 1:
Input: matrix = [[3,7,8],[9,11,13],[15,16,17]]
Output: [15]
Explanation: 15 is the only lucky number since it is the minimum in its row and the maximum in its column.
Example 2:
Input: matrix = [[1,10,4,2],[9,3,8,7],[15,16,17,12]]
Output: [12]
Explanation: 12 is the only lucky number since it is the minimum in its row and the maximum in its column.
Example 3:
Input: matrix = [[7,8],[1,2]]
Output: [7]
Explanation: 7 is the only lucky number since it is the minimum in its row and the maximum in its column.

Constraints:
m == mat.length
n == mat[i].length
1 <= n, m <= 50
1 <= matrix[i][j] <= 105.
All elements in the matrix are distinct.

 */