package leetdaily.hard;

import java.util.Arrays;
import java.util.List;

public class NumberToWord273 {
    //    list to store words for numbers
    public static final List<NumberWord> numberToWordsList = Arrays.asList(
            new NumberWord(1000000000, "Billion"), new NumberWord(1000000, "Million"),
            new NumberWord(1000, "Thousand"), new NumberWord(100, "Hundred"),
            new NumberWord(90, "Ninety"), new NumberWord(80, "Eighty"),
            new NumberWord(70, "Seventy"), new NumberWord(60, "Sixty"),
            new NumberWord(50, "Fifty"), new NumberWord(40, "Forty"),
            new NumberWord(30, "Thirty"), new NumberWord(20, "Twenty"),
            new NumberWord(19, "Nineteen"), new NumberWord(18, "Eighteen"),
            new NumberWord(17, "Seventeen"), new NumberWord(16, "Sixteen"),
            new NumberWord(15, "Fifteen"), new NumberWord(14, "Fourteen"),
            new NumberWord(13, "Thirteen"), new NumberWord(12, "Twelve"),
            new NumberWord(11, "Eleven"), new NumberWord(10, "Ten"),
            new NumberWord(9, "Nine"), new NumberWord(8, "Eight"),
            new NumberWord(7, "Seven"), new NumberWord(6, "Six"),
            new NumberWord(5, "Five"), new NumberWord(4, "Four"),
            new NumberWord(3, "Three"), new NumberWord(2, "Two"),
            new NumberWord(1, "One")
    );

    public static void main(String[] args) {
        int num = 123;
        System.out.println(numberToWords(num));
    }

//    pair based approach; time: O(K), space: O(log10n) [k - number to words map, n - number]
    public static String numberToWords(int num) {
        if(num == 0) return "Zero";
        for(NumberWord nw : numberToWordsList) {
//            check if the number is greater than or equal to the current unit
            if(num >= nw.value) {
//                convert the quotient to words if the current word is greater than or equal to 100
                String prefix = (num >= 100) ? numberToWords(num / nw.value) + " " : "";
//                get the word for the current unit
                String unit = nw.word;
//                convert the remainder to words if it is not zero
                String suffix = (num % nw.value == 0) ? "" : " " + numberToWords(num % nw.value);

                return prefix + unit + suffix;
            }
        }
        return "";
    }

}

class NumberWord {
    int value;
    String word;
    NumberWord(int value, String word) {
        this.value = value;
        this.word = word;
    }
}

/*
Convert a non-negative integer num to its English words representation.
Example 1:
Input: num = 123
Output: "One Hundred Twenty Three"
Example 2:
Input: num = 12345
Output: "Twelve Thousand Three Hundred Forty Five"
Example 3:
Input: num = 1234567
Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"

Constraints:
0 <= num <= 231 - 1
 */