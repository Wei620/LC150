/*
227 Basic Calculator II

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

You may assume that the given expression is always valid.

Some examples:

"3+2*2" = 7
" 3/2 " = 1
" 3+5 / 2 " = 5



Note: Do not use the eval built-in library function. 

*/
/*****
1. 	两遍扫描，第一遍只算乘除，加法入栈， 减法当作加有符号数。
	第二遍只算加法。
2.  第一遍， 缓存lastSign， 累计num， 在遇到新符号时，
		+， -： （+-num）入栈
		*， /： （pop* /num）入栈
		lastSign = 新符号
3. lastSign 上一个符号， num 当前数

411.
0. 遇新符号, 再考虑压入(lastSign和num组成的)复合数字.
1. 两变量, num, lastSign
2. 因为 ||, 所以不能用else if. 处理了最后一个数.
3. LinkedList就够了, 不用stack.
****/

public class Solution {
	public int calculate(String s) {
		int len;
		if(s==null || (len = s.length())==0) return 0;
		Stack<Integer> stack = new Stack<Integer>();
		int num = 0;
		char lastSign = '+';
		for(int i=0;i<len;i++){
			if(Character.isDigit(s.charAt(i))){
				num = num*10+s.charAt(i)-'0';
			}
			if((!Character.isDigit(s.charAt(i)) && s.charAt(i)!= ' ')/*MUST BE OPERATOR*/ 
				|| i == len - 1){
				if(lastSign=='-'){
					stack.push(-num);
				}
				if(lastSign=='+'){
					stack.push(num);
				}
				if(lastSign=='*'){
					stack.push(stack.pop()*num);
				}
				if(lastSign=='/'){
					stack.push(stack.pop()/num);
				}
				lastSign = s.charAt(i);
				num = 0;
			}
		}
		int re = 0;
		for(int i:stack){
			re += i;
		}
		return re;
	}
}

// General operator + parentheses， to be finished.
public class Solution {
	public int calculate(String s) {
		int len;
		if(s==null || (len = s.length())==0) return 0;
		Stack<Integer> stack = new Stack<Integer>();
		int num = 0;
		char sign = '+';
		for(int i=0;i<len;i++){
			char c = s.charAt(i);
			if(Character.isDigit(c)){
				num = num*10+s.charAt(i)-'0';
			}
			/*
			else if(c == '('){
				stack.push((int)sign);
				curr = new Stack<Integer>();
				num = 0;
				sign = "+"；
			}
			else if(c == ')'){
				if(sign == '-') num = -num;
				for(int i : curr){
					num += i;
				}
				curr = stack.pop();
				sign = (char)(curr.pop());
			}*/			
			else if(c != ' ')/*MUST BE OPERATOR*/ || i == len - 1){
				if(sign=='-'){
					stack.push(-num);
				}
				if(sign=='+'){
					stack.push(num);
				}
				if(sign=='*'){
					stack.push(stack.pop()*num);
				}
				if(sign=='/'){
					stack.push(stack.pop()/num);
				}
				sign = s.charAt(i);
				num = 0;
			}
		}
		int re = 0;
		for(int i:stack){
			re += i;
		}
		return re;
	}
}