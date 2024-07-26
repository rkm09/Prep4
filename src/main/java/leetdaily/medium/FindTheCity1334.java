package leetdaily.medium;

import java.util.*;

public class FindTheCity1334 {
    public static void main(String[] args) {
        int[][] edges = {{0,1,3},{1,2,1},{1,3,4},{2,3,1}};
        System.out.println(findTheCity(4, edges, 4));
    }

//    bellman ford; time: O(n^4), space: O(n^2)
    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
//        large value to represent infinity
        int INF = (int) 1e9 + 7;
//        matrix to store shortest path distances from each node
        int[][] shortestPathMatrix = new int[n][n];
//        initialize shortest path matrix
        for(int i = 0 ; i < n ; i++) {
//            set all distances to infinity
            Arrays.fill(shortestPathMatrix[i], INF);
//            distance to itself is zero
            shortestPathMatrix[i][i] = 0;
        }
//      populate the matrix with initial edge weights for an undirected graph
        for(int[] edge : edges) {
            shortestPathMatrix[edge[0]][edge[1]] = edge[2];
            shortestPathMatrix[edge[1]][edge[0]] = edge[2];
        }
//      compute shortest path from each city using bellman-ford
        for(int i = 0 ; i < n ; i++) {
            bellmanFord(n, edges, shortestPathMatrix[i], i);
        }
//      find the city with the fewest number of reachable cities within the distance threshold
        return getCityWithFewestReachable(n, shortestPathMatrix, distanceThreshold);
    }

//    Bellman ford to find the shortest path from a source city
    private static void bellmanFord(int n, int[][] edges, int[] shortestPathDistances, int source) {
//        initialize distances from the source
        Arrays.fill(shortestPathDistances, Integer.MAX_VALUE);
        shortestPathDistances[source] = 0;
//        relax edges upto n - 1
        for(int i = 1 ; i < n ; i++) {
            for(int[] edge : edges) {
//                update the shortest path distance if a shorter path is found
                int start = edge[0];
                int end = edge[1];
                int weight = edge[2];
                if(shortestPathDistances[start] != Integer.MAX_VALUE
                    && shortestPathDistances[start] + weight < shortestPathDistances[end]) {
                    shortestPathDistances[end] = shortestPathDistances[start] + weight;
                }
                if(shortestPathDistances[end] != Integer.MAX_VALUE
                    && shortestPathDistances[end] + weight < shortestPathDistances[start]) {
                    shortestPathDistances[start] = shortestPathDistances[end] + weight;
                }
            }
        }
    }

//    determine the city with the fewest number of reachable cities within the distance threshold
    private static int getCityWithFewestReachable(int n, int[][] shortestPathMatrix, int distanceThreshold) {
        int cityWithFewestReachable = -1;
        int fewestReachableCount = n;
//        count the number of cities within the distance threshold
        for(int i = 0 ; i < n ; i++) {
            int reachableCount = 0;
            for(int j = 0 ; j < n ; j++) {
                if(i == j) continue; // skip self
                if(shortestPathMatrix[i][j] <= distanceThreshold)
                    reachableCount++;
            }
//            update the city with the fewest reachable cities
            if(reachableCount <= fewestReachableCount) {
                fewestReachableCount = reachableCount;
                cityWithFewestReachable = i;
            }
        }
        return cityWithFewestReachable;
    }
}

/*
There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.
Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.
Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.

Example 1:
Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
Output: 3
Explanation: The figure above describes the graph.
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -> [City 1, City 2]
City 1 -> [City 0, City 2, City 3]
City 2 -> [City 0, City 1, City 3]
City 3 -> [City 1, City 2]
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
Example 2:
Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
Output: 0
Explanation: The figure above describes the graph.
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -> [City 1]
City 1 -> [City 0, City 4]
City 2 -> [City 3, City 4]
City 3 -> [City 2, City 4]
City 4 -> [City 1, City 2, City 3]
The city 0 has 1 neighboring city at a distanceThreshold = 2.

Constraints:
2 <= n <= 100
1 <= edges.length <= n * (n - 1) / 2
edges[i].length == 3
0 <= fromi < toi < n
1 <= weighti, distanceThreshold <= 10^4
All pairs (fromi, toi) are distinct.
 */
