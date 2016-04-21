/*
327 Count of Range Sum

Given an integer array nums, return the number of range sums that lie in [lower, upper] inclusive.
 Range sum S(i, j) is defined as the sum of the elements in nums between indices i and j (i ≤ j), inclusive. 

Note:
 A naive algorithm of O(n2) is trivial. You MUST do better than that. 

Example:
 Given nums = [-2, 5, -1], lower = -2, upper = 2,
 Return 3.
 The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums are: -2, -1, 2. 

*/

/*****
1. 	BIT sum很大，用long。 离散后的数组太大。
2. 	merge sort的变种。前后两段的元素，在未排序前， 可以分别做区间的左边界和有边界。
		从left枚举i，在right里找k， j。 (right里本身已排好序)
			sums[k] >= sums[i] + lower, 
			sums[j] > sums[i] + uppper.
3.	已经枚举了i，就把right里比i小的排先，在加i，.... merge
4.	为什么要空sums[0] 这样才能查nums[i]]本身是否在区间内。
*****/

public class Solution {
	public int countRangeSum(int[] nums, int lower, int upper) {
		int n = nums.length;
		long[] sums = new long[n + 1];
		for (int i = 0; i < n; ++i)
			sums[i + 1] = sums[i] + nums[i];
		return countWhileMergeSort(sums, 0, n + 1, lower, upper);
	}
	
	//[start, end)
	private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
		if (end - start <= 1) return 0;
		int mid = (start + end) / 2;
		int count = countWhileMergeSort(sums, start, mid, lower, upper) 
					+ countWhileMergeSort(sums, mid, end, lower, upper);
		int j = mid, k = mid, t = mid, r = 0; //merge id in right, r merge id.
		long[] cache = new long[end - start];
		for (int i = start; i < mid; i++) {
			while (k < end && sums[k] - sums[i] < lower) k++;
			while (j < end && sums[j] - sums[i] <= upper) j++;
			//start merging
			while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++]; 
			cache[r++] = sums[i];
			count += j - k;
		}
		System.arraycopy(cache, 0, sums, start, r);
		return count;
	}
}
