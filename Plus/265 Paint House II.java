/*
265 Paint House II

Q:
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
Note:
All costs are positive integers.
Follow up:
Could you solve it in O(nk) runtime?
A:
DP
每次记录之前一行，最小的value和index，以及 secondMinimum value;
*/

/*****
1. K种颜色。
2. 遍历所有房子， 每轮累加计算， 当前房子刷颜色j是的最小总cost。
	如果颜色j和上轮最小撞色， 那么上轮只能选总预算第二便宜的颜色。 否则上轮选总预算最便宜。
3. 每轮要选出并记下最便宜， 和第二便宜的颜色索引。怎么选，很有意思。”退位“更新。
4. 返回 costs[n-1][min1]

*****/

public int minCostII(int[][] costs) {
	if (costs == null || costs.length == 0) return 0;
	int n = costs.length, k = costs[0].length;
	// min1 is the index of the 1st-smallest cost till previous house
	// min2 is the index of the 2nd-smallest cost till previous house
	int min1 = -1, min2 = -1;
	for (int i = 0; i < n; i++) {
		int last1 = min1, last2 = min2;
		min1 = -1; min2 = -1;
		for (int j = 0; j < k; j++) {
			if (j != last1) {
				// current color j is different to last min1
				costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
			} else {
				costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
			}
			// find the indices of 1st and 2nd smallest cost of painting current house i
			if (min1 < 0 || costs[i][j] < costs[i][min1]) {
				min2 = min1; min1 = j;
			} else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
				min2 = j;
			}
		}
	}
	return costs[n - 1][min1];
}