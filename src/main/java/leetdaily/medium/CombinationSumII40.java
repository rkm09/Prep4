package leetdaily.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII40 {
    List<List<Integer>> answer;
    public static void main(String[] args) {
        CombinationSumII40 c = new CombinationSumII40();
        int[] candidates = {10,1,2,7,6,1,5};
        System.out.println(c.combinationSum2(candidates, 8));
    }

//    backtracking; time: O(2^n), space: O(n)
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        answer = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(new ArrayList<>(), candidates, target, 0);
        return answer;
    }

    private void backtrack(List<Integer> tempList, int[] candidates, int totalLeft, int index) {
        if(totalLeft < 0) return;
        else if(totalLeft == 0) {
//            add to list if total equals target
            answer.add(new ArrayList<>(tempList));
        } else {
            for(int i = index ; i < candidates.length && totalLeft >= candidates[i] ; i++) {
                if(i > index && candidates[i] == candidates[i - 1]) continue;
//                add to tempList
                tempList.add(candidates[i]);
//                check for all possible scenarios
                backtrack(tempList, candidates, totalLeft - candidates[i], i + 1);
//                backtrack the tempList
                tempList.remove(tempList.size() - 1);
            }
        }
    }
}

/*
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.
Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]
Example 2:
Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]

Constraints:
1 <= candidates.length <= 100
1 <= candidates[i] <= 50
1 <= target <= 30
 */