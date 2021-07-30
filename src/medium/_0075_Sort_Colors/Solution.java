package medium._0075_Sort_Colors;

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums;

        nums = new int[] {2,0,2,1,1,0};
        s.sortColors_Improved(nums);
        System.out.println(Arrays.toString(nums)); // [0,0,1,1,2,2]
        nums = new int[] {2,0,1};
        s.sortColors_Improved(nums);
        System.out.println(Arrays.toString(nums)); // [0,1,2]
        nums = new int[] {0};
        s.sortColors_Improved(nums);
        System.out.println(Arrays.toString(nums)); // [0]
        nums = new int[] {1};
        s.sortColors_Improved(nums);
        System.out.println(Arrays.toString(nums)); // [1]
        nums = new int[] {0,1,0};
        s.sortColors_Improved(nums);
        System.out.println(Arrays.toString(nums)); // [0,0,1]
        nums = new int[] {0,0,1,1,1,2,2,0,0,2};
        s.sortColors_Improved(nums);
        System.out.println(Arrays.toString(nums)); // [0,0,0,0,1,1,1,2,2,2]

        nums = new int[] {2,0,2,1,1,0};
        s.sortColors_My_Way(nums);
        System.out.println(Arrays.toString(nums)); // [0,0,1,1,2,2]
        nums = new int[] {2,0,1};
        s.sortColors_My_Way(nums);
        System.out.println(Arrays.toString(nums)); // [0,1,2]
        nums = new int[] {0};
        s.sortColors_My_Way(nums);
        System.out.println(Arrays.toString(nums)); // [0]
        nums = new int[] {1};
        s.sortColors_My_Way(nums);
        System.out.println(Arrays.toString(nums)); // [1]
        nums = new int[] {0,1,0};
        s.sortColors_My_Way(nums);
        System.out.println(Arrays.toString(nums)); // [0,0,1]
        nums = new int[] {0,0,1,1,1,2,2,0,0,2};
        s.sortColors_My_Way(nums);
        System.out.println(Arrays.toString(nums)); // [0,0,0,0,1,1,1,2,2,2]
    }

    /*  Time complexity: O(n)
        Space complexity: O(1)
     */
    public void sortColors_Improved(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int i = 0;
        /*  Be careful with <=
                   end
            ...  1  1  2  2  ...
                      curr
            end > curr is the breaking case,
            so it is i <= end
         */
        while (i <= end) {
            if (nums[i] == 0) {
                swap(nums, i++, start++);
            } else if (nums[i] == 2) {
                swap(nums, i, end--);
            } else {
                i++;
            }
        }
    }

    /*  Time complexity: O(n)
        Space complexity: O(1)
     */
    public void sortColors_My_Way(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int i = 0;
        while (i < nums.length) {
            if (nums[i] == 0) {
                if (i != start) {
                    swap(nums, i, start);
                }
                i++;
                start++;
            } else if (nums[i] == 2) {
                if (i > end) {
                    break;
                } else {
                    swap(nums, i, end--);
                }
            } else {
                i++;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
