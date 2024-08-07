package leetdaily.easy;

public class WaterBottles1518 {
    public static void main(String[] args) {
        System.out.println(numWaterBottles(15, 8));
    }

//    simulation [def]; time: O(log(baseM)N), space: O(1) [n - number of full bottles, m - the number of exchanges]
    public static int numWaterBottles(int numBottles, int numExchange) {
        if(numBottles < numExchange)
            return numBottles;
        int count = numBottles;
        while(numBottles >= numExchange) {
            int rem = numBottles % numExchange;
            numBottles /= numExchange;
            count += numBottles;
            numBottles += rem;
        }
        return count;
    }
}

/*
There are numBottles water bottles that are initially full of water. You can exchange numExchange empty water bottles from the market with one full water bottle.
The operation of drinking a full water bottle turns it into an empty bottle.
Given the two integers numBottles and numExchange, return the maximum number of water bottles you can drink.
Example 1:
Input: numBottles = 9, numExchange = 3
Output: 13
Explanation: You can exchange 3 empty bottles to get 1 full water bottle.
Number of water bottles you can drink: 9 + 3 + 1 = 13.
Example 2:
Input: numBottles = 15, numExchange = 4
Output: 19
Explanation: You can exchange 4 empty bottles to get 1 full water bottle.
Number of water bottles you can drink: 15 + 3 + 1 = 19.

Constraints:

1 <= numBottles <= 100
2 <= numExchange <= 100
 */