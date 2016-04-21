/*
241 Different Ways to Add Parentheses

Given a string of numbers and operators, return all possible results from computing all the different possible ways to group numbers and operators. The valid operators are +, - and *.

Example 1 
Input: "2-1-1".
((2-1)-1) = 0
(2-(1-1)) = 2

Output: [0, 2]

Example 2 
Input: "2*3-4*5"
(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10

Output: [-34, -14, -10, -10, 10]

*/

/*****
1. divide-conquer
2. base，不包含运算符， 结果是自己。
3. 遍历所有运算符，对各种分割后的2个list，按元素组合求值。

238
1. base很有特点, 两重循环内加到res,但分割有一个是空,所以res没有添加过元素.

411
0. 加括号的本质, 每个operator都可以是最后的计算.
1. base是input里没有operator.不好直接判断. 所以下移, 借用正常for.
*****/

public class Solution {
	public List<Integer> diffWaysToCompute(String input) {
		List<Integer> res = new LinkedList<Integer>();
		for (int i=0; i<input.length(); i++) { // first may be a operator.
			if (input.charAt(i) == '-' ||
				input.charAt(i) == '*' ||
				input.charAt(i) == '+' ) {
				String part1 = input.substring(0, i); // i = 0, 1 -> base case
				String part2 = input.substring(i+1);
				List<Integer> part1Ret = diffWaysToCompute(part1);
				List<Integer> part2Ret = diffWaysToCompute(part2);
				for (Integer p1 : part1Ret) {
					for (Integer p2 : part2Ret) {
						int c = 0;
						switch (input.charAt(i)) {
							case '+': c = p1+p2;
							break;
							case '-': c = p1-p2;
							break;
							case '*': c = p1*p2;
							break;
						}
						res.add(c);
					}
				}
			}
		}
		if (res.size() == 0) { // base case. RET.size
			res.add(Integer.valueOf(input));
		}
		resurn res;
	}
}
