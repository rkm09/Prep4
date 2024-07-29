package leetdaily.medium;

import java.util.Arrays;

public class TossStrangeCoins1230 {
    public static void main(String[] args) {
        double[] prob = {0.5,0.5,0.5,0.5,0.5};
        System.out.println(probabilityOfHeads(prob, 0));
    }

//    recursive dp; time: O(n.target), space: O(n.target)
    public static double probabilityOfHeads(double[] prob, int target) {
        int n = prob.length;
        double[][] memo = new double[n][target + 1];
        for(double[] row : memo)
            Arrays.fill(row, -1);
        return findProbability(0, n, memo, prob, target);
    }

    private static double findProbability(int index, int n, double[][] memo, double[] prob, int target) {
//        return 0 if the target is less than zero, coz we have more heads than we need
        if(target < 0) return 0;
//        after tossing all the coins if we get the required number of heads, return 1 to count this case
//        otherwise return 0;
        if(index == n) return target == 0 ? 1 : 0;
        if(memo[index][target] != -1)
            return memo[index][target];
        memo[index][target] = findProbability(index + 1, n, memo, prob, target - 1) * prob[index]
                + findProbability(index + 1, n, memo, prob, target) * (1 - prob[index]);
        return memo[index][target];
    }
}

/*
You have some coins.  The i-th coin has a probability prob[i] of facing heads when tossed.
Return the probability that the number of coins facing heads equals target if you toss every coin exactly once.
Example 1:
Input: prob = [0.4], target = 1
Output: 0.40000
Example 2:
Input: prob = [0.5,0.5,0.5,0.5,0.5], target = 0
Output: 0.03125

Constraints:
1 <= prob.length <= 1000
0 <= prob[i] <= 1
0 <= target <= prob.length
Answers will be accepted as correct if they are within 10^-5 of the correct answer.

 */
