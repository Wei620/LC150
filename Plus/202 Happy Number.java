/*
202 Happy Number
Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 19 is a happy number
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1

*/

/* ****
411
1. 重复 - hashset, 但流程重复可用 Floyd.
2. fast一次不,不好步间检查 fast == slow
   happy - 重点自循环, 如果前面没环, 一点在重点相遇.

1.	十进制数的取低,右移
2. 	双指针走 Floyd cycle detection. 
		变形, 不知道slow总共多少不, 但fast先走完的会一直在重点等. 看相遇是不是在重点.
			do-while
*****/

/*
I see the majority of those posts use hashset to record values. Actually, we can
simply adapt the Floyd Cycle detection algorithm. I believe that many people have
seen this in the Linked List Cycle detection problem. The following is my code:*/

int digitSquareSum(int n) {
	int sum = 0, tmp;
	while (n > 0) {
		tmp = n % 10;
		sum += tmp * tmp;
		n /= 10;
	}
	return sum;
}

boolean isHappy(int n) {
	int slow = n, fast = n;
	do {
		slow = digitSquareSum(slow);
		fast = digitSquareSum(fast);
		fast = digitSquareSum(fast);
	} while(slow != fast);
	if (slow == 1) return true;
	else return false;
}