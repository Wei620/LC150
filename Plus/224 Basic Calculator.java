/*
224 Basic Calculator

Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

You may assume that the given expression is always valid.

Some examples:

"1 + 1" = 2
" 2-1 + 2 " = 3
"(1+(4+5+2)-3)+(6+8)" = 23

Note: Do not use the eval built-in library function. 

*/

/* ****
1. 把“-”当成符号，所有按加法做。
2. 可能是多位数，所以要 高位 * 10处理。 
3. 遇到新符号， 累加符号数后; 数清零， 符号更新。
4. （ 入栈， 压入 结果，符号， 数清零， 符号为正。
   ） 出战， 弹出 上次符号，上次结果， 上次结果 + 符号 * 当前结果
   
283
1.  (: result = 0
	): result 乘累加

411
1. 三变量, result, number,lastSign 一起更新
2. ()多级优先, 只能用stack, * and / 一级优先,可以先算,再一遍累加.
*****/

public int calculate(String s) {
	Stack<Integer> stack = new Stack<Integer>();
	int result = 0;
	int number = 0;
	int lastSign = 1;
	for(int i = 0; i < s.length(); i++){
		char c = s.charAt(i);
		if(Character.isDigit(c)){
			number = 10 * number + (int)(c - '0');
		}else if(c == '+'){
			result += lastSign * number;
			number = 0;
			lastSign = 1;
		}else if(c == '-'){
			result += lastSign * number;
			number = 0;
			lastSign = -1;
		}else if(c == '('){
			//we push the result first, then lastSign;
			stack.push(result);
			stack.push(lastSign);
			//reset the lastSign and result for the value in the parenthesis
			result = 0;
			number = 0;//more clear.
			lastSign = 1;
		}else if(c == ')'){
			result += lastSign * number;
			result *= stack.pop(); //stack.pop() is the lastSign before the parenthesis
			result += stack.pop(); //stack.pop() now is the result calculated before the parenthesis
			number = 0;
			lastSign = 1;
		}
	}
	if(number != 0) result += lastSign * number;
	return result;
}

/*
Simple iterative solution by identifying characters one by one. One important thing
is that the input is valid, which means the parentheses are always paired and in
order. Only 5 possible input we need to pay attention:
1. digit: it should be one digit from the current number
2. '+': number is over, we can add the previous number and start a new number
3. '-': same as above
4. '(': push the previous result and the sign into the stack, set result to 0, just
calculate the new result within the parenthesis.
5. ')': pop out the top two numbers from stack, first one is the sign before this pair
of parenthesis, second is the temporary result before this pair of parenthesis.
We add them together.
Finally if there is only one number, from the above solution, we haven't add the
number to the result, so we do a check see if the number is zero.
*/