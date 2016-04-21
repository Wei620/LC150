/**
 * A peak element is an element that is greater than its neighbors.
 * 
 * Given an input array where num[i] ≠ num[i+1], find a peak element and return
 * its index.
 * 
 * You may imagine that num[-1] = num[n] = -∞.
 * 
 * For example, in array [1, 2, 3, 1], 3 is a peak element and your function
 * should return the index number 2.
 * 
 * Note:
 * Your solution should be in logarithmic complexity.
 * 
 * Tags: Array, Binary Search
 */

 /* ****
 1. 三个corn cases. 因为循环里要访问前后元素。
 2. 不算fs， 找到就行， 不用209， 用闭区间。
 3. 非peak后，形成 三个连续元素单调序列， 在最大端继续寻找。
 4. 最后返回 有显示表达的序号。
 *****/
 // 410
 public class Solution {
    public int findPeakElement(int[] nums) {
        if(nums == null || nums.length == 0) return -1;
        
        int n = nums.length;
        if(n == 1) return 0;
        if(nums[0] > nums[1]) return 0;
        if(nums[n -1] > nums[n - 2]) return n - 1;
        
        int l = 1, r = n - 2;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) return mid;
            if(nums[mid] > nums[mid - 1]) l = mid + 1;
            else r = mid - 1;
        }
        return l - 1;
    }
}
 
 class FindPeak {
    
    public static void main(String[] args) {
        int[] num = {1, 2, 1, 3, 1, 4, 1};
        System.out.println(new FindPeak().findPeakElement(num));
    }
    
    /**
     * Binary search for a peak. Other peaks can be ignored. 
     */
    public int findPeakElement(int[] num) {
        if (num == null || num.length == 0) return 0;
        int n = num.length;
        if (n <= 1) return 0;
        // Edge cases: handle the first and last element in num[]
        if (num[0] > num[1]) return 0;
        if (num[n - 1] > num[n - 2]) return n - 1;
        
        int left = 1, right = n - 2; // important!
        while (left < right) { // "=" return right.
            int mid = (right - left) / 2 + left;
            if (num[mid] > num[mid - 1] && num[mid] > num[mid + 1]) return mid;
            else if (num[mid] > num[mid + 1]) right = mid - 1; // left half has one greater element num[mid-1] at least. num[right] > num[right+1] > num[right + 2]
            else left = mid + 1; // right half has one greater element num[mid+1] at least.
			// num[left - 1] < num[left]
        }
		// break condition left == right.
		// So left(right) is the right element that greater than num[left-1], num[left+1] and num[left + 2]
        return left;
    }
}