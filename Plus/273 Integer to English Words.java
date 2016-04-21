/*
273 Integer to English Words

Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1. 

For example,

123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"


Hint:
1.Did you see a pattern in dividing the number into chunk of words? For example, 123 and 123000.
2.Group the number by thousands (3 digits). You can write a helper function that takes a number less than 1000 and convert just that chunk to words.
3.There are many edge cases. What are some good test cases? Does your code work with input such as 0? Or 1000010? (middle chunk is zero and should not be printed out)

*/

/*****
1. 一千内的数字读法相同, 只要换单位(thousands); 不用求1000的高权数,从后向前转化.
2. 用n%权数找词. 权数不一定是单位组的长度, 很灵活.
3. 单位组里第一个" ", 处理 n%权数 == 0, 不能读0. num==0 是special case.
4. 100 vs 20重叠. 1) 先20, 2) 100时只读20里的前10做个位 + helper(num % 10).
5. 如何处理hundreds.

293
1. 主函数里, 被1000整除的部分不读, thousand单位后缀空格, 最后在trim.
2. Helper里分区间讨论. 注意除模联读.

411
1. helper(num % 1000).trim(). 因为 "seventy five" 和 "seventy "
2. words.trim(); "one thousand "
3. "Zero"
*****/
//411
public class Solution {
    private final String[] lessThan20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final String[] tens = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final String[] thousands = {"", "Thousand", "Million", "Billion"};

    public String numberToWords(int num) {
    	if (num == 0) return "Zero";
    	int i = 0;
    	String words = "";
    	while (num > 0) {
    		if (num % 1000 != 0){ //需要跳过单位
    			words = helper(num % 1000).trim() + " " + thousands[i] + " " + words;
			}
    		num /= 1000;
    		i++;
    	}
    	return words.trim();
    }

    // less than 1000
    private String helper(int num) {
    	if (num == 0)
    		return ""; // 整除, 跳过该组.
    	else if (num < 20)
    		return lessThan20[num];
    	else if (num < 100) // [20, 100)
    		return tens[num / 10] + " " + helper(num%10); // tens的TEN只是placeholder
    	else // [100, 1000)
    		return helper(num / 100) + " Hundred " + helper(num % 100);
    }
}