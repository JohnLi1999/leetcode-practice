package medium._0056_Merge_Intervals.round_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] intervals;

        intervals = new int[][] {{1,3},{2,6},{8,10},{15,18}};
        System.out.println(Arrays.deepToString(s.merge(intervals))); // [[1,6],[8,10],[15,18]]

        intervals = new int[][] {{1,4},{4,5}};
        System.out.println(Arrays.deepToString(s.merge(intervals))); // [[1,5]]
    }

    /*  Sort with the starting time
        Time complexity: O(n*log(n))
        Space complexity: O(n)
     */
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

        List<int[]> results = new ArrayList<>();
        int[] prevInterval = null;
        for (int[] interval : intervals) {
            if (prevInterval == null || interval[0] > prevInterval[1]) {
                results.add(interval);
                prevInterval = interval;
            } else if (interval[1] > prevInterval[1]) {
                prevInterval[1] = interval[1];
                results.get(results.size() - 1)[1] = interval[1];
            }
        }

        int[][] mergedIntervals = new int[results.size()][2];

        for (int i = 0; i < results.size(); i++) {
            mergedIntervals[i] = results.get(i);
        }

        return mergedIntervals;
    }
}
