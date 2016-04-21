/*
248 Strobogrammatic Number III

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to count the total strobogrammatic numbers that exist in the range of low <= num <= high.

For example,

Given low = "50", high = "100", return 3. Because 69, 88, and 96 are three strobogrammatic numbers.

Note:

Because the range might be a large number, the low and high numbers are represented as string.
*/

/* ****
1. 充分利用I和II

411
1. dp[i] = (dp[i-2] + dp[i-4]) * 4, dp[i-4]是补偿dp[i-2]里没计算0x0的那组.

*****/

// exclusive. all numbers are with same length of num.
private int countSmaller(String num){
	List<Integer> stros = findStrobogrammatic(num.length());
	int cnt;
	for(int stro : Stros){
		if(stro < num) cnt++;
	}
	return cnt;
}

public int strobogrammaticInRange(String low, String high){
	int lLen = low.length(), hLen = high.length();
	int[] dp = new int[hLen + 1];
	dp[0] = 1; //""
	dp[1] = 3; //"0", "1", "8"
	dp[2] = 1 * 4;
	dp[3] = 3 * 4;
	for(i = 4; i < hLen; i++){
		dp[i] = (dp[i-2] + dp[i-4]) * 4;
	}
	
	int cnt = 0;
	for(int i = lLen; i < hLen; i++){
		cnt += dp[i];
	}
	
	cnt -= countSmaller(low);
	cnt += countSmaller(high);
	if(isStrobogrammatic(high)) cnt++
	
	return cnt;
}





/*****
1. 递归计算各个长度的Stro个数
2. 累加[lLen, hLen). lLen再扣除, hLen除0累加
3. 
*****/
char[] map5 = {'0','1','6','8','9'};

// 返回于num等长,但小于num的Stro个数
private int countSmaller(String num, int[] dp){
	int len = num.length();
	if(len == 0) return 0;
	if(len  == 1){
		if(num > 8) return 2;
		if(num > 1) return 1;
		return 0;
	}
		
	int cnt = 0;
	char c0 = num.charAt(0);
	for(char c : map){
		if(c0 > c){
			cnt += dp[len - 2];
		}
		else if(c0 == c){
			String inner = num.Substring(1, num.length() - 1);
			cnt += countSmaller(inner, dp);
			if(num.charAt(len-1) > c && isStro(c + inner + c)) cnt++; // compensate
			break; //sorted
		}
	}
	return cnt;
}

public int strobogrammaticInRange(String low, String high){
	int lLen = low.length(), hLen = high.length();
	int[] dp = new int[hLen + 1];
	dp[0] = 1; //""
	dp[1] = 3; //"0", "1", "8"
	int cnt = 0;
	
	for(int i = 2; i <= hLen; i++){
		dp[i] = dp[i-2] * 5;
		if(i == lLen){
			cnt += dp[i];
			int small = countSmaller(low, dp);
			if(lLen > 1) small -= dp[lLen - 2]; // 0 is a stro. but any 00 is not.
			cnt -= small;					
		}
		else if(i > lLen && i < hLen){
			cnt += dp[i];
		}		
		else if(i == hLen){
			int small = countCmaller(high, dp);
			if(hLen > 1) small -= dp[hLen - 2];
			cnt += small;
		}
	}
	
	if(isStro(high)) cnt++;
	return cnt;
}

/*
List<String> helper(int currLen, int targetLen) {
	if (currLen == 0) return new ArrayList<String>(Arrays.asList(""));
	if (currLen == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));
	List<String> list = helper(currLen - 2, targetLen);
	List<String> res = new ArrayList<String>();
	for (int i = 0; i < list.size(); i++) {
		String s = list.get(i);
		if (currLen != targetLen) res.add("0" + s + "0");
		res.add("1" + s + "1");
		res.add("6" + s + "9");
		res.add("8" + s + "8");
		res.add("9" + s + "6");
	}
	return res;
}
*/