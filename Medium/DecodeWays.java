/**
 * A message containing letters from A-Z is being encoded to numbers using the
 * following mapping:
 * 
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 
 * Given an encoded message containing digits, determine the total number of
 * ways to decode it.
 * 
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 * 
 * The number of ways decoding "12" is 2.
 * 
 * Tags: DP, String
 */
class DecodeWays {
    public static void main(String[] args) {
        DecodeWays d = new DecodeWays();
        System.out.println(d.numDecodings("1029"));
    }
    
    /**
     * Optimal, DP
     * Reduce space to O(1)
     */
	
	/*
	 *	Let f(i) to be the amount of solutions for s[0,i)
	 *  f(i) = isValid(code1)*f(i-1) + isValid(code2)*f(i-2)
	 *  prev1 = f(i-2), prev2 = f(i-1)
	 */
	 
	 // Restore IP - List all decode ways, can also count the decode ways. But not O(n).
	 
    public int numDecodingsOptimal(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int f_i_2 = 1; // f(0) - always be 1 for the starting point.
        int f_i_1 = s.charAt(0) == '0' ? 0 : 1; //f(1)
        for (int i = 2; i <= len; i++) {
            int code1 = Integer.valueOf(s.substring(i - 1, i)); // 1 digit
            int code2 = Integer.valueOf(s.substring(i - 2, i)); // 2 digits
            int temp = f_i_1;
			// Update for next loop. Actually, f_i_1 is the current solution.
            f_i_1 = (code1 != 0 ? f_i_1 : 0) + (code2 <= 26 && code2 > 9 ? f_i_2 : 0);
            f_i_2 = temp;
        }
        return f_i_1;
    }
    
    /**
     * Time O(n), Space O(n)
     * note that there can be zeros in s
     */
    public static int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length();
        int[] ways = new int[len + 1];
        ways[0] = 1;
        ways[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= len; i++) {
            int code1 = Integer.valueOf(s.substring(i - 1, i));
            int code2 = Integer.valueOf(s.substring(i - 2, i));
            // System.out.println(code2);
            ways[i] = (code1 != 0 ? ways[i - 1] : 0) + (code2 <= 26 && code2 > 9 ? ways[i - 2] : 0);
        }
        return ways[len];
    }
}
