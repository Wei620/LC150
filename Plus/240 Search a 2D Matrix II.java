/*
240 Search a 2D Matrix II

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:


•Integers in each row are sorted in ascending from left to right.
•Integers in each column are sorted in ascending from top to bottom.


For example,

Consider the following matrix: 
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]


Given target = 5, return true.

Given target = 20, return false.

*/

/*****
1. BS更快
2. nums[lo-1] < target. 修改bs返回lo-1. lo-1 = n - 1, target大于所有数。
3. nums[0][]内bs， nums[][lo-1]内bs， 找不到就返-1.

411
1. 左下方向遍历, 不能BS.
2. i, j的边界条件
*****/
// 411
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while(i < m && j >= 0){
            if(target == matrix[i][j]) return true;
            else if(target > matrix[i][j]) i++;
            else if(target < matrix[i][j]) j--;
        }
        return false;
    }
}

/*
We start search the matrix from top right corner, initialize the current position to
top right corner, if the target is greater than the value in current position, then the
target can not be in entire row of current position because the row is sorted, if the
target is less than the value in current position, then the target can not in the entire
column because the column is sorted too. We can rule out one row or one column
each time, so the time complexity is O(m+n).
*/
