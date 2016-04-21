/*
308 Range Sum Query 2D

Given a 2D matrix matrix, find the sum of the elements inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).

Range Sum Query 2D
The above rectangle (with the red border) is defined by (row1, col1) = (2, 1) and (row2, col2) = (4, 3), which contains sum = 8.

Example:

Given matrix = [
  [3, 0, 1, 4, 2],
  [5, 6, 3, 2, 1],
  [1, 2, 0, 1, 5],
  [4, 1, 0, 1, 7],
  [1, 0, 3, 0, 5]
]

sumRegion(2, 1, 4, 3) -> 8
update(3, 2, 2)
sumRegion(2, 1, 4, 3) -> 10
 

Note:

The matrix is only modifiable by the update function.
You may assume the number of calls to update and sumRegion function is distributed evenly.
You may assume that row1 ≤ row2 and col1 ≤ col2.
*/

public class NumMatrix {
    Bit2D bit2D;
    
    public NumMatrix(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length, n = matrix[0].length;
        bit2D = new Bit2D(m, n);
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                update(i, j, matrix[i][j]);
            }
        }
    }

    public void update(int row, int col, int val) {
        if(bit2D == null) return;
        int diff = val - sumRegion(row, col, row, col);
        bit2D.update(row + 1, col + 1, diff);
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if(bit2D == null) return 0;
        return bit2D.sum(row2 + 1, col2 + 1) + bit2D.sum(row1, col1)
                - bit2D.sum(row1, col2 + 1) - bit2D.sum(row2 + 1, col1);
    }
    
    class Bit2D{
		int m, n;
		int[][] tree;
		public Bit2D(int m, int n){
			this.m = m + 1;
			this.n = n + 1;
			tree = new int[this.m][this.n];
		}
		
		private int lowBit(int idx){
			return idx & (-idx);
		}
		
		public void update(int i, int j, int diff){
		    for(int r = i; 0 < r && r < m; r += lowBit(r)){
		        for(int c = j; 0 < c && c < n; c += lowBit(c)){
		            tree[r][c] += diff;
		        }
		    }
		}
		
		public int sum(int i, int j){
			int sum = 0;
			for(int r = i; 0 < r && r < m; r -= lowBit(r)){
			    for(int c = j; 0 < c && c < n; c -= lowBit(c)){
			        sum += tree[r][c];
			    }
			}
			return sum;
		}				
	}
}