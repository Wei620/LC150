/*
221 Maximal Square

Given a 2D binary matrix filled with 0's and 1's, find the largest square containing all 1's and return its area. 

For example, given the following matrix: 
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Return 4. 
*/

/*****
1. （i， j） = 1
   正方形S(i-1,j-1)限定了S（i，j）正方形的左上角，在左上半周至少有一个0。
   dp（i，j) <= dp(i-1, j-1) + 1;
   所以 dp（i-1,j） 决定S（i，j)的高， min(dp(i-1, j-1), dp(i-1, j)) + 1
       dp(i,j-1)            宽， min(dp(i-1, j-1), dp(i, j-1) + 1
2. 要求正方形，返回min（高，宽）
3. 初始行列值为0. [n+1]维, [0] = 0.

411.
1. 本题要square里全都是1, 而不是用squre包含全图所有的1.
2. d(i,j) the max length of the rect whose right-bottom is at(i, j) and all element in the rect is 1.
   if not exist, set the value to 0.
*****/

public int maximalSquare(char[][] a) {
	if (a == null || a.length == 0 || a[0].length == 0)
	return 0;
	int max = 0, n = a.length, m = a[0].length;
	// dp(i, j) represents the length of the square
	// whose lower-right corner is located at (i, j)
	// dp(i, j) = min{ dp(i-1, j-1), dp(i-1, j), dp(i, j-1) }
	int[][] dp = new int[n + 1][m + 1];
	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			if (a[i - 1][j - 1] == '1') {
				dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]
				)) + 1;
				max = Math.max(max, dp[i][j]);
			}
		}
	}
	// return the area
	return max * max;
}