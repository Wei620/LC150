/*
247 Strobogrammatic Number II

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,

Given n = 2, return ["11","69","88","96"].
*/

/* ****
1. 简洁, 递归处理奇偶情况,两个base. 不用back tracking.
2. 判断 currLen 是否已达到 targetLen, 此时不能添加'00'

293
1. 不是palindrome, 找半反转略麻烦. dfs

411
1. currLen 必须要. 只用target, 回溯中无法判断是否为最后一步(不加0).
*****/

public List<String> findStrobogrammatic(int n) {
	return helper(n, n);
	//dfs(n, "", res);
	//return res;
}

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

void dfs(int total, String half, List<String> res){
	char[] digits = {'0','1','8','6','9'};
	int len = half.length();
	if(len = total/2){
		StringBuilder sb = new StringBuilder(half);
		if(total % 2 == 1){
			for(int i = 0; i < 3; i++){
				sb.append(digits[i]);
			}
		}
		for(int i = half.length() - 1; i >= 0; i--){
			sb.append(getStro(half.charAt(i)));
		}
		res.add(new String(sb));
		return;
	}	
	
	for(int i = 0; i < digits.length; i++){
		if(len == 0 && i == 0) continue;
		dfs(total, half + digits[i], res);		
	}
}
