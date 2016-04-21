/*
304 Range Sum Query 2D - Immutable

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
sumRegion(1, 1, 2, 2) -> 11
sumRegion(1, 2, 2, 4) -> 12



Note:

1.You may assume that the matrix does not change.
2.There are many calls to sumRegion function.
3.You may assume that row1 ≤ row2 and col1 ≤ col2.

*/
/* -----
1. 初始化 拓展求 和阵. dp[i][j] 1-based, 访问matrix(0-based), 坐标-1.
2. 给出的对角点,转成,左上-右下.
   dp[][] inclusive, (iMin, jMin)要退后一个像素.
   
411
1. 一像素和模型
2. 查模型
3. 模型里小矩形退后1
4. dp存在i-1所以 int[m+1][n+1]
5. sumRegion 0-based convert to 1-base.
******/

private int[][] dp; // inclusive
public NumMatrix(int[][] matrix) {
	if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

	int m = matrix.length;
	int n = matrix[0].length;
	dp = new int[m + 1][n + 1];
	for(int i = 1; i <= m; i++){
		for(int j = 1; j <= n; j++){
			dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1] ;
		}
	}
}

public int sumRegion(int row1, int col1, int row2, int col2) {
	int iMin = Math.min(row1, row2) + 1; //1-based
	int iMax = Math.max(row1, row2) + 1;
	int jMin = Math.min(col1, col2) + 1;
	int jMax = Math.max(col1, col2) + 1;
	return dp[iMax][jMax] - dp[iMax][jMin-1] - dp[iMin-1][jMax] + dp[iMin-1][jMin-1];
}