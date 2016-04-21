/*
217 Contains Duplicate

Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct. 
*/

//Time complexity: O(N), memory: O(N)
public boolean containsDuplicate(int[] nums) {
	final Set<Integer> distinct = new HashSet<Integer>();
	for(int num : nums) {
		if(!distinct.add(num)) {
			return true;
		}
		
	}
	return false;
}

/*****
1. 重复可用 hashmap 统计，但排序后比较相邻更简单
*****/

/*
Time complexity: O(N lg N), memory: O(1) - not counting the memory used by sort
Since it is trivial task to find duplicates in sorted array, we can sort it as the first step of the algorithm and then search for consecutive duplicates.
*/
public boolean containsDuplicate(int[] nums) {
	Arrays.sort(nums);
	for(int ind = 1; ind < nums.length; ind++) {
		if(nums[ind] == nums[ind - 1]) {
			return true;
		}
	}
	return false;
}