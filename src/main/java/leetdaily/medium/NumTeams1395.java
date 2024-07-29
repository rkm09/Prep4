package leetdaily.medium;

public class NumTeams1395 {
    public static void main(String[] args) {
        int[] rating = {2,5,3,4,1};
        System.out.println(numTeams(rating));
    }

//    dp; memoization; time: O(n^2), space: O(n)
    public static int numTeams(int[] rating) {
        int n = rating.length, teams = 0;
//        current index, team size
        Integer[][] increasingCache = new Integer[n][4];
        Integer[][] decreasingCache = new Integer[n][4];

//      calculate total teams by considering each soldier as a starting point
        for(int startIndex = 0 ; startIndex < n ; startIndex++) {
            teams += countIncreasingTeams(rating, startIndex, 1, increasingCache)
                    + countDecreasingTeams(rating, startIndex, 1, decreasingCache);
        }
        return teams;
    }

    private static int countIncreasingTeams(int[] rating, int currentIndex, int teamSize, Integer[][] increasingCache) {
        int n = rating.length;
//        base case: reached end of array
        if(currentIndex == n) return 0;
//        base case: found a valid team of size 3
        if(teamSize == 3) return 1;
//        return cached result if available
        if(increasingCache[currentIndex][teamSize] != null)
            return increasingCache[currentIndex][teamSize];
//        recursively count teams with increasing index
        int validTeams = 0;
        for(int nextIndex = currentIndex + 1 ; nextIndex < n ; nextIndex++) {
            if(rating[nextIndex] > rating[currentIndex]) {
                validTeams += countIncreasingTeams(rating, nextIndex, teamSize + 1, increasingCache);
            }
        }
        return increasingCache[currentIndex][teamSize] = validTeams;
    }

    private static int countDecreasingTeams(int[] rating, int currentIndex, int teamSize, Integer[][] decreasingCache) {
        int n = rating.length;
//        base case: reached end of array
        if(currentIndex == n) return 0;
//        base case: found a valid team of size 3
        if(teamSize == 3) return 1;
//        return cached result if available
        if(decreasingCache[currentIndex][teamSize] != null)
            return decreasingCache[currentIndex][teamSize];
//        recursively count teams with decreasing index
        int validTeams = 0;
        for(int nextIndex = currentIndex + 1 ; nextIndex < n ; nextIndex++) {
            if(rating[nextIndex] < rating[currentIndex]) {
                validTeams += countDecreasingTeams(rating, nextIndex, teamSize + 1, decreasingCache);
            }
        }
        return decreasingCache[currentIndex][teamSize] = validTeams;
    }
}

/*
There are n soldiers standing in a line. Each soldier is assigned a unique rating value.
You have to form a team of 3 soldiers amongst them under the following rules:
Choose 3 soldiers with index (i, j, k) with rating (rating[i], rating[j], rating[k]).
A team is valid if: (rating[i] < rating[j] < rating[k]) or (rating[i] > rating[j] > rating[k]) where (0 <= i < j < k < n).
Return the number of teams you can form given the conditions. (soldiers can be part of multiple teams).
Example 1:
Input: rating = [2,5,3,4,1]
Output: 3
Explanation: We can form three teams given the conditions. (2,3,4), (5,4,1), (5,3,1).
Example 2:
Input: rating = [2,1,3]
Output: 0
Explanation: We can't form any team given the conditions.
Example 3:
Input: rating = [1,2,3,4]
Output: 4

Constraints:
n == rating.length
3 <= n <= 1000
1 <= rating[i] <= 105
All the integers in rating are unique.

 */