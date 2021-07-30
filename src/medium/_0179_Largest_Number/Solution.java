package medium._0179_Largest_Number;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.largestNumber(new int[] {10, 2})); // 210
        System.out.println(s.largestNumber(new int[] {3,30,34,5,9})); // 9534330
        System.out.println(s.largestNumber(new int[] {1})); // 1
        System.out.println(s.largestNumber(new int[] {10})); // 10
        System.out.println(s.largestNumber(new int[] {34323, 3432})); // 343234323
        System.out.println(s.largestNumber(new int[] {999999991, 9})); // 9999999991
        System.out.println(s.largestNumber(new int[] {0, 0})); // 0
    }

    /*  Time complexity: O(n*log(n))
            sort the array
        Space complexity: O(n)
            spaced for the extra sorted array
     */
    public String largestNumber(int[] nums) {
        if (nums.length <= 1) {
            return nums[0] + "";
        }

        Integer[] updatedNums = Arrays.stream(nums).boxed().toArray(Integer[]::new);
        Arrays.sort(updatedNums, (i1, i2) -> {
            String inOrderStr = i1.toString() + i2.toString();
            String reverseStr = i2.toString() + i1.toString();
            long inOrderInt = Long.parseLong(inOrderStr);
            long reverseInt = Long.parseLong(reverseStr);
            return Long.compare(reverseInt, inOrderInt);
        });

        StringBuilder result = new StringBuilder();
        for (int num : updatedNums) {
            if (num == 0 && result.toString().equals("0")) {
                continue;
            }
            result.append(num);
        }

        return result.toString();
    }
}
