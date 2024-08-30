package leetdaily.hard;

import java.util.Arrays;

public class ModifyGraph2699 {
    private static final int INF = (int) 2e9;
    public static void main(String[] args) {
        ModifyGraph2699 m = new ModifyGraph2699();
        int[][] edges = {{4,1,-1},{2,0,-1},{0,3,-1},{4,3,-1}};
        int[][] res = m.modifiedGraphEdges(5,edges,0,1,5);
        Arrays.stream(res).forEach(System.out::println);
    }

//    dijkstra; time: O(E*V^2), space: O(V^2)
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
//        Step1: Compute the initial shortest distance from source to destination
        long currentShortestDistance = runDijkstra(edges, n, source, destination);
//        if the current shortest distance is less than the target, return an empty result
        if(currentShortestDistance < target) return new int[0][0];
        boolean matchesTarget = (currentShortestDistance == target);

//        Step2: Iterate through each edge to adjust its weight if necessary
        for(int[] edge : edges) {
            if(edge[2] > 0) continue;
//            set edge weight to a large value if the current distance matches the target, else set to 1
            edge[2] = matchesTarget ? INF : 1;
//            Step 3: If the current shortest distance does not match the target
            if(!matchesTarget) {
//              compute the new shortest distance with the updated edge weight
                long newDistance = runDijkstra(edges, n, source, destination);
//                if the new distance is within the target range, update edge weight to match the target
                if(newDistance <= target) {
                    matchesTarget = true;
                    edge[2] += target - newDistance;
                }
            }
        }
//        return modified edges if the target distance is achieved, else return an empty result
        return matchesTarget ? edges : new int[0][0];
    }

    private long runDijkstra(int[][] edges, int n, int source, int destination) {
//        Step1: Initialize adjacency matrix and distance arrays
        long[][] adjacencyMatrix = new long[n][n];
        long[] minDistance = new long[n];
        boolean[] visited = new boolean[n];
        Arrays.fill(minDistance, INF);
        for(long[] row : adjacencyMatrix)
            Arrays.fill(row, INF);
//        set the distance to source node as zero
        minDistance[source] = 0;

//        Step2: Fill the adjacency matrix with edge weights
        for(int[] edge : edges) {
            if(edge[2] != -1) {
                adjacencyMatrix[edge[0]][edge[1]] = edge[2];
                adjacencyMatrix[edge[1]][edge[0]] = edge[2];
            }
        }

//        Step3: Perform Dijkstra's algorithm
        for(int i = 0 ; i < n ; i++) {
//            find the nearest unvisited node
            int nearestUnvisitedNode = -1;
            for(int j = 0 ; j < n ; j++) {
                if(!visited[j] &&
                        (nearestUnvisitedNode == -1 ||
                                minDistance[j] < minDistance[nearestUnvisitedNode]))
                    nearestUnvisitedNode = j;
            }
//            mark the nearest visited node
            visited[nearestUnvisitedNode] = true;
//            update the shortest distance for each adjacent node
            for(int v = 0 ; v < n ; v++) {
                minDistance[v] = Math.min(
                        minDistance[v],
                        minDistance[nearestUnvisitedNode] + adjacencyMatrix[nearestUnvisitedNode][v]
                );
            }
        }
//        return the shortest distance to the destination
        return minDistance[destination];
    }
}

/*
You are given an undirected weighted connected graph containing n nodes labeled from 0 to n - 1, and an integer array edges where edges[i] = [ai, bi, wi] indicates that there is an edge between nodes ai and bi with weight wi.
Some edges have a weight of -1 (wi = -1), while others have a positive weight (wi > 0)
Your task is to modify all edges with a weight of -1 by assigning them positive integer values in the range [1, 2 * 109] so that the shortest distance between the nodes source and destination becomes equal to an integer target. If there are multiple modifications that make the shortest distance between source and destination equal to target, any of them will be considered correct.
Return an array containing all edges (even unmodified ones) in any order if it is possible to make the shortest distance from source to destination equal to target, or an empty array if it's impossible.
Note: You are not allowed to modify the weights of edges with initial positive weights.
Example 1:
Input: n = 5, edges = [[4,1,-1],[2,0,-1],[0,3,-1],[4,3,-1]], source = 0, destination = 1, target = 5
Output: [[4,1,1],[2,0,1],[0,3,3],[4,3,1]]
Explanation: The graph above shows a possible modification to the edges, making the distance from 0 to 1 equal to 5.
Example 2:
Input: n = 3, edges = [[0,1,-1],[0,2,5]], source = 0, destination = 2, target = 6
Output: []
Explanation: The graph above contains the initial edges. It is not possible to make the distance from 0 to 2 equal to 6 by modifying the edge with weight -1. So, an empty array is returned.
Example 3:
Input: n = 4, edges = [[1,0,4],[1,2,3],[2,3,5],[0,3,-1]], source = 0, destination = 2, target = 6
Output: [[1,0,4],[1,2,3],[2,3,5],[0,3,1]]
Explanation: The graph above shows a modified graph having the shortest distance from 0 to 2 as 6.

Constraints:
1 <= n <= 100
1 <= edges.length <= n * (n - 1) / 2
edges[i].length == 3
0 <= ai, bi < n
wi = -1 or 1 <= wi <= 107
ai != bi
0 <= source, destination < n
source != destination
1 <= target <= 109
The graph is connected, and there are no self-loops or repeated edges
 */