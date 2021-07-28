package hard._0336_Palindrome_Pairs.round_2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution_Trie {

    public static void main(String[] args) {
        Solution_Trie s = new Solution_Trie();
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
        List<List<Integer>> pairs = new ArrayList<>();
        TrieNode root = new TrieNode();

        // Build the Trie
        for (int i = 0; i < words.length; i++) {
            String word = new StringBuilder(words[i]).reverse().toString();
            TrieNode current = root;

            for (int j = 0; j < word.length(); j++) {
                //                | ------ current word ------|
                // [target prefix][palindrome + reverse prefix]
                if (isPalindrome(word, j, word.length() - 1)) {
                    current.indices.add(i);
                }

                char c = word.charAt(j);
                if (!current.children.containsKey(c)) {
                    current.children.put(word.charAt(j), new TrieNode());
                }
                current = current.children.get(c);
            }

            // Record the word's index
            current.index = i;
        }

        // Search for palindrome pairs

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            TrieNode current = root;

            for (int j = 0; j < word.length(); j++) {
                // Case 3
                // | ------ current word ------|
                // [reverse suffix + palindrome][target suffix]
                if (current.index != -1 && isPalindrome(word, j, word.length() - 1)) {
                    pairs.add(List.of(i, current.index));
                }

                char c = word.charAt(j);
                current = current.children.get(c);
                if (current == null) {
                    break;
                }
            }

            // No more matching characters
            if (current == null) {
                continue;
            }

            // Case 1
            // [current word][reverse of current word]
            if (current.index != -1 && current.index != i) {
                pairs.add(List.of(i, current.index));
            }

            // Case 2
            // |current word|
            // [target prefix][palindrome + reverse of prefix]
            for (int index : current.indices) {
                pairs.add(List.of(i, index));
            }
        }

        return pairs;
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

class TrieNode {
    int index = -1;
    ArrayList<Integer> indices = new ArrayList<>();
    HashMap<Character, TrieNode> children = new HashMap<>();
}
