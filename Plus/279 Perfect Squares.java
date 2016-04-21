/*
279	Perfect Squares

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.
*/

/* ****
1. 求个数用dp.
2. dp[i] 至少要多少个的平方数加出i. 
3. min(dp[i - j*j] + 1(j*j这个平方数)), 1 <= j*j <= i, for条件完美
   dp[0] to dp[i-1] 均已知. 

补充. Lagrange's four-square theorem 说明每个正整数均可表示为4个整数的平方和

303
1. Lagrange, 推论, 大数 = 小数 + 平方数
2. dp[1] = 1, 1 <= 平方数 < i比较好.
2. 注意j的循环条件.

411
1. 个数用dp,列举所有情况 dfs-bt
2. 求 dp[i], 已知 dp[0] to dp[i-1], 向下轮询
        dp[i - j^2], 1 < j^2 <= i.

*****/

int numSquares(int n){
    if (n <= 0){
        return 0;
    }
    // dp[i] = the least number of perfect square numbers which sum up to i. Note that dp[0] is 0.
    int[] dp = new int[n + 1];
    Arrays.fill(dp, Integer.MAX_VALUE);
    dp[0] = 0;
    for (int i = 1; i <= n; i++){}
        // For each i, it must be the sum of some number (i - j*j) and
        // a perfect square number (j*j).
        for (int j = 1; j*j <= i; j++){// j is a square number
            dp[i] = min(dp[i], dp[i - j*j] + 1);
        }
    }
    return dp[n];
}
