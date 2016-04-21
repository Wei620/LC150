/*
280 Wiggle Sort

Given an unsorted array nums, reorder it in-place such that nums[0] <= nums[1] >= nums[2] <= nums[3]....

For example, given nums = [3, 5, 2, 1, 6, 4], one possible answer is [1, 6, 2, 5, 3, 4].

*/
/* ****
1. 有等号, 相对于II简单. 可以相邻相等  
	对于II, Given nums = [1, 1, 1, 2, 2, 2], only possible answer is [1, 2, 1, 2, 1, 2].
2. 局部序. 奇数i < i-1 或偶数i > i-1, 需要 i 和 前面的i-1 互换.

303
1. 一遍扫,偶数大于下一个,或奇数小于下一个,换.
2. II为什么不成立?
		比如,偶数大于等一下一个,就换.但这并未完成当前的排序,因为"=" 换了也没解决问题.
		
411
1. 向后查更好, 不用偶数时跳零.
*****/
// 411
public class Solution {
    public void wiggleSort(int[] nums) {
        for(int i=0;i<nums.length - 1;i++){
            if(i%2==1){
                if(nums[i] < nums[i+1]) swap(nums, i);
            }else if(nums[i] > nums[i+1])swap(nums, i);
        }
    }
    
    public void swap(int[] nums, int i){
        int tmp=nums[i];
        nums[i]=nums[i+1];
        nums[i+1]=tmp;
    }
}


//http://www.zrzahid.com/wiggle-sort/
public class Solution {
    public void wiggleSort(int[] nums) {
        for(int i=0;i<nums.length;i++){
            if(i%2==1){
                if(nums[i-1]>nums[i]) swap(nums, i);
            }else if(i!=0 && nums[i-1]<nums[i]){
                swap(nums, i);
            }
        }
    }
    
    // swap i- 1 and i
    public void swap(int[] nums, int i){
        int tmp=nums[i];
        nums[i]=nums[i-1];
        nums[i-1]=tmp;
    }
}