/*
231 Power of Two

Given an integer, write a function to determine if it is a power of two. 
*/

/*****
1. 常规： while(i < n)i*=2; return i == n.
2. 7&8 == 0. 
3. 排除负数。

411
1. signed, n & -n 最右1代表的2进制数
   signed, unsigned, n & (n - 1) 去除最右1后的2进制数.
*****/

public class Solution {
	public boolean isPowerOfTwo(int n) {
		if(n<=0) return false;
			return !(n&(n-1));
	}
	
	/*
	This is kind of cheating, but the idea is that a power of two in binary form has and
	only has one "1".*/
	
	public boolean isPowerOfTwo(int n) {
		return n>0 && Integer.bitCount(n) == 1;
	}
}