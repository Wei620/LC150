/*
287 Find the Duplicate Number

Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.

Note:
You must not modify the array (assume the array is read only).
You must use only constant, O(1) extra space.
Your runtime complexity should be less than O(n2).
There is only one duplicate number in the array, but it could be repeated more than once.
*/

/* ****
因为时间复杂度要求要小于O(n2), 所以不能用朴素的判别。
空间复杂度O(1)所以不能hash，不能修改所以不能排序,可能不止重复一次所以不能n项和.

1.  定义count(x) 统计nums中小于等于x的元素个数。 x的范围[1,n]. O(n)
2. 	朴素解法， 遍历x， 如果 count（x-1)<x-1 && count(x)>x 找到x。 O(n^2)
3.	???
http://bookshadow.com/weblog/2015/09/28/leetcode-find-duplicate-number/

303
1. count(x)表示num里小于等于x的个数.
	num值域被x分割,图想, count(x) > x 说明 [1,x]内有重复.满足条件的最小值就是dups.
	 开区间,返high, 验证### n是否 length
		count(n) = n+1 > n 必然成立,所以不用查. 但最好还是 high = n+1 更统一.

411.
1. 是missing number 的反题
2. 只有 count(num, dups) == dups
*****/



public class Solution {  
    public int findDuplicate(int[] nums) {  
        int n = nums.length-1;  
        int low = 1, high= n;  // exclude n
        int mid = 0;  
        while(low<high) {  
            mid = low + (high-low)/2;  
            int c= count(nums, mid); //count #numbers less or equal than mid.  
            if(c<=mid) {  
                low = mid+1;  
            } else {  
                high = mid;  
            }  
        }  
        return low;  
    }  
    	
    private int count(int[] nums, int x) {  
        int c =0;  
        for(int i=0; i<nums.length; i++) {  
            if(nums[i]<=x) c++;  
        }  
        return c;  
    }  
}  

//The main idea is the same with problem Linked List Cycle II
// nums[A] is one step move, A(current status) -> nums[A] (next status)
int findDuplicate3(int[] nums){
    if (nums.size() > 1)    {
        int slow = nums[0];
        int fast = nums[nums[0]];
        while (slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        fast = 0;
        while (fast != slow){
            fast = nums[fast];
            slow = nums[slow];
        }
        return slow;
    }
    return -1;
}