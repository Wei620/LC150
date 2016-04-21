/*
238 Product of Array Except Self

Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Solve it without division and in O(n).

For example, given [1,2,3,4], return [24,12,8,6]. 

Follow up:
 Could you solve it with constant space complexity? (Note: The output array does not count as extra space for the purpose of space complexity analysis.)

*/

/*****
1. 正反两边扫描. 分别结算i前,i后的乘积
2. 正扫直接存输出
3. 反扫引入constant space "right"临时记录.
4. 初始值 res[0]=1 (1 ... n-1), right = 1(n-2 ... 0);

411
1. 浮着的 right
*****/

public class Solution {
	public int[] productExceptSelf(int[] nums) {
		int n = nums.length;
		int[] res = new int[n];
		res[0] = 1;
		for (int i = 1; i < n; i++) {
			res[i] = res[i - 1] * nums[i - 1];
		}
		
		int right = 1; // product of all elements behind i.
		for (int i = n - 2; i >= 0; i--) {
			right *= nums[i+1];
			res[i] *= right;
		}
		return res;
	}
}