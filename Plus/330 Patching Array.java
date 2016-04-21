/*
330 Patching Array

Given a sorted positive integer array nums and an integer n, add/patch elements to the array such that any number in range [1, n] inclusive can be formed by the sum of some elements in the array. Return the minimum number of patches required. 

Example 1:
nums = [1, 3], n = 6
 Return 1.

Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3].
 Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 So we only need 1 patch.

Example 2:
nums = [1, 5, 10], n = 20
 Return 2.
 The two patches can be [2, 4].

Example 3:
nums = [1, 2, 2], n = 5
 Return 0.

*/

/*****
1.	个数怪题，用dp。
2.	对于连续区间[1,currMaxSum), 添加add （add <= currMaxSum）, 可把连续区间扩展至[1, currMaxSum + add). 
3.	最小patch数 <=> 尽量用nums里的，所以
		若 nums有这样的add, 更新 currMaxSum.
		否则， 引入最大扩展 patch = currMaxSum, Cnt++
			同时 currMaxSum *= 2.
4.	nums里找add，应尽可能大。 遍历 or BS？
5.	sum值都可能越界，要用long。

	
*****/

public class Solution {
    public int minPatches(int[] nums, int n) {
        int cnt = 0,i=0;
		long currMaxSum = 1;
        while(currMaxSum <= n){
            if(i < nums.length && nums[i] <= currMaxSum){
                currMaxSum += nums[i++];
            }else{
                currMaxSum <<= 1;
                cnt++; // add currMaxSum
            }
        }
        return cnt;
    }
}

/*
然后参考了discuss的思路：https://leetcode.com/discuss/82822/solution-explanation

就是用currMaxSum表示已知的连续和为[1,currMaxSum)，有了这个表示那就简单了：

nums[i] <= currMaxSum，更新已知范围为：[1,currMaxSum + nums[i] ), nums[0] to nums[i-1] was covered already.
nums[i] >  currMaxSum,  添加currMaxSum进数组才能达到最大的范围，所以已知范围更新为：[1,currMaxSum *2), add currMaxSum+1 will create a hole.
*/

//? bs找边界。
public class Solution {
    public int minPatches(int[] nums, int n) {
        int cnt = 0,i=0;
		long currMaxSum = 1;
		int lo = 0, hi = nums.length-1;
        while(currMaxSum <= n){
            int idx = bs(nums, lo, hi, (int)currMaxSum);
            if(idx < lo){
                currMaxSum <<= 1;
                cnt++;
            }
            else{
                currMaxSum += nums[idx];
                lo = idx + 1;
            }
            
            /*
            if(i < nums.length && nums[i] <= currMaxSum){
                currMaxSum += nums[i++];
            }else{
                currMaxSum <<= 1;
                cnt++; // add currMaxSum
            }*/
        }
        return cnt;
    }
    
    private int bs(int[] nums, int lo, int hi, int target){
        while(lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(nums[mid] == target) return mid;
            if(nums[mid] > target) hi = mid - 1;
            else lo = mid + 1;
        }
        return lo - 1;
    }
}