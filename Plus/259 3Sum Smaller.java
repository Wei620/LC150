/*
259 3Sum Smaller

Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition nums[i] + nums[j] + nums[k] < target.

For example, given nums = [-2, 0, 1, 3], and target = 2.

Return 2. Because there are two triplets which sums are less than 2:
[-2, 0, 1]
[-2, 0, 3]


Follow up:
 Could you solve it in O(n2) runtime?

*/

/*****
1. 不用back tracking
2. 排序三和的变种。
3. 累加 k - j

411
1. 先排序。
2. 条件 i < len - 2, 3*nums[i]<target
3. count += k - (j+1) + 1 = k - j.
*****/

// No need to exclude the dups.
int threeSumSmaller(int nums, int target){
	nums.sort();
	int count = 0;
	for(int i = 0; i < nums.length - 2; i++){
		if(nums[i] * 3 >= target) break;
		int j = i + 1;
		int k = num.length - 1;
		while(j < k){
			if(nums[i] + nums[j] + nums[k] < target){
				count += k - j;// [i, j, j+1] to [i, j, k]
				j++;
			}
			else{
				k--;
			}
		}
	}
	return count
}