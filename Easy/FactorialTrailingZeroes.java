/**
 * Given an integer n, return the number of trailing zeroes in n!.
 * 
 * Note: Your solution should be in logarithmic time complexity.
 * 
 * Tag: Math
 */
 
 /* ****
1. n! 里Prime factor 2的个数比5的多。 一对2-5就有构成一个尾0. n！有多少factor 5？
2. 1-n里有factor 5的数共有n/5个
        其中， 有extra 5的数共有 n/5/5个
			其中， 再有         n/5/5/5个
		 ....
	累加起来就是总共factor5的个数
   *****/
class FactorialTrailingZeroes {
    public static void main(String[] args) {
        System.out.println(trailingZeroes(20));
    }
    
    /**
     * O(log5-n)
     */
    public static int trailingZeroes(int n) {
        int r = 0;
        while (n > 0) {
            n /= 5;
            r += n; // add # of 5 in n
        }
        return r;
    }
    
    /**
     * Recursive
     */
    public static int trailingZeroesB(int n) {
        return n <= 0 ? 0 : n / 5 + trailingZeroes(n / 5);
    }
}