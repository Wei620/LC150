/*
313 Super Ugly Number

Write a program to find the nth super ugly number. 

Super ugly numbers are positive numbers whose all prime factors are in the given prime list primes of size k. For example, [1, 2, 4, 7, 8, 13, 14, 16, 19, 26, 28, 32]  is the sequence of the first 12 super ugly numbers given primes = [2, 7, 13, 19] of size 4. 

Note:
 (1) 1 is a super ugly number for any given primes.
 (2) The given numbers in primes are in ascending order.
 (3) 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000. 

*/

/*****
1. ugly II的一般形式， prime由一个list给出。
	用idx = int[list.length]记录，还未添加至（ret）结果序列ugly prime[i]*idx[i].
2. ret初始为MAX_VALUE, 可以用同一形势更新（ret跟新为最小结果）
*****/

// any primes[j] * res[i] is a ugly number, how to maintain an acsending order?
// use index[j] to record the res[index[j]] to be multiplied by primes[j]
public int nthSuperUglyNumber(int n, int[] primes) {
	int[] ret = new int[n];
	ret[0] = 1;
	int[] indexes = new int[primes.length];// acsending order
	for(int i = 1; i < n; i++){
		ret[i] = Integer.MAX_VALUE;
		for(int j = 0; j < primes.length; j++){
			ret[i] = Math.min(ret[i], primes[j] * ret[indexes[j]]);
		}
		for(int j = 0; j < indexes.length; j++){
			if(ret[i] == primes[j] * ret[indexes[j]]){
			indexes[j]++;
			}
		}
	}
	return ret[n - 1];
}