/*
254 Factor Combinations

Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note:

Each combination’s factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].

You may assume that n is always positive.

Factors should be greater than 1 and less than n.

Examples:

input: 1

output:

[]
input: 37

output:

[]
input: 12

output:

[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32

output:

[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]

*/

/*****
1.	back tracking. 终止条件 n <= 1. 0,1 无因子，不添加结果.
2.	n可以被i整除， n%i == 0，

293
1.	helper(result, item, n/i, i); 注意i做下一次start, 处理重复因子.

411
1. 循环条件 <n 排除质数(进不了if).
2. base 1. 查isEmpty()防止 直接test 1.
3. i = Math.max(2, start) 清晰.
4. next start 还是i, 考虑重复因子.
*****/

//411 not tested.
public List<List<Integer>> getFactors(int n) {
	List<List<Integer>> result = new ArrayList<List<Integer>>();
	helper(result, new ArrayList<Integer>(), n, 2);
	return result;
}
public void helper(List<List<Integer>> result, List<Integer> item, int n, int start){
	if (n == 1) {
		if (!item.isEmpty()) {
			result.add(new ArrayList<Integer>(item));
		}
		return;
	}
	for (int i = Math.max(2, start); i < n; i++) {
		if (n % i == 0) {
			item.add(i);
			helper(result, item, n/i, i);
			item.remove(item.size()-1);
		}
	}
}

public List<List<Integer>> getFactors(int n) {
	List<List<Integer>> result = new ArrayList<List<Integer>>();
	helper(result, new ArrayList<Integer>(), n, 2);
	return result;
}
public void helper(List<List<Integer>> result, List<Integer> item, int n, int start){
	if (n <= 1) {
		if (item.size() > 1) {
			result.add(new ArrayList<Integer>(item));
		}
		return;
	}
	for (int i = start; i <= n; i++) {
		if (n % i == 0) {
			item.add(i);
			helper(result, item, n/i, i);
			item.remove(item.size()-1);
		}
	}
}