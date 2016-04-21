/*
312 Burst Balloons

Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent. 

Find the maximum coins you can collect by bursting the balloons wisely. 

Note: 
 (1) You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
 (2) 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100 

Example: 

Given [3, 1, 5, 8] 

Return 167 
    nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
   coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167


*/

/*-----------
1. n长扩至n+2, A = new int[n+2]. 
	A[0] = 1, A[n+1] = 1.原来n长所有元素都有两个邻居.
2. dp[l][r] 表示在(l,r)里破气球得到的最多金币. dp = new int[n+2][n+2], 返dp[1][n]
	dp[l][r] = max(dp[l][i] + A[l]*A[i]*A[r] + dp[i][r])， i in [l+1, r-1]
3. 要先算dp[l][i-1], dp[i+1][r], 这些的特点 r - l较短
	对于dp[l][r] r - l >= 2, 这样(l,r)至少有一个气球.
		最后返回dp[0][n+1]

	令 r = l + k. 
	初始 dp = 0.
	for(k = 2; k < n +1; k++)
		for(l = 0; l+k < n+2; l++)
			r = l + k
			for(i = l+1; i <= r-1; i++)
				dp[l][r] = max(dp[l][r], num[l]num[i]num[r] + dp[l][i] + dp[i][r]);

313
1. 注释和题解是一回事, 注释 n = 题解 iNums.length.
2. dp[l][i]是气球爆在(l,i)区间的最大值, 完后只有气球l,i本身并没有爆.   

-----------*/
public int maxCoins(int[] iNums) {
	int[] nums = new int[iNums.length + 2];
	int n = 1;
	for (int x : iNums) if (x > 0) nums[n++] = x;
	nums[0] = nums[n++] = 1; //num[0] = 1, num[iNums.length + 1] = 1
	
	int[][] dp = new int[n][n];// dp[left][right] means the maxcoins for the range (left, right)
	for (int k = 2; k < n; ++k){
		for (int left = 0; left + k < n; ++left) {// right < n
			int right = left + k;
			for (int i = left + 1; i < right; ++i)// try any posibble i in (left, right)
				dp[left][right] = Math.max(dp[left][right], nums[left] * nums[i] * nums[right] + dp[left][i] + dp[i][right]);// small span at any point(left) has been calculated.
		}
	}
	return dp[0][n - 1];
}