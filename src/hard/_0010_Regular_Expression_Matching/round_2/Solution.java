package hard._0010_Regular_Expression_Matching.round_2;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();

        System.out.println(s.isMatch_Better("aa", "a")); // false
        System.out.println(s.isMatch_Better("aa", "a*")); // true
        System.out.println(s.isMatch_Better("ab", ".*")); // true
        System.out.println(s.isMatch_Better("aab", "c*a*b")); // true
        System.out.println(s.isMatch_Better("mississippi", "mis*is*p*.")); // false
        System.out.println(s.isMatch_Better("aaa", "ab*ac*a")); // true
        System.out.println(s.isMatch_Better("mississippi", "mis*is*ip*.")); // true
        System.out.println(s.isMatch_Better("aasdfasdf", "aasdf.*asdf")); // true
        System.out.println(s.isMatch_Better("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s")); // true

//        System.out.println(s.isMatch_My_Way("aa", "a")); // false
//        System.out.println(s.isMatch_My_Way("aa", "a*")); // true
//        System.out.println(s.isMatch_My_Way("ab", ".*")); // true
//        System.out.println(s.isMatch_My_Way("aab", "c*a*b")); // true
//        System.out.println(s.isMatch_My_Way("mississippi", "mis*is*p*.")); // false
//        System.out.println(s.isMatch_My_Way("aaa", "ab*ac*a")); // true
//        System.out.println(s.isMatch_My_Way("mississippi", "mis*is*ip*.")); // true
//        System.out.println(s.isMatch_My_Way("aasdfasdf", "aasdf.*asdf")); // true
//        System.out.println(s.isMatch_My_Way("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s")); // true
    }

    /*  Right to Left -> traverse the text and pattern from end to start
        Time complexity: O(m * n)
            m -> s.length
            n -> p.length
        Space complexity: O(m * n)
     */
    public boolean isMatch_Better(String s, String p) {
        /*
            p is empty => s is empty

            firstMatch = s is not empty && (s[0] == p[0] || p[0] == '.')

            if (p.length >= 2 && p[1] == '*')
                => match(s, p[2...]) || (firstMatch && match(s[1...], p))
            else
                => firstMatch && match(s[1...], p[1...])

            Explanation:
                1. Be careful with the direction of the traversal.
                   We built the results at the end first, then traversal from the right to left
                   e.g. ? -> unknown, ! -> calculated already
                                current
                                   |
                                   v
                   text    [ ? ? ? ? ! ! ! ! ! !]
                   pattern [ ? ? ? ? ! ! ! ! ! !]
                             <-----------------
                2. FirstMatch has three conditions
                    2.1 The text must not be empty
                    2.2 The elements from the text and pattern are the same OR
                        the element from pattern is a wildcard (.)
                3. If the next element in pattern is a wildcard (*)
                   we have two options
                    3.1 Skip this wildcard (the wildcard matches 0 element)
                            So the text does not change, but the pattern will move forward by 2 elements
                            firstMatch is not needed in this case
                    3.2 Consume the text (the wildcard matches multiple this specific element)
                            If the firstMatch is true, we can consider the wildcard represents
                            multiple this matched element.
                            So the text move forward by 1 element, but the pattern does not change
                4. If the next element in patter is not (*) wildcard
                    Then the first elements must match, and both text and pattern need to consume 1 element.
                    So, both text and pattern move forward by 1 element.
         */

        char[] text = s.toCharArray();
        char[] pattern = p.toCharArray();
        boolean[][] dp = new boolean[text.length + 1][pattern.length + 1];

        for (int i = text.length; i >= 0; i--) {
            for (int j = pattern.length - 1; j >= 0; j-- ) {
                if (j == pattern.length) {
                    dp[i][j] = i == text.length;
                    continue;
                }

                boolean firstMatch = i != text.length && (text[i] == pattern[j] || pattern[j] == '.');

                if (j <= pattern.length - 2 && pattern[j + 1] == '*') {
                    dp[i][j] = dp[i][j + 2] || (firstMatch && dp[i + 1][j]);
                } else {
                    dp[i][j] = firstMatch && dp[i + 1][j + 1];
                }
            }
        }

        return dp[0][0];
    }

    /*  Left to Right -> traverse the text and pattern from start to end
        Time complexity: O(m * n)
            m -> s.length
            n -> p.length
        Space complexity: O(m * n)
     */
    private boolean[][] dp;

    public boolean isMatch_My_Way(String s, String p) {
        if (p.equals(".*")) {
            return true;
        }

        int m = s.length();
        int n = p.length();
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        dp = new boolean[m + 1][n + 1];
        init(m, n, p);

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (sArr[i - 1] == pArr[j - 1]) {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }

                if (pArr[j - 1] == '.') {
                    dp[i][j] = dp[i - 1][j - 1];
                    continue;
                }

                if (pArr[j - 1] == '*') {
                    if (pArr[j - 2] == '.' || sArr[i - 1] == pArr[j - 2]) {
                        dp[i][j] = dp[i - 1][j - 2] || dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                    continue;
                }

                dp[i][j] = false;
            }
        }

//        Arrays.stream(dp).forEach(arr -> System.out.println(Arrays.toString(arr)));

        return dp[m][n];
    }

    public void init(int m, int n, String p) {

        dp[0][0] = true;

        for (int i = 1; i < m + 1; i++) {
            dp[i][0] = false;
        }

        boolean guaranteedFalse = false;
        for (int j = 1; j < n + 1; j++) {
            if (guaranteedFalse) {
                dp[0][j] = false;
            } else {
                if (p.charAt(j - 1) == '*') {
                    dp[0][j] = true;
                } else {
                    if (j == n) {
                        dp[0][j] = false;
                    } else if (p.charAt(j) != '*') {
                        guaranteedFalse = true;
                        dp[0][j] = false;
                    }
                }
            }
        }
    }
}
