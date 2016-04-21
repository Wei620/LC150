/*
209 Minimum Size Subarray Sum

Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead. 

For example, given the array [2,3,1,2,4,3] and s = 7,
 the subarray [4,3] has the minimal length under the problem constraint. 

click to show more practice.

More practice: 
If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

*/

/*
Since the given array contains only positive integers, the subarray sum can only
increase by including more elements. Therefore, you don't have to include more
elements once the current subarray already has a sum large enough. This gives the
linear time complexity solution by maintaining a minimum window with a two
indices.
*/

/* ****
1. 双指针start, end. end够条件就停. start贪婪右移,挤压长度. 
	终止, end = nums.lenth.
2. end 的 break很高明.
411. 
1. BS 209开区间， 注意 minLen r - i. sums[i]相当于常数。
*****/
//411
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int sum = 0, minLen = Integer.MAX_VALUE;
        for(int start = 0, end = 0; end < nums.length; end++){
            sum += nums[end];
            if(sum < s) continue;
            while(sum - nums[start] >= s) sum -= nums[start++];
            minLen = Math.min(minLen, end - start + 1);
        }
        return minLen == Integer.MAX_VALUE? 0 : minLen;
    }
}

//411 BS
public class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int[] sums = new int[nums.length  + 1];
        for(int i = 1; i <= nums.length; i++){
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        
        int minLen = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length; i++){
            int l = i + 1, r = sums.length;
            while(l < r){
                int mid = l + (r - l)/2;
                if(sums[mid] >= sums[i] + s) r = mid;
                else l = mid + 1;
            }
            if(r == sums.length) break;
            minLen = Math.min(minLen, r - i);
        }
        
        return minLen == Integer.MAX_VALUE? 0 : minLen;
    }
}

public class Solution {
	public int minSubArrayLen(int s, int[] nums) {
		return solveNLogN(s, nums);
	}
	
	private int solveN(int s, int[] nums) {
		int start = 0, end = 0, sum = 0, minLen = Integer.MAX_VALUE;
		while (end < nums.length) {
			while (end < nums.length && sum < s){
				sum += nums[end++];	
			}
			if (sum < s) break; // end == nums.length.
			while (start < end && sum >= s){ // start = end sum < s, 加= ok.
				sum -= nums[start++];
			} // both end and start are over counted.
			if (end - start + 1 < minLen) minLen = end - start + 1;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}

	/*
	As to NLogN solution, logN immediately reminds you of binary search. In this case,
	you cannot sort as the current order actually matters. How does one get an ordered
	array then? Since all elements are positive, the cumulative sum must be strictly
	increasing. Then, a subarray sum can expressed as the difference between two
	cumulative sum. Hence, given a start index for the cumulative sum array, the other
	end index can be searched using binary search.
	*/
	
	/***** BS 一定要找到的标准格式. 找不到,就反比target小的最大数. 或比target大的最小数.
	1. Sum(i) = [0,i) 利于求区间和 Sum[i,j) = Sum(j) - Sum(i)
	2. nums[] 为正， Sum(i)严格增， 可以在[i+1, sums.length - 1]内BS查找 最小的大于等于 Sum(i) + s 的数。
	3. BS时 hi + 1 是 key的上限UB； low - 1 是 key的下限LB。 等号按需要归并（上确界，小确界）。
	4. 找不着时， 最终有lo = hi + 1， 可以简化返回值，但不直观。
	5. 找不着是 返回 UB = hi + 1时， 元素访问UB会上越界， 说明全部元素小于Key； 
		返回LB = low - 1时，元素访问LB会下越界， 说明全部元素大于key。
	*****/
	private int solveNLogN(int s, int[] nums) {
		int[] sums = new int[nums.length + 1];// sum(i) [0,i)
		for (int i = 1; i < sums.length; i++){
			sums[i] = sums[i - 1] + nums[i - 1];
		}
		
		int minLen = Integer.MAX_VALUE;
		for (int i = 0; i < sums.length; i++) {
			int end = binarySearch(i + 1, sums.length - 1, sums[i] + s, sums);
			if (end == sums.length) break;
			if (end - i < minLen) minLen = end - i;
		}
		return minLen == Integer.MAX_VALUE ? 0 : minLen;
	}
	
	//[lo, hi] 找最小的大于等于 key 的数
	private int binarySearch(int lo, int hi, int key, int[] sums) {
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (sums[mid] >= key){
				hi = mid - 1;
			} else {//sums[mid] < key
				lo = mid + 1;
			}
		}
		return lo; // key <= sums[hi + 1] = sums[lo]
	}
}

