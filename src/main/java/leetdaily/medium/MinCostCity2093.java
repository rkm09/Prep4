package leetdaily.medium;

import java.util.*;

public class MinCostCity2093 {
    public static void main(String[] args) {
        int[][] highways = {{0, 1, 4}, {2, 1, 3}, {1, 4, 1}, {3, 2, 3}, {3, 4, 2}};
        System.out.println(minimumCost(5, highways, 1));
    }

    //    dijikstra's algo; time: O(n.k + e), space: O(n.k + e) [n - number of nodes, k - discounts used, e - edges]
    public static int minimumCost(int n, int[][] highways, int discounts) {
//        construct the graph from the given highway's array
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++)
            graph.add(new ArrayList<>());
        for (int[] highway : highways) {
            int u = highway[0], v = highway[1], toll = highway[2];
            graph.get(u).add(new int[]{v, toll});
            graph.get(v).add(new int[]{u, toll});
        }
//        min heap priority queue to store tuples of cost, city & discounts used
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
//        start from city 0, cost 0 and 0 discounts used
        pq.offer(new int[]{0, 0, 0});
//        2d array to track minimum distance to a given city with a given number fo discounts used
        int[][] dist = new int[n][discounts + 1];
        for (int[] row : dist)
            Arrays.fill(row, Integer.MAX_VALUE);
        dist[0][0] = 0;
        boolean[][] visited = new boolean[n][discounts + 1];

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int currentCost = current[0], city = current[1], discountsUsed = current[2];
//            skip if already visited the city with the same number of discounts used
            if (visited[city][discountsUsed]) continue;
            visited[city][discountsUsed] = true;
//            explore all neighbours of the current city
            for (int[] neighbour : graph.get(city)) {
                int nextCity = neighbour[0], toll = neighbour[1];
//                Case 1: move to the neighbour without using the discount
                if (currentCost + toll < dist[nextCity][discountsUsed]) {
                    dist[nextCity][discountsUsed] = currentCost + toll;
                    pq.offer(
                            new int[]{
                                    dist[nextCity][discountsUsed],
                                    nextCity,
                                    discountsUsed
                            }
                    );
                }
//                Case 2: move to the neighbour using the discount if available
                if (discountsUsed < discounts) {
                    int newCostWithDiscount = currentCost + toll / 2;
                    if (newCostWithDiscount < dist[nextCity][discountsUsed + 1]) {
                        dist[nextCity][discountsUsed + 1] = newCostWithDiscount;
                        pq.offer(
                                new int[]{
                                        dist[nextCity][discountsUsed + 1],
                                        nextCity,
                                        discountsUsed + 1
                                }
                        );
                    }
                }
            }
        }
//              find the minimum cost to reach n - 1 with any number of discounts used
        int minCost = Integer.MAX_VALUE;
        for (int d = 0; d <= discounts; d++)
            minCost = Math.min(minCost, dist[n - 1][d]);

        return minCost == Integer.MAX_VALUE ? -1 : minCost;

    }
}

/*
A series of highways connect n cities numbered from 0 to n - 1. You are given a 2D integer array highways where highways[i] = [city1i, city2i, tolli] indicates that there is a highway that connects city1i and city2i, allowing a car to go from city1i to city2i and vice versa for a cost of tolli.
You are also given an integer discounts which represents the number of discounts you have. You can use a discount to travel across the ith highway for a cost of tolli / 2 (integer division). Each discount may only be used once, and you can only use at most one discount per highway.
Return the minimum total cost to go from city 0 to city n - 1, or -1 if it is not possible to go from city 0 to city n - 1.

Example 1:
Input: n = 5, highways = [[0,1,4],[2,1,3],[1,4,11],[3,2,3],[3,4,2]], discounts = 1
Output: 9
Explanation:
Go from 0 to 1 for a cost of 4.
Go from 1 to 4 and use a discount for a cost of 11 / 2 = 5.
The minimum cost to go from 0 to 4 is 4 + 5 = 9.
Example 2:
Input: n = 4, highways = [[1,3,17],[1,2,7],[3,2,5],[0,1,6],[3,0,20]], discounts = 20
Output: 8
Explanation:
Go from 0 to 1 and use a discount for a cost of 6 / 2 = 3.
Go from 1 to 2 and use a discount for a cost of 7 / 2 = 3.
Go from 2 to 3 and use a discount for a cost of 5 / 2 = 2.
The minimum cost to go from 0 to 3 is 3 + 3 + 2 = 8.
Example 3:
Input: n = 4, highways = [[0,1,3],[2,3,2]], discounts = 0
Output: -1
Explanation:
It is impossible to go from 0 to 3 so return -1.

Constraints:
2 <= n <= 1000
1 <= highways.length <= 1000
highways[i].length == 3
0 <= city1i, city2i <= n - 1
city1i != city2i
0 <= tolli <= 105
0 <= discounts <= 500
There are no duplicate highways.
 */