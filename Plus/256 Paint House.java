/*
256 Paint House

There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.
The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
Note:
All costs are positive integers.
*/

// update cost to the total cost.
// costs[i][j] the minimal total cost (inclusive) when ith house is painted to color j.

/*****
1.	在cost矩阵in-place累加， 变成i房子j颜色的总花费。 这样返回 min（cost[n][])
2.	根据当前颜色， 排除前一个房子的颜色。

401
1.  复用cost矩阵， 注意是累加。
*****/
public class Solution {
	public int minCost(int[][] costs) {
		if(costs==null||costs.length==0){
			return 0;
		}
		for(int i=1; i<costs.length; i++){
			costs[i][0] += Math.min(costs[i-1][1],costs[i-1][2]);
			costs[i][1] += Math.min(costs[i-1][0],costs[i-1][2]);
			costs[i][2] += Math.min(costs[i-1][1],costs[i-1][0]);
		}
		int n = costs.length-1;
		return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
	}
}