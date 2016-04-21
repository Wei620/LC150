/*
322 Coin Change

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1. 

Example 1:
 coins = [1, 2, 5], amount = 11
 return 3 (11 = 5 + 5 + 1) 

Example 2:
 coins = [2], amount = 3
 return -1. 

Note:
 You may assume that you have an infinite number of each kind of coin. 

*/

/*****
1. 和urgly很像。因子不断运算。用一样的方法， 只能判断amount能否组成。
2. dp[i], 组成i需要的最小硬币数。 
		dp[0] = 0. 形式统一。
		剩下 dp[amount + 1] MAX_VALUE - 1. 
		扫描一次dp[i], 用dp[i]+1去更新dp[i+coin[j]].	
			每个dp[i]只加一次， 不会溢出。 且还要去min，所以如果dp[i]==INF, 更新完dp[i+coin[j]]==INF
		
*****/

public class Solution {
    public int coinChange(int[] coins, int amount) {
        int dp[] = new int[amount + 1];
        final int INF = 0x7ffffffe;
        for (int i = 1; i <= amount; i++) dp[i] = INF; // dp[0] = 0;
        for (int i = 0; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i + coins[j] <= amount) // calculate dp[x], x > i
                    dp[i + coins[j]] = Math.min(dp[i + coins[j]], dp[i] + 1);
            }
        }
        return dp[amount] == INF ? -1 : dp[amount];
    }
}


/*
dp，设dp[i] 为兑换目标i最少的硬币数。

则有：dp[i + coins[j] ] = min(dp[i + coins[j] ] , dp[i] + 1）

说白了就是用当前的硬币能组合成啥，取最小
*/