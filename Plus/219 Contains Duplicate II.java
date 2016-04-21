/*
219 Contains Duplicate II

Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the difference between i and j is at most k. 
*/

/*****
1. 查看 num[i - k] 和 num[i] 间是否有重复
2. i- 1， num[i - k - 1] to num[i - 1] 无重复。
   i，  先移除num[i - k - 1], 新区间有重复必由 新引入的num[i]造成
3. 利用nums[], hashset就够了
4. 也可用map 以nums[i]做key， i做val
   abs(get（nums[i]) - i) <= k?

0328 
1. Set保证里面的元素不同.
2. 标号差 <= k
3. 两个条件都满足, 先保证标号, 所以remove nums[i-k-1]. i > k防越界.
				在看相等能不能满足
411.
1. 移除的是 (i-1) - k.
*****/

public boolean containsNearbyDuplicate(int[] nums, int k) {
	Set<Integer> set = new HashSet<Integer>();
	for(int i = 0; i < nums.length; i++){
		if(i > k) set.remove(nums[i-k-1]); // i - (i-k-1) = k + 1 > k 他们可以重复,因为需要>k, set放不可重复的部分. 
		if(!set.add(nums[i])) return true;
	}
	return false;
}