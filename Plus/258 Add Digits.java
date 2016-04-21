/*
258 Add Digits

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit. 

For example: 

Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it. 

Follow up:
 Could you do it without any loop/recursion in O(1) runtime? 

Hint:
1.A naive implementation of the above process is trivial. Could you come up with other methods? 
2.What are all the possible results?
3.How do they occur, periodically or randomly?
4.You may find this Wikipedia article useful.

*/

/* ****
in: 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, ...
out:0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 1,  2,  3,  4,  5,  6, 

10 - 18整块 -> 0 - 8 -> 1 - 9.
19 - 27    -> 0 - 8 -> 1 - 9.
*****/

int addDigits(int num) {
	return 1 + ((num - 1) % 9);
}
