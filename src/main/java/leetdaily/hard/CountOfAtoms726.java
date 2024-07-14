package leetdaily.hard;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CountOfAtoms726 {
    public static void main(String[] args) {
        String formula = "H2O";
        System.out.println(countOfAtoms(formula));
    }

//    reverse scanning with regex; time: O(nlogn), space: O(n)
    public static String countOfAtoms(String formula) {
//        every element matcher will be a quintuple
        Matcher matcher = Pattern.compile("([A-Z][a-z]*)(\\d*)|(\\()|(\\))(\\d*)")
         .matcher(formula);

        List<String[]> list = new ArrayList<>();
        while(matcher.find()) {
            list.add(new String[] {
                    matcher.group(1),
                    matcher.group(2),
                    matcher.group(3),
                    matcher.group(4),
                    matcher.group(5)
            });
        }
        Collections.reverse(list);

//        map to store the count of atoms
        Map<String, Integer> finalMap = new HashMap<>();
//        stack to keep track of the nested multiplicities
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1);
//        current multiplicity
        int runningMul = 1;
//        parse the formula
        for(String[] quintuple : list) {
            String atom = quintuple[0];
            String count = quintuple[1];
            String left = quintuple[2];
            String right = quintuple[3];
            String multiplier = quintuple[4];

//            if atom, add it to the final map
            if(atom != null) {
                int cnt = count.length() > 0 ? Integer.parseInt(count) : 1;
                finalMap.put(atom, finalMap.getOrDefault(atom, 0) + cnt * runningMul);
            } else if(right != null) {
//                if right parenthesis, then multiply the runningMul
                int currentMultiplier = multiplier.length() > 0 ? Integer.parseInt(multiplier) : 1;
                runningMul *= currentMultiplier;
                stack.push(currentMultiplier);
            } else if(left != null) {
//                if left parenthesis, then divide the runningMul
                runningMul /= stack.pop();
            }
        }
//      sort the final map
        TreeMap<String, Integer> sortedMap = new TreeMap<>(finalMap);
//      generate the answer string
        StringBuilder ans = new StringBuilder();
        for(String atom : sortedMap.keySet()) {
            ans.append(atom);
            if(sortedMap.get(atom) > 1)
                ans.append(sortedMap.get(atom));
        }
        return ans.toString();
    }
}

/*
Given a string formula representing a chemical formula, return the count of each atom.
The atomic element always starts with an uppercase character, then zero or more lowercase letters, representing the name.
One or more digits representing that element's count may follow if the count is greater than 1. If the count is 1, no digits will follow.
For example, "H2O" and "H2O2" are possible, but "H1O2" is impossible.
Two formulas are concatenated together to produce another formula.
For example, "H2O2He3Mg4" is also a formula.
A formula placed in parentheses, and a count (optionally added) is also a formula.
For example, "(H2O2)" and "(H2O2)3" are formulas.
Return the count of all elements as a string in the following form: the first name (in sorted order), followed by its count (if that count is more than 1), followed by the second name (in sorted order), followed by its count (if that count is more than 1), and so on.
The test cases are generated so that all the values in the output fit in a 32-bit integer.
Example 1:
Input: formula = "H2O"
Output: "H2O"
Explanation: The count of elements are {'H': 2, 'O': 1}.
Example 2:
Input: formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.
Example 3:
Input: formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.

Constraints:
1 <= formula.length <= 1000
formula consists of English letters, digits, '(', and ')'.
formula is always valid.
 */