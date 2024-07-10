package leetdaily.easy;

import java.util.ArrayDeque;
import java.util.Deque;

public class LogFolder1598 {
    public static void main(String[] args) {
        String[] logs = {"d1/","d2/","../","d21/","./"};
        System.out.println(minOperations(logs));
    }

//    counter; time: O(n), space: O(1)
//    Note: String matching operations take linear time with respect to the length of the string.
//    However, given the constraint that the length of the strings is limited to 10,
//    this does not significantly impact the overall time complexity.
    public static int minOperations(String[] logs) {
        int folderDepth = 0;
        for(String op : logs) {
            if(op.equals("../")) {
                folderDepth = Math.max(0, folderDepth - 1);
            } else if(!op.equals("./")) {
                folderDepth++;
            }
        }
        return folderDepth;
    }

//    stack; time: O(n), space: O(n)
//    no need for a stack actually as we only care about depth, not the actual path taken; included for the sake of completeness only
    public static int minOperations1(String[] logs) {
        Deque<String> folderStack = new ArrayDeque<>();
        for(String op : logs) {
            if(op.equals("../")) {
                if(!folderStack.isEmpty())
                    folderStack.pop();
            } else if(!op.equals("./")){
                folderStack.push(op);
            }
        }
        return folderStack.size();
    }
}

/*
The Leetcode file system keeps a log each time some user performs a change folder operation.
The operations are described below:
"../" : Move to the parent folder of the current folder. (If you are already in the main folder, remain in the same folder).
"./" : Remain in the same folder.
"x/" : Move to the child folder named x (This folder is guaranteed to always exist).
You are given a list of strings logs where logs[i] is the operation performed by the user at the ith step.
The file system starts in the main folder, then the operations in logs are performed.
Return the minimum number of operations needed to go back to the main folder after the change folder operations.
Example 1:
Input: logs = ["d1/","d2/","../","d21/","./"]
Output: 2
Explanation: Use this change folder operation "../" 2 times and go back to the main folder.
Example 2:
Input: logs = ["d1/","d2/","./","d3/","../","d31/"]
Output: 3
Example 3:
Input: logs = ["d1/","../","../","../"]
Output: 0

Constraints:
1 <= logs.length <= 103
2 <= logs[i].length <= 10
logs[i] contains lowercase English letters, digits, '.', and '/'.
logs[i] follows the format described in the statement.
Folder names consist of lowercase English letters and digits.
 */
