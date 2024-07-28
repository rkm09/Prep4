package leetdaily.hard;

import java.util.*;

public class SecondMinimum2045 {
    public static void main(String[] args) {
        int[][] edges = {{1,2},{1,3},{1,4},{3,4},{4,5}};
        System.out.println(secondMinimum(5, edges, 3, 5));
    }

//    bfs; time: O(ne + e), space: O(n + e) [n -> num of cities, e -> edges]
//    ps: this approach works only coz the graph is equally weighted for all edges;
    public static int secondMinimum(int n, int[][] edges, int time, int change) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for(int[] edge : edges) {
            adjMap.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adjMap.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }
//        dist1 & dist2 store minimum and second minimum distances from node1 to all nodes
        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];
        Arrays.fill(dist1, -1);
        Arrays.fill(dist2, -1);

//        stores {node, freq}
        Deque<int[]> queue = new ArrayDeque<>();
//        start with node 1 and it's min dist
        queue.offer(new int[]{1, 1});
        dist1[1] = 0;

        while(!queue.isEmpty()) {
            int[] temp = queue.poll();
            int node = temp[0];
            int freq = temp[1];
            int timeTaken = freq == 1 ? dist1[node] : dist2[node];
//            if time taken falls under the red bracket, wait till the path turns green
            if((timeTaken / change) % 2 == 1)
                timeTaken = change * (timeTaken / change + 1) + time;
            else
                timeTaken = timeTaken + time;

            if(!adjMap.containsKey(node)) continue;
            for(int neighbour : adjMap.get(node)) {
                if(dist1[neighbour] == -1) {
                    dist1[neighbour] = timeTaken;
                    queue.offer(new int[]{neighbour, 1});
                } else if(dist2[neighbour] == -1 && dist1[neighbour] != timeTaken) {
                    if(neighbour == n) return timeTaken;
                    dist2[neighbour] = timeTaken;
                    queue.offer(new int[]{neighbour, 2});
                }
            }
        }

        return 0;
    }
}

/*
A city is represented as a bi-directional connected graph with n vertices where each vertex is labeled from 1 to n (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself. The time taken to traverse any edge is time minutes.
Each vertex has a traffic signal which changes its color from green to red and vice versa every change minutes. All signals change at the same time. You can enter a vertex at any time, but can leave a vertex only when the signal is green. You cannot wait at a vertex if the signal is green.
The second minimum value is defined as the smallest value strictly larger than the minimum value.
For example the second minimum value of [2, 3, 4] is 3, and the second minimum value of [2, 2, 4] is 4.
Given n, edges, time, and change, return the second minimum time it will take to go from vertex 1 to vertex n.
Notes:
You can go through any vertex any number of times, including 1 and n.
You can assume that when the journey starts, all signals have just turned green.

Example 1:
Input: n = 5, edges = [[1,2],[1,3],[1,4],[3,4],[4,5]], time = 3, change = 5
Output: 13
Explanation:
The figure on the left shows the given graph.
The blue path in the figure on the right is the minimum time path.
The time taken is:
- Start at 1, time elapsed=0
- 1 -> 4: 3 minutes, time elapsed=3
- 4 -> 5: 3 minutes, time elapsed=6
Hence the minimum time needed is 6 minutes.
The red path shows the path to get the second minimum time.
- Start at 1, time elapsed=0
- 1 -> 3: 3 minutes, time elapsed=3
- 3 -> 4: 3 minutes, time elapsed=6
- Wait at 4 for 4 minutes, time elapsed=10
- 4 -> 5: 3 minutes, time elapsed=13
Hence the second minimum time is 13 minutes.
Example 2:
Input: n = 2, edges = [[1,2]], time = 3, change = 2
Output: 11
Explanation:
The minimum time path is 1 -> 2 with time = 3 minutes.
The second minimum time path is 1 -> 2 -> 1 -> 2 with time = 11 minutes.

Constraints:
2 <= n <= 104
n - 1 <= edges.length <= min(2 * 104, n * (n - 1) / 2)
edges[i].length == 2
1 <= ui, vi <= n
ui != vi
There are no duplicate edges.
Each vertex can be reached directly or indirectly from every other vertex.
1 <= time, change <= 103

 */