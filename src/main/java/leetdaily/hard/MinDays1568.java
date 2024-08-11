package leetdaily.hard;

public class MinDays1568 {
    public static void main(String[] args) {
        int[][] grid = {{0,1,1,0},{0,1,1,0},{0,0,0,0}};
        MinDays1568 m = new MinDays1568();
        System.out.println(m.minDays(grid));
    }

    public int minDays(int[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int nr = grid.length, nc = grid[0].length;
        DSU dsu = new DSU(grid);
        for(int r = 0 ; r < nr ; r++) {
            for(int c = 0 ; c < nc ; c++) {
                if(grid[r][c] == 1)  {
//                    mark visited
                    grid[r][c] = 0;
                    if(r - 1 >= 0 && grid[r - 1][c] == 1)
                        dsu.union(r * nc + c, (r - 1) * nc + c);
                    if(r + 1 < nr && grid[r + 1][c] == 1)
                        dsu.union(r * nc + c, (r + 1) * nc + c);
                    if(c - 1 >= 0 && grid[r][c - 1] == 1)
                        dsu.union(r * nc + c, r * nc + (c - 1));
                    if(c + 1 < nc && grid[r][c + 1] == 1)
                        dsu.union(r * nc + c, r * nc + (c + 1));
                }
            }
        }
        return dsu.getCount();
    }

    class DSU {
        private final int[] parent;
        private final int[] rank;
        private int count;
        public DSU(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for(int i = 0 ; i < m; i++) {
                for(int j = 0 ; j < n ; j++) {
                    if(grid[i][j] == 1) {
                        parent[i * n + j] = i * n + j;
                        count++;
                    }
                }
            }
        }
        int find(int i) {
            if(parent[i] != i)
                parent[i] = find(parent[i]);
            return parent[i];
        }
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX != rootY) {
                if (rank[rootX] > rank[rootY])
                    parent[rootY] = rootX;
                else if (rank[rootY] > rank[rootX])
                    parent[rootX] = rootY;
                else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
            }
        }
        boolean isConnected(int a, int b) {
            return find(a) == find(b);
        }
        int getCount() {
            return count;
        }
    }
}


/*
You are given an m x n binary grid where 1 represents land and 0 represents water. An island is a maximal 4-directionally (horizontal or vertical) connected group of 1s.
The grid is said to be connected if we have exactly one island, otherwise is said disconnected.
In one day, we are allowed to change any single land cell (1) into a water cell (0).
Return the minimum number of days to disconnect the grid.
Example 1:
Input: grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
Output: 2
Explanation: We need at least 2 days to get a disconnected grid.
Change land grid[1][1] and grid[0][2] to water and get 2 disconnected island.
Example 2:
Input: grid = [[1,1]]
Output: 2
Explanation: Grid of full water is also disconnected ([[1,1]] -> [[0,0]]), 0 islands.

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 30
grid[i][j] is either 0 or 1.
 */