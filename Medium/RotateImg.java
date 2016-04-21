import java.util.*;

/**
 * You are given an n x n 2D matrix representing an image.
 * 
 * Rotate the image by 90 degrees (clockwise).
 * 
 * Follow up:
 * Could you do this in-place?
 * 
 * Tags: Array
 */
class RotateImg {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2}, {4, 3}};
        new RotateImg().rotate(matrix);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
    
	/* ***
	1. 以四角为基准算段内偏移。
	*****/
	
    /**
     * Get the length of matrix
     * Do level by level, each level edge by edge
     * In-place solutions overwrites original matrix
     */
	
    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int n = matrix.length;
        for(int lv = 0; 2 * lv < n; lv++){
            int min = lv, max = n - 1 - lv;
            for(int offset = 0; offset < max - min; offset++){
                int tmp = matrix[min][min + offset];
                matrix[min][min + offset] = matrix[max - offset][min];
                matrix[max - offset][min] = matrix[max][max - offset];
                matrix[max][max - offset] = matrix[min + offset][max];
                matrix[min + offset][max] = tmp;   
            }
        }
    }
}
