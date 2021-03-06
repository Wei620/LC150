/**
 * Say you have an array for which the ith element is the price of a given
 * stock on day i.
 * 
 * If you were only permitted to complete at most one transaction (ie, buy one
 * and sell one share of the stock), design an algorithm to find the maximum
 * profit.
 * 
 * Tags: Array, DP
 */

 /* ****
 1. 先更新valley， 再更新maxProfit. 没有else， 计算同日0利润， 否则复杂。
 *****/
 // 410 
 public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        int valley = Integer.MAX_VALUE, maxProfit = Integer.MIN_VALUE;
        for(int price : prices){
            if(price < valley) valley = price;
            maxProfit = Math.max(maxProfit, price - valley);
        }
        return maxProfit;
    }
}

class BestTimeStock {
    public static void main(String[] args) {
        int[] prices = { 1, 4, 2 };
        System.out.println(maxProfit(prices));
    }
    
    /**
     * Optimized bottom-up approach
     * O(n) Time, O(1) Space
     * Just record yesterday's profit
     * Update min, max and profit
     * If next price is bigger, it's only possible to update the profit
     * If next price is smaller or equal, it's only possible to update min
     */
	 
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0; // need at least 2
        int maxProfit = 0;
        int min = prices[0]; // min price
        for (int i = 1; i < prices.length; i++) { // note that i starts from 1
            min = Math.min(min, prices[i]); // update min
            // You can update the profit day by day. But this condition save some unnessary comparison.
			if (prices[i] > prices[i - 1]) maxProfit = Math.max(maxProfit, prices[i] - min);
        }
        return maxProfit;
    }
    
    /**
     * Bottom-up approach
     * O(n) Time, O(n) Space
     * Store max profit of each day using an integer array
     * Profit of next day can only be same as last day or update because of 
     * higher price today
     */
    public static int maxProfitB(int[] prices) {
        if (prices == null || prices.length < 2) return 0; // need at least 2 days
        int min = prices[0];
        int max = 0;
        int len = prices.length;
        int[] history = new int[len];
        for (int i = 0; i < len - 1; i++) {
            min = min < prices[i] ? min : prices[i];
            if (i > 0) { // skip first day
                history[i] = Math.max(history[i - 1], prices[i] - min);
                max = history[i] > max ? history[i] : max;
            }
        }
        return max;
    }
}
