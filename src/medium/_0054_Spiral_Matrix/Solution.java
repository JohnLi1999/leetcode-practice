package medium._0054_Spiral_Matrix;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] matrix;
        matrix = new int[][] {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(s.spiralOrder(matrix)); // [1,2,3,6,9,8,7,4,5]
        matrix = new int[][] {{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        System.out.println(s.spiralOrder(matrix)); // [1,2,3,4,8,12,11,10,9,5,6,7]
        matrix = new int[][] {{1}};
        System.out.println(s.spiralOrder(matrix)); // [1]
        matrix = new int[][] {{1,2,3}};
        System.out.println(s.spiralOrder(matrix)); // [1,2,3]
        matrix = new int[][] {{1,2},{3,4}};
        System.out.println(s.spiralOrder(matrix)); // [1,2,4,3]
        matrix = new int[][] {{7},{9},{6}};
        System.out.println(s.spiralOrder(matrix)); // [7,9,6]
        matrix = new int[][] {{2,5,8},{4,0,-1}};
        System.out.println(s.spiralOrder(matrix)); // [2,5,8,-1,0,4]
        matrix = new int[][] {{1},{2},{3},{4},{5},{6},{7},{8},{9},{10}};
        System.out.println(s.spiralOrder(matrix)); // [1,2,3,4,5,6,7,8,9,10]
        matrix = new int[][] {{1,11},{2,12},{3,13},{4,14},{5,15},{6,16},{7,17},{8,18},{9,19},{10,20}};
        System.out.println(s.spiralOrder(matrix)); // [1,11,12,13,14,15,16,17,18,19,20,10,9,8,7,6,5,4,3,2]
    }

    /*  Time complexity: O(m * n)
            m -> matrix's height
            n -> matrix's width
        Space complexity: O(m * n)
     */
    private List<Integer> nums;

    public List<Integer> spiralOrder(int[][] matrix) {
        nums = new ArrayList<>();

        int minLevels = Math.min(matrix.length, matrix[0].length);
        int levels = minLevels % 2 == 0 ? minLevels / 2 - 1 : minLevels / 2;
        for (int row = 0; row <= levels; row++) {
            addBorder(matrix, row);
        }
        return nums;
    }

    public void addBorder(int[][] matrix, int level) {
        /*  1  2  3  4  5
            6  7  8  9  10
            11 12 13 14 15
            16 17 18 19 20
            21 22 23 24 25
         */

        // top
        int topEnd = matrix[level].length - level - 1;
        for (int col = level; col <= topEnd; col++) {
            nums.add(matrix[level][col]);
        }

        // right
        int rightCol = matrix[level].length - level - 1;
        int rightStart = level + 1;
        int rightEnd = matrix.length - level - 2;
        if (rightCol >= 0 && rightCol < matrix[level].length) {
            for (int row = rightStart; row <= rightEnd; row++) {
                nums.add(matrix[row][rightCol]);
            }
        }

        // bottom
        int bottomRow = matrix.length - level - 1;
        int bottomStart = matrix[level].length - level - 1;
        if (bottomRow != level) {
            for (int col = bottomStart; col >= level; col--) {
                nums.add(matrix[bottomRow][col]);
            }
        }

        // left
        int leftStart = matrix.length - level - 2;
        int leftEnd = level + 1;
        if (level != rightCol && level < matrix[level].length) {
            for (int row = leftStart; row >= leftEnd; row--) {
                nums.add(matrix[row][level]);
            }
        }
    }
}
