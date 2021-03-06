/**
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * Design an algorithm to find the maximum profit. You may complete at most 
 * <strong>two</strong> transactions.
 * 
 * Note:
 * You may not engage in multiple transactions at the same time (ie, you must
 * sell the stock before you buy again).
 * 
 * Tags: Array, DP
 */
 /* ****
 1. maxBy, maxSince 都是比较更新。
 2. valley和peak的初始值
 3. maxProfit 的初始值
 *****/
 
 //410
 public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int n = prices.length;
        
        // calculate max profit by i.
        int valley = prices[0];
        int[] maxBy = new int[n];
        for(int i = 1; i < n; i++){
            if(prices[i] < valley) valley = prices[i];
            maxBy[i] = Math.max(maxBy[i -1], prices[i] - valley);
        }
        
        // calculate max profit since i
        int peak = prices[n - 1], maxProfit = maxBy[n - 1];
        int[] maxSince = new int[n];
        for(int i = n - 2; i >= 0; i--){
            if(prices[i] > peak) peak = prices[i];
            maxSince[i] = Math.max(maxSince[i+1], peak - prices[i]);
            maxProfit = Math.max(maxProfit, maxBy[i] + maxSince[i]);
        }
        return maxProfit;
    }
}
class BestTimeStock3 {
    public static void main(String[] args) {
        BestTimeStock3 b = new BestTimeStock3();
        int[] prices = { 6, 1, 3, 2, 4, 7, 6, 10, 15 };
        System.out.println(b.maxProfit(prices));
    }
    
    /**
     * Goes forward to build single transaction max profit
     * Then goes backward to build max since day i profit
     * Find the max of the sum of these two
     */
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        if (prices == null || prices.length < 2) return maxProfit;
        int len = prices.length;
        int[] maxBy = new int[len];
        int[] maxSince = new int[len];
               
		//at most two transactions. 
		//Divide days into two parts each one covers on e trasaction. n division.
		// Division gurantees no overlap on transactions.
		//Same-day transaction bring 0 profit.
		//NOTE maxBy[0] = 0, maxSince[len-1] = 0.		
		int valley = prices[0];
        for (int i = 1; i < len; i++) {
            valley = Math.min(valley, prices[i]);
			//Compared with stock1. Need to update the everyday profit. So no pre-price-comparison.
            maxBy[i] = Math.max(maxBy[i - 1], prices[i] - valley);
        } // ignore same day transatcion on day 1.
        
		int peak = prices[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            peak = Math.max(peak, prices[i]);
            maxSince[i] = Math.max(maxSince[i + 1], peak - prices[i]);
            /*update maxProfit while build maxSince*/
            maxProfit = Math.max(maxProfit, maxBy[i] + maxSince[i]); // find i such that maxBy[i]+maxSince[i+1] is the max two-transaction profit, no overlap
        } // ignore same day ransaction on last day. need transact twice.
        return maxProfit;
    }
}
