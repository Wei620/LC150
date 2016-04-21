/*
324 Wiggle Sort II

Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3].... 

Example:
 (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
 (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2]. 

Note:
 You may assume all input has valid answer. 

Follow Up:
 Can you do it in O(n) time and/or in-place with O(1) extra space? 

*/

//排序，然后两边分别取，复杂度O(nlogn)
//注意排完序之后应该倒着来。比如[4,5,5,6]这个 数据

/*****
1. 	与I的区别，相邻不等。
2.	(n+1)/2 是后半部分开始的索引。 i&1==0偶数。
3.	分成两半，前半任何<=后半任何。
3.  s(0), ..... , s((n-1)/2), s((n+1)/2), ..... s(n-1)
					--s							--t
	num[t] 必大于 num[s]， 如果此题有解。
		反证，因为已排序， 假设 num[t] == nums[s]. 
			共有， n-1-(n-1)/2+1 >= （n-1）/2 +1 = （n+1）/2 个相等， 
			要把他们隔开 至少需要（n+1）/2个不同元素， 总数至少要n+1个， 超过。 所以不可能。
	所以 num[s], num[t] 排好。 
		num[t] > nums[s] >= num[s-1]
		移出这两个元素，可以同理证明 num[t-1] 大于 num[s-1]， 
		所以是用 （n+1）/2分开后，依次从前半末尾，后半末尾取元素，构成wiggleII序列。
		
*****/
public class Solution {
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        int[] temp = new int[nums.length];
        int s = (nums.length + 1) >> 1, t = nums.length;
        for (int i = 0; i < nums.length; i++) {
            temp[i] = (i & 1) == 0 ?  nums[--s] : nums[--t] ;
        }
 
        for (int i = 0; i < nums.length; i++)
            nums[i] = temp[i];
    }
}

//http://www.zrzahid.com/wiggle-sort/ follow up