/*
268 Missing Number 

Given an array containing n distinct numbers taken from 0, 1, 2, …, n, find the one that is missing from the array.

For example,
Given nums = [0, 1, 3] return 2.

Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
*/

// n distinct numbers taken from 0, 1, 2, …, n
// missing one
// xor [0,n] will find that one.

/* ****
1. 非固定空间. A[n+1] 初始化为-1, i放到A[i], 再扫一遍 A[i] == -1, 返回 i.
2. 图形化想象. 一个空间, nums 异或 他的值域空间 [0,n], 剩下的就是missing.

293
1. xor的初始值是0.

411.
1. 其他方法， 求和， 排序， 额外int[n+1]统计
*****/

public int missingNumber(int[] nums) {
	int xor = 0, i = 0;
	for (i = 0; i < nums.length; i++) {
		xor = xor ^ i ^ nums[i];
	}
	return xor ^ i;
}

/*
The basic idea is to use XOR operation. We all know that a^b^b =a, which means
two xor operations with the same number will eliminate the number and reveal the
original number. In this solution, I apply XOR operation to both the index and value
of the array. In a complete array with no missing numbers, the index and value
should be perfectly corresponding( nums[index] = index), so in a missing array,
what left finally is the missing number.
*/