/*
233 Number of Digit One

Given an integer n, count the total number of digit 1 appearing in all non-negative integers less than or equal to n.

For example:
 Given n = 13,
 Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13. 

Hint:
1.Beware of overflow.

*/

/***
1. 分别看k位上的1会出现几次， k是0-based。
2. 把所有小于等于n的书分为两部分， [0, n % (10^k * 10)], [n/(10^k * 10) * 10^（k+1), n], 
3. [0, n % (10^k * 10)], 第k位 n/(10^k)%10
   如果kth位 > 1, 该位 1 会出现 10^k次。 
   如果kth位 = 1， 该位 1 只出现 n % (10^k) + 1 (kth = 1, others = 0)
4. [n / (10^k * 10) * 10^(k+1), n]
   每10^（k+1) k位上的1会出现 10^k次， n/(10^(k+1)) * 10^k次
5. 令m = 10^k

238
1.	m是权数， 对应位上的数字为 n/m % 10.
2.  每1000里， 百位1会出现100次。 注意 m <= n.
***/

public int countDigitOne(int n) {
	int ones = 0;
	for (long m = 1; m <= n; m *= 10)
		int k = n/m % 10; // digit
		
		if(k == 1) ones += 1 + n%m; // 残值
		else if(k > 1) ones += m; //高位全零
        ones += n/(10 * m) * m;        
	return ones;
}


/*
Go through the digit positions by using position multiplier m with values 1, 10, 100,
1000, etc.
For each position, split the decimal representation into two parts, for example split
n=3141592 into a=31415 and b=92 when we're at m=100 for analyzing the
hundreds-digit. And then we know that the hundreds-digit of n is 1 for prefixes "" to
"3141", i.e., 3142 times. Each of those times is a streak, though. Because it's the
hundreds-digit, each streak is 100 long. So (a / 10 + 1) * 100 times, the
hundreds-digit is 1.
Consider the thousands-digit, i.e., when m=1000. Then a=3141 and b=592. The
thousands-digit is 1 for prefixes "" to "314", so 315 times. And each time is a streak of
1000 numbers. However, since the thousands-digit is a 1, the very last streak isn't
1000 numbers but only 593 numbers, for the suffixes "000" to "592". So (a / 10 *
1000) + (b + 1) times, the thousands-digit is 1.
The case distincton between the current digit/position being 0, 1 and >=2 can easily
be done in one expression. With (a + 8) / 10 you get the number of full streaks,
and a % 10 == 1 tells you whether to add a partial streak.
*/
