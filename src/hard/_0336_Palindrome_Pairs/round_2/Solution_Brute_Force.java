package hard._0336_Palindrome_Pairs.round_2;

import java.util.*;

public class Solution_Brute_Force {

    public static void main(String[] args) {
        Solution_Brute_Force s = new Solution_Brute_Force();
        String[] words;

        words = new String[] {"abcd","dcba","lls","s","sssll"};
        System.out.println(s.palindromePairs(words)); // [[0,1],[1,0],[3,2],[2,4]]
        words = new String[] {"bat","tab","cat"};
        System.out.println(s.palindromePairs(words)); // [[0,1],[1,0]]
        words = new String[] {"a",""};
        System.out.println(s.palindromePairs(words)); // [[0,1],[1,0]]
    }

    /*  Brute Force
        Time Limit Exceeded

        Time complexity: O(n^2 * k)
            k -> max length of combine words
        Space complexity: O(n^2 + k)
            there will be n * (n - 1) possible combinations with
            the extra space for the combine strings
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> pairs = new LinkedList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i != j) {
                    if (isPalindrome(words[i] + words[j])) {
                        pairs.add(List.of(i, j));
                    }
                }
            }
        }
        return pairs;
    }

    public boolean isPalindrome(String str) {
        int len = str.length();
        if (len < 2) {
            return true;
        }
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
