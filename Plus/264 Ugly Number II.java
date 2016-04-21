/*
264 Ugly Number II

Write a program to find the n-th ugly number. 

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers. 

Note that 1 is typically treated as an ugly number. 

Hint:
1.The naive approach is to call isUgly for every number until you reach the nth one. Most numbers are not ugly. Try to focus your effort on generating only the ugly ones.
2.An ugly number must be multiplied by either 2, 3, or 5 from a smaller ugly number.
3.The key is how to maintain the order of the ugly numbers. Try a similar approach of merging from three sorted lists: L1, L2, and L3.
4.Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).

*/

/*****
1. 第一个Ugly 1， （x2, x3, x5）生成3个， （x2, x3, x5）再生成9个， ... 如何排序， 如何找到nth?
2. k[n]存前n个ugly， 每个都有机会（x2, x3, x5）。
3. t2, t3, t5, the index of last element that 才x2, x3, x5。
   min(k[t2]*2, k[t3]*3, k[t5]*5) 做下一个ugly。 
4. 哪个最小，移动哪个最小。 可能同时移动多个， 全是if 没有else。
*****/

public int nthUglyNumber(int n) {
	if(n <= 0) return 0; // get rid of corner cases
	if(n == 1) return 1; 
	int t2 = 0, t3 = 0, t5 = 0; //pointers for 2, 3, 5
	List<Integer> k = new ArrayList<Integer>();
	k[0] = 1;
	for(int i = 1; i < n ; i ++)
	{
		k[i] = Math.min(Math.min(k[t2]*2,min(k[t3]*3),k[t5]*5));
		if(k[i] == k[t2]*2) t2++;
		if(k[i] == k[t3]*3) t3++;
		if(k[i] == k[t5]*5) t5++;
	}
	return k[n-1];
}


/*
We have an array k of first n ugly number. We only know, at the beginning, the first
one, which is 1. Then
k[1] = min( k[0]x2, k[0]x3, k[0]x5). The answer is k[0]x2. So we move 2's pointer to
1. Then we test:
k[2] = min( k[1]x2, k[0]x3, k[0]x5). And so on. Be careful about the cases such as 6,
in which we need to forward both pointers of 2 and 3.
x here is multiplication.
*/