package leetdaily.medium;


import java.util.Arrays;

public class XORQueries1314 {
    public static void main(String[] args) {
        int[][] queries = {{0,1},{1,2},{0,3},{3,3}};
        int[] arr = {1,3,4,8};
        System.out.println(Arrays.toString(xorQueries(arr, queries)));
    }

//    prefix xor; time: O(n + q), space: O(1)  [fast]
    public static int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] prefixXOR = new int[n + 1];
//        build prefix xor array
        for(int i = 0 ; i < n ; i++)
            prefixXOR[i + 1]  = prefixXOR[i] ^ arr[i];
        int[] res = new int[queries.length];
        for(int i = 0 ; i < queries.length ; i++) {
            int left = queries[i][0];
            int right = queries[i][1];
            res[i] = prefixXOR[right + 1] ^ prefixXOR[left];
        }
        return res;
    }

//    def(iterative); time: O(q.n), space: O(1) [q - number of queries, n - number of elements]
    public static int[] xorQueries1(int[] arr, int[][] queries) {
        int[] res = new int[queries.length];
        int idx = 0;
        for(int[] query : queries) {
            int i = query[0];
            int j = query[1];
            int xorSum = arr[i];
            while(i != j) {
                xorSum ^= arr[++i];
            }
            res[idx++] = xorSum;
        }
        return res;
    }
}

/*
You are given an array arr of positive integers. You are also given the array queries where queries[i] = [lefti, righti].
For each query i compute the XOR of elements from lefti to righti (that is, arr[lefti] XOR arr[lefti + 1] XOR ... XOR arr[righti] ).
Return an array answer where answer[i] is the answer to the ith query.
Example 1:
Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
Output: [2,7,14,8]
Explanation:
The binary representation of the elements in the array are:
1 = 0001
3 = 0011
4 = 0100
8 = 1000
The XOR values for queries are:
[0,1] = 1 xor 3 = 2
[1,2] = 3 xor 4 = 7
[0,3] = 1 xor 3 xor 4 xor 8 = 14
[3,3] = 8
Example 2:
Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
Output: [8,0,4,4]

Constraints:
1 <= arr.length, queries.length <= 3 * 104
1 <= arr[i] <= 109
queries[i].length == 2
0 <= lefti <= righti < arr.length

 */