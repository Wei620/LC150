/*
204 Count Primes
Description:

Count the number of prime numbers less than a non-negative number, n.

*/

/*****
1. black out法, boolean[n]
		n太大也可用hashset(non-prime)
2. 遍历i = 3 to n-1的奇数. black out i*(i+k), k = 0 to (i*(i+k) < n)
		for() 更好.j^2 + j*k
3. i^2 >= n不再black out, 用sqrt(n)做边界

411
1. sqrt(MAX) 做边界, 防止 i * i overflow.
2. i+=2 tricky
3. 2 is a prime.
*****/

public class Solution {
int countPrimes(int n) {
	if (n<=2) return 0;
	boolean[] blackout = new boolean[n];
	int cnt = 1;
	int upper = (int)Math.sqrt(Integer.MAX_VALUE);
	for (int i=3; i<n; i+=2) {// i += 2 skip even num
		if (!blackout[i]) {
			cnt++;
			//avoid overflow
			if (i>upper) continue;
			for (int j=i*i; j<n; j+=i) {// j = i * (i + k)
				blackout[j] = true;
			}
		}
	}
	return cnt;
}
}