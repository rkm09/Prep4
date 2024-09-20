package leetdaily.hard;

public class ShortestPalindrome214 {
    public static void main(String[] args) {
        String s = "aacecaaa";
        System.out.println(shortestPalindrome(s));
    }

//    brute force; time: O(n^2), space: O(n)
    public static String shortestPalindrome(String s) {
        int n = s.length();
        String reversedString = new StringBuilder(s).reverse().toString();
        for(int i = 0 ; i < n ; i++) {
            if(s.substring(0, n - i).equals(reversedString.substring(i)))
                return reversedString.substring(0, i) + s;
        }
        return "";
    }
}

/*
You are given a string s. You can convert s to a palindrome by adding characters in front of it.
Return the shortest palindrome you can find by performing this transformation.
Example 1:
Input: s = "aacecaaa"
Output: "aaacecaaa"
Example 2:
Input: s = "abcd"
Output: "dcbabcd"

Constraints:
0 <= s.length <= 5 * 104
s consists of lowercase English letters only.
 */