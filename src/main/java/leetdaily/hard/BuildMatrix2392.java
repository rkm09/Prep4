package leetdaily.hard;

import java.util.*;

public class BuildMatrix2392 {
    public static void main(String[] args) {
        int[][] rowConditions = {{1,2},{3,2}};
        int[][] colConditions = {{2,1},{3,2}};
        int[][] res = buildMatrix(3, rowConditions, colConditions);
        for(int[] r : res)
            System.out.println(Arrays.toString(r));
    }

//  topological sort; time: O(max(k*k),n), space: O(max(k*k),n)
    public static int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] orderRows = topologicalSort(rowConditions, k);
        int[] orderCols = topologicalSort(colConditions, k);
        if(orderRows.length == 0 || orderCols.length == 0)
            return new int[0][0];
        int[][] matrix = new int[k][k];
        for(int i = 0 ; i < k ; i++) {
            for(int j = 0 ; j < k ; j++) {
                if(orderRows[i] == orderCols[j])
                    matrix[i][j] = orderRows[i];
            }
        }
        return matrix;
    }

    private static int[] topologicalSort(int[][] edges, int n) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        int[] inDegree = new int[n + 1];
        for(int[] edge : edges) {
            adjMap.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            inDegree[edge[1]]++;
        }
        Deque<Integer> queue = new ArrayDeque<>();
        int[] order = new int[n];
        for(int i = 1 ; i <= n ; i++) {
            if(inDegree[i] == 0)
                queue.offer(i);
        }
        int index = 0;
        while(!queue.isEmpty()) {
            int node = queue.poll();
            order[index++] = node;
            n--;
            for(int neighbour : adjMap.getOrDefault(node, new ArrayList<>())) {
                if(--inDegree[neighbour] == 0)
                    queue.offer(neighbour);
            }
        }
        if(n != 0)
            return new int[0];
        return order;
    }
}

/*
You are given a positive integer k. You are also given:
a 2D integer array rowConditions of size n where rowConditions[i] = [abovei, belowi], and
a 2D integer array colConditions of size m where colConditions[i] = [lefti, righti].
The two arrays contain integers from 1 to k.
You have to build a k x k matrix that contains each of the numbers from 1 to k exactly once. The remaining cells should have the value 0.
The matrix should also satisfy the following conditions:
The number abovei should appear in a row that is strictly above the row at which the number belowi appears for all i from 0 to n - 1.
The number lefti should appear in a column that is strictly left of the column at which the number righti appears for all i from 0 to m - 1.
Return any matrix that satisfies the conditions. If no answer exists, return an empty matrix.
Example 1:
Input: k = 3, rowConditions = [[1,2],[3,2]], colConditions = [[2,1],[3,2]]
Output: [[3,0,0],[0,0,1],[0,2,0]]
Explanation: The diagram above shows a valid example of a matrix that satisfies all the conditions.
The row conditions are the following:
- Number 1 is in row 1, and number 2 is in row 2, so 1 is above 2 in the matrix.
- Number 3 is in row 0, and number 2 is in row 2, so 3 is above 2 in the matrix.
The column conditions are the following:
- Number 2 is in column 1, and number 1 is in column 2, so 2 is left of 1 in the matrix.
- Number 3 is in column 0, and number 2 is in column 1, so 3 is left of 2 in the matrix.
Note that there may be multiple correct answers.
Example 2:
Input: k = 3, rowConditions = [[1,2],[2,3],[3,1],[2,3]], colConditions = [[2,1]]
Output: []
Explanation: From the first two conditions, 3 has to be below 1 but the third conditions needs 3 to be above 1 to be satisfied.
No matrix can satisfy all the conditions, so we return the empty matrix.

Constraints:
2 <= k <= 400
1 <= rowConditions.length, colConditions.length <= 104
rowConditions[i].length == colConditions[i].length == 2
1 <= abovei, belowi, lefti, righti <= k
abovei != belowi
lefti != righti

 */