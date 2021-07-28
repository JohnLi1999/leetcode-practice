package hard._0336_Palindrome_Pairs.round_2;

import java.util.*;

public class Solution_HashMap {

    public static void main(String[] args) {
        Solution_HashMap s = new Solution_HashMap();
        String[] words;

        words = new String[] {"abcd","dcba","lls","s","sssll"};
        System.out.println(s.palindromePairs(words)); // [[0,1],[1,0],[3,2],[2,4]]
        words = new String[] {"bat","tab","cat"};
        System.out.println(s.palindromePairs(words)); // [[0,1],[1,0]]
        words = new String[] {"a",""};
        System.out.println(s.palindromePairs(words)); // [[0,1],[1,0]]
    }

    /*  Time complexity: O(n * k^2)
            k -> max length of the word in words
        Space complexity: O((k + n)^2) from sample solution
     */
    public List<List<Integer>> palindromePairs(String[] words) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        List<List<Integer>> pairs = new ArrayList<>();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            // Case 1
            // [current word][reverse of current word]
            String reverse = new StringBuilder(word).reverse().toString();
            if (map.containsKey(reverse) && map.get(reverse) != i) {
                pairs.add(List.of(i, map.get(reverse)));
            }

            // Case 2
            // [target prefix][palindrome + reverse of prefix]
            for (String prefix : validPrefixes(word)) {
                if (map.containsKey(prefix)) {
                    pairs.add(List.of(map.get(prefix), i));
                }
            }

            // Case 3
            // [reverse of suffix + palindrome][target suffix]
            for (String suffix : validSuffixes(word)) {
                if (map.containsKey(suffix)) {
                    pairs.add(List.of(i, map.get(suffix)));
                }
            }
        }

        return pairs;
    }


    public ArrayList<String> validPrefixes(String word) {
        // This is different from the sample solution !!!
        // In this case, current word will appear at the end of the combined word
        // So, we are searching for valid prefixes for it

        // [target prefix][palindrome + reverse of prefix]

        ArrayList<String> prefixes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (isPalindrome(word, 0, i)) {
                prefixes.add(new StringBuilder(word.substring(i + 1)).reverse().toString());
            }
        }
        return prefixes;
    }

    public ArrayList<String> validSuffixes(String word) {
        // This is different from the sample solution !!!
        // In this case, current word will appear at the beginning of the combined word
        // So, we are searching for valid suffixes for it

        // [reverse of suffix + palindrome][target suffix]

        ArrayList<String> suffixes = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            if (isPalindrome(word, i, word.length() - 1)) {
                suffixes.add(new StringBuilder(word.substring(0, i)).reverse().toString());
            }
        }
        return suffixes;
    }

    public boolean isPalindrome(String word, int start, int end) {
        while (start < end) {
            if (word.charAt(start++) != word.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
