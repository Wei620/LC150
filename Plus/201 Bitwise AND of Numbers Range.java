/*
201 Bitwise AND of Numbers Range

Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.

For example, given the range [5, 7], you should return 4. 

*/

/*The idea is very simple:
1. last bit of (odd number & even number) is 0.
2. when m != n, There is at least an odd number and an even number, so the last
bit position result is 0.
3. Move m and n rigth a position.
Keep doing step 1,2,3 until m equal to n, use a factor to record the iteration time.
*/
/* ****
1. m < n, at least one last digit is zero. so the AND result is zero.
2. right shift bit i till m == n, from this bit(inclusively), m equals to n bitwise.
3. remember to resume m(n) by left shift or multiplication.

1. 保留m,n的共有高位(区间内所有的数的这些高位都没变过), 低位都变过,都有0, AND后都置零.
2. m = 0. 共有高位只有0(因为全0).
*****/

/* ****
1. 寻找共有高位.
*****/
//411
public class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int rShift = 0;
        while(m != n){
            m >>>= 1;
            n >>>= 1;
            rShift++;
        }
        return m << rShift;
    }
}

public class Solution {
	public int rangeBitwiseAnd(int m, int n) {
		if(m == 0){
			return 0;
		}
		
		int shiftCnt = 0;
		while(m != n){
			m >>= 1;
			n >>= 1;
			shiftCnt++;
		}
		return m << shiftCnt; // resume m(or n) left
	}
}

/*
The idea is to use a mask to find the leftmost common digits of m and n. Example:
m=1110001, n=1110111, and you just need to find 1110000 and it will be the answer.
*/
public class Solution {
	public int rangeBitwiseAnd(int m, int n) {
		int r=Integer.MAX_VALUE;
		while((m&r)!=(n&r)) r=r<<1;
		return n&r;
	}
}