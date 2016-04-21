/*
300 Longest Increasing Subsequence

Given an unsorted array of integers, find the length of longest increasing subsequence. 

For example,
 Given [10, 9, 2, 5, 3, 7, 101, 18],
 The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4. Note that there may be more than one LIS combination, it is only necessary for you to return the length. 

Your algorithm should run in O(n2) complexity. 

Follow up: Could you improve it to O(n log n) time complexity? 

*/

/* ****
1. 	利用Arrays的静态binarySearch. 插入点 = -返回值-1
2.  若插入点=len, len++; 
3.  重复无影响.
4.  需自己实现有重复的BS(前面有) ###

303
1. 	dp[i] 表示长度为i+1的递增子序列中, 尾数的最小值.
	图想， dp[]是增函数（非严格， 因为递增子序列不严格）。 dp的实际长度，就是所求LIS
2. 重复BS， 209开区间形式，找到dp中比当前数大（非严格，无等号）的最小数。 22.LIS = 2.
		最小数，朝左找， dp[mid] > num, 返 r。
3.	返回r == len（越界情况）， len++

411
1.  尾数的最小值, 重复也更新
*****/
//411
public class Solution {
    public int lengthOfLIS(int[] nums) {
        ArrayList<Integer> lastCharOfLIS = new ArrayList<>(nums.length);
        for(int num : nums){
            int i = bs(lastCharOfLIS, num);
            if(i == lastCharOfLIS.size()) lastCharOfLIS.add(num);
            else lastCharOfLIS.set(i, num);
        }
        return lastCharOfLIS.size();
    }
    
    private int bs(ArrayList<Integer> list, int num){
        int l = 0, r = list.size();
        while(l < r){
            int mid = l + (r - l)/2;
            if(num <= list.get(mid)) r = mid; //序列里不能重复, 所以=
            else l = mid + 1;
        }
        return r;
    }
}


public class Solution {
    public int lengthOfLIS(int[] nums) {            
        int[] dp = new int[nums.length];
        int len = 0;

        for(int x : nums) {
			/*
			index of the search key, if it is contained in the array within the specified range; otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element in the range greater than the key, or toIndex if all elements in the range are less than the specified key. 
			*/
            int i = Arrays.binarySearch(dp, 0, len, x);
            if(i < 0) i = -(i + 1); //insertion point
            dp[i] = x;
            if(i == len) len++;
        }

        return len;
    }
	
	//209
	private int bs(int[] nums, target){
		int l = 0, r = nums.length();
		whle(l < r){
			int mid = l + (r - l)/2;
			if(nums[mid] <= target)	r = mid;
			else l = mid + 1;
		}
		return r;
	}
	
}

/*
The idea is that as you iterate the sequence, you keep track of the minimum value a subsequence of given length might end with, for all so far possible subsequence lengths. So dp[i] is the minimum value a subsequence of length i+1 might end with. Having this info, for each new number we iterate to, we can determine the longest subsequence where it can be appended using binary search. The final answer is the length of the longest subsequence we found so far.
*/