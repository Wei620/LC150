/**
 * Given a sorted array and a target value, return the index if the target is
 * found. If not, return the index where it would be if it were inserted in
 * order.
 * 
 * You may assume no duplicates in the array.
 * 
 * Here are few examples.
 * [1,3,5,6], 5 → 2
 * [1,3,5,6], 2 → 1
 * [1,3,5,6], 7 → 4
 * [1,3,5,6], 0 → 0
 * 
 * Tags: Array, Binary Search
 */

 /* ****
1. 209， 虽然no dups
2. 找到 >= target 
 *****/
//410 
public class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0) return 0;
        int l = 0, r = nums.length;
        while(l < r){
            int mid = l + (r - l)/2;
            if(nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        return r;
    }
}
 
class SearchInsertPos {
    public static void main(String[] args) {
        
    }
	   
    /**
     * Binary search
     * r = m - 1, l = m + 1
     */
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0) return 0;
        int l = 0;
        int r = A.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (A[m] == target) return m;
            else if (A[m] > target) r = m - 1;
            else l = m + 1;
        }
		// l : greatest lower bound. 
		// last l satisify A[l-1]<target.
		// target should be insert right after l-1. 
		// So return l.
        return l;
    }
}