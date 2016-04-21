/*
309 Best Time to Buy and Sell Stock with Cooldown

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
•You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
•After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

Example:

prices = [1, 2, 3, 0, 2]
maxProfit = 3
transactions = [buy, sell, cooldown, buy, sell]

*/


/*
buy[i] : Max profit till index i. The series of transaction is ending with a buy.
sell[i] : Max profit till index i. The series of transaction is ending with a sell.

Till index i , the buy / sell action must happen and must be the last action. It may not happen at index i . It may happen at i - 1, i - 2, ... 0 .

buy[i] = max(sell[i-2]-price, buy[i-1])
sell[i] = max(buy[i-1]+price, sell[i-1])
*/
/* ****
1. 到ith天，最后一次交易有两种， 买，卖。
	如果是买的最大利润 buy[i] = max(买在之前 buy[i-1]， 买在最后一天 sell(i-2)-price).
	     卖        sell[i] = max(卖在之前) sell[i-1], 卖最后 buy(i-1) + price).
	注意，最有一天有交易，需在利润里反应当天transaction.
2. 最后返回的是sell[n-1], 不要烂在手里。

303
1. 每式都是max（状态延续， 当天交易）
2. sell[i-2]的处理， 计算buy时，presell还没更新，所以是两天前的。
3. 初始值很tricky。 sell[0] = 0(不可能，也可以MIN_VALUE, 因为都是取max), sell[1] = price[1] - price[0], buy[0] = -price[0]. buy[1] = -price[1]

411
1. 注意 buy[1], sell[1]
*****/
// 411
public class Solution {
    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) return 0;
        int m = prices.length;
        int[] buy = new int[m], sell = new int[m];
        buy[0] = -prices[0];
        buy[1] = Math.max(buy[0], -prices[1]);
        sell[0] = 0;
        sell[1] = Math.max(0, prices[1] - prices[0]);
        for(int i = 2; i < m; i++){
            sell[i] = Math.max(sell[i - 1], buy[i - 1] + prices[i]);
            buy[i] = Math.max(buy[i - 1], sell[i - 2] - prices[i]);
        }
        return sell[m - 1];
    }
}

public int maxProfit(int[] prices) {
    int sell = 0, prev_sell = 0, buy = Integer.MIN_VALUE, prev_buy;
    for (int price : prices) {
        prev_buy = buy;
        buy = Math.max(prev_buy， prev_sell - price); // prev_sell not updated yet. 
        prev_sell = sell;
        sell = Math.max(prev_sell， prev_buy + price);
    }
    return sell;
}