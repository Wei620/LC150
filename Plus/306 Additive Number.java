/*
306 Additive Number

Additive number is a string whose digits can form additive sequence.

A valid additive sequence should contain at least three numbers. Except for the lStart two numbers, each subsequent number in the sequence must be the sum of the preceding two.

For example:
"112358" is an additive number because the digits can form an additive sequence: 1, 1, 2, 3, 5, 8. 
1 + 1 = 2, 1 + 2 = 3, 2 + 3 = 5, 3 + 5 = 8
"199100199" is also an additive number, the additive sequence is: 1, 99, 100, 199. 1 + 99 = 100, 99 + 100 = 199


Note: Numbers in the additive sequence cannot have leading zeros, so sequence 1, 2, 03 or 1, 02, 3 is invalid. 

Given a string containing only digits '0'-'9', write a function to determine if it's an additive number.

Follow up:
 How would you handle overflow for very large input integers? 

*/

/*****
1. 前两个数确定了， additive序列就确定。 根据长度切分出前两个， 然后验证后续。
2. 注意两个长度的限制，
	len1 <= n/2， （这个其实可以省略， 多一些简单的循环）。 图想，奇偶（中，中右-已经不行）
	len1+len2+max(len1,len2) <= n， max表示sum的最小长度。
3. 切分数字， 排除02的情况。	
*****/


public class Solution {
	public boolean isAdditiveNumber(String num) {
		if (num == null || num.length() < 3) return false;
		int n = num.length();
		for(int len1 = 1; len1 <= n/2; len1++){
			if(len1 > 1 && nums.charAt(0) == '0') break;
			int start2 = len1;			
			for(int len2 = 1; len1 + len2 + Math.max(len1, len2) <= n; len2++){
				if(len2 > 1 && num.charAt(start2) == '0') break;
				String first = num.substring(0, len1);
				String second = num.substring(start2, start2 + len2);
				if(isValid(first, second, num)) return true;
			}
		}
		
		return false;
	}
	
	//  Check if the substring staring at pos is a additive sequence given s1 and s2.
	private boolean isValid(String s1, String s2, String num){
		int n = num.length();
		int pos = s1.length() + s2.length();
		while(pos < n){
		    String sum = add(s1, s2);
			if(!num.startsWith(sum, pos)) return false; //剩余不足sum长的 也false淘汰
			pos += sum.length();
			s1 = s2;
			s2 = sum;
		}
		return true;
	}
	
	private String add(String s1, String s2){
		int i = s1.length() - 1;
		int j = s2.length() - 1;
		char[] cArr1 = s1.toCharArray();
		char[] cArr2 = s2.toCharArray();
		String res = "";
		int tmp = 0;
		while(i >= 0 || j >= 0){
			if(i >= 0) tmp += cArr1[i--] - '0';
			if(j >= 0) tmp += cArr2[j--] - '0';
			res = (tmp % 10) + res;
			tmp /= 10;
		}
		if(tmp == 1) res = '1' + res;
		return res;
	}
}