/**
 * Given an integer n, generate a square matrix filled with elements from 1 to
 * n^2 in spiral order.
 * 
 * For example,
 * Given n = 3,
 * 
 * You should return the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 8, 9, 4 ],
 *  [ 7, 6, 5 ]
 * ]
 * 
 * Tags: Array
 */
class SpiralMatrix2 {
    public static void main(String[] args) {
        int[][] mat = generateMatrix(5);
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        
    }
	
	/*****
	1. 计算每层两端。
	2. 中心节点的处理和I统一。
	*****/
    
    /**
     * Track current level
     * Work level by level toward center
     */
    public static int[][] generateMatrix(int n) {
        if (n <= 0) return new int[0][0];
        int[][] ans = new int[n][n];
        int num = 1;

        for(int lv = 0; 2 * lv < n; lv++) {
            int min = lv, max = n - 1 - lv;

			
			for(int j = min; j < max; j++) ans[min][j] = num++;
			for(int i = min; i < max; i++) ans[i][max] = num++;
			
			if(min == max){
				ans[min][min] = num;
				break;
			}// same as I			
			
			for(int j = max; j > min; j--) ans[max][j] = num++;
			for(int i = max; i > min; i--) ans[i][min] = num++;
        }		
        return ans;
    }
    
    /**
     * use startR, endR, startC, endC to mark the range
     * update relative range whenever finish filling up a row or column
     */
    public static int[][] generateMatrixB(int n) {
        if (n <= 0) return new int[0][0];
        int[][] ans = new int[n][n];
        int i = 1;
        int startR = 0;
        int startC = 0;
        int endR = n - 1;
        int endC = n - 1;
        while (startR <= endR && startC <= endC) {
            for (int j = startC; j <= endC; j++) ans[startR][j] = i++;
            startR++;
            for (int j = startR; j <= endR; j++) ans[j][endC] = i++;
            endC--;
            for (int j = endC; j >= startC; j--) ans[endR][j] = i++;
            endR--;
            for (int j = endR; j >= startR; j--) ans[j][startC] = i++;
            startC++;
        }
        return ans;
    }
}
