/*
198 House Robber

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

*/

// 411
public class Solution {
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int currRub = nums[0], currNotRub = 0;
        for(int i = 1; i < nums.length; i++){
            int prevRub = currRub, prevNotRub = currNotRub;
            currRub = prevNotRub + nums[i];
            currNotRub = Math.max(prevRub, prevNotRub);
        }
        return Math.max(currRub, currNotRub);
    }
}

/* ****
1. 两态， dp[n+1][2]
2. 是否强ith，仅决定于（i-1）th.
3. 不抢ith 不是说一定要抢 （i-1)th. 要比较。
4. 1-based可解决首累加问题.dp[0][] = 0.
*****/

public int rob(int[] num) {
	int[][] dp = new int[num.length + 1][2];
	for (int i = 1; i <= num.length; i++) {
		dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
		dp[i][1] = num[i - 1] + dp[i - 1][0]; // i is 1-based
	}
	return Math.max(dp[num.length][0], dp[num.length][1]);
}

//dp[i][1] means we rob the current house and dp[i][0] means we don't,
//so it is easy to convert this to O(1) space
public int rob(int[] num) {
	int prevNo = 0;
	int prevYes = 0;
	for (int n : num) {
		int temp = prevNo;
		prevNo = Math.max(prevNo, prevYes); // was currNo actually. update prev directly.
		prevYes = n + temp;
	}
	return Math.max(prevNo, prevYes);
}
