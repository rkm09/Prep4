package leetdaily.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveStones947 {
    public static void main(String[] args) {
        RemoveStones947 r = new RemoveStones947();
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,1}};
        System.out.println(r.removeStones(stones));
    }

//    dfs; time: O(n^2), space: O(n^2)
    public int removeStones(int[][] stones) {
        int n = stones.length;
        List<List<Integer>> adjacencyList = new ArrayList<>();
//        adjacency list to store graph connections
        for(int i = 0 ; i < n ; i++)
            adjacencyList.add(new ArrayList<>());
//        build the graph, connect stones that share the same row or column
        for(int i = 0 ; i < n ; i++) {
            for(int j = i + 1 ; j < n ; j++) {
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
                    adjacencyList.get(i).add(j);
                    adjacencyList.get(j).add(i);
                }
            }
        }

        int numOfConnectedComponents = 0;
        boolean[] visited = new boolean[n];
//        traverse all stones using DFS to count connected components
        for(int i = 0 ; i < n ; i++) {
            if(!visited[i]) {
                depthFirstSearch(adjacencyList, visited, i);
                numOfConnectedComponents++;
            }
        }
//        maximum stones that can be removed is total stones minus number of connected components
        return n - numOfConnectedComponents;
    }

//    DFS to visit all stones in a connected component
    private void depthFirstSearch(List<List<Integer>> adjacencyList, boolean[] visited, int stone) {
        visited[stone] = true;
        for(int neighbour : adjacencyList.get(stone)) {
            if(!visited[neighbour])
                depthFirstSearch(adjacencyList, visited, neighbour);
        }
    }

//    disjoint set union;
    public int removeStones1(int[][] stones) {
        int n = stones.length;
        UnionFind uf = new UnionFind(n);
        for(int i = 0 ; i < n ; i++) {
            for(int j = i + 1 ; j < n ; j++) {
                if(stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1])
                    uf.union(i,j);
            }
        }
        return n - uf.getCount();
    }

    class UnionFind {
        int[] parent;
        int[] rank;
        int count;
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for(int i = 0 ; i < n ; i++) {
                parent[i] = i;
            }
            count = n;
        }

//        path compression
        int find(int i) {
            if(parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

//        union
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                if(rank[rootX] > rank[rootY])
                    parent[rootY] = rootX;
                else if(rank[rootX] < rank[rootY])
                    parent[rootX] = rootY;
                else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
            }
        }
//        get count
        int getCount() {
            return count;
        }
    }
}

/*
On a 2D plane, we place n stones at some integer coordinate points. Each coordinate point may have at most one stone.
A stone can be removed if it shares either the same row or the same column as another stone that has not been removed.
Given an array stones of length n where stones[i] = [xi, yi] represents the location of the ith stone, return the largest possible number of stones that can be removed.
Example 1:
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
Example 2:
Input: stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
Output: 3
Explanation: One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
Example 3:
Input: stones = [[0,0]]
Output: 0
Explanation: [0,0] is the only stone on the plane, so you cannot remove it.

Constraints:
1 <= stones.length <= 1000
0 <= xi, yi <= 104
No two stones are at the same coordinate point.
 */