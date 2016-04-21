
/*
220 Contains Duplicate III

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the difference between nums[i] and nums[j] is at most t and the difference between i and j is at most k. 
*/

/*****
1. 同法II， 在map只保留 nums[i - k] 到 nums[i]， 这样发现重复就不用比较序号
2. 但此时 要 看 nums[i] 和 区间内节点的最大距离 <= t. 
3. 可以 i-> [nums[i]-k, nums[i] + k]. 但每次需要查找要遍历map的所有entry。
4. nums[i]装到bucket里（size t）, 注意无法求逆，只有有map记录 bucket -> nums[i], 否则set足够。
   计算nums[i]对应的bucket， 扩展bucket - 1， bucket， bucket + 1，
   查map对应的nums[j]们是否在nums[i]的k范围内。

283
1. bucketId(num[i]) -> num[i]. 用map反找, 因为映射不可逆.
		i-k => num[i-k] => bucketID
2. 滑动窗口 [i - k, i]

411
1. 注意 在差的比较中, t要转成long. map unwrap成了int.
*****/
//411
public class Solution {
	public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
		if (k < 1 || t < 0) return false;
		Map<Long, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
		    if (i >= k + 1) {
				map.remove(getBucketId(nums[i - 1 - k], t));
			}

			long bucket = getBucketId(nums[i], t);
			if (map.containsKey(bucket)
				|| (map.containsKey(bucket - 1) && nums[i] <= map.get(bucket - 1) + (long)t)
				|| (map.containsKey(bucket + 1) && nums[i] >= map.get(bucket + 1) - (long)t))
				return true;
			map.put(bucket, nums[i]); // idx diff is k - 1. next round k
		}
		return false;
	}
	
	private long getBucketId(int x, int size){
	    return ((long) x - Integer.MIN_VALUE) / ((long) size + 1);
	}
}

/*
Since there is now a constraint on the range of the values of the elements to be
considered duplicates, it reminds us of doing a range check which is implemented in
tree data structure and would take O(LogN) if a balanced tree structure is used, or
doing a bucket check which is constant time. We shall just discuss the idea using
bucket here.
Bucketing means we map a range of values to the a bucket. For example, if the
bucket size is 3, we consider 0, 1, 2 all map to the same bucket. However, if t == 3,
(0, 3) is a considered duplicates but does not map to the same bucket. This is fine
since we are checking the buckets immediately before and after as well. So, as a rule
of thumb, just make sure the size of the bucket is reasonable such that elements
having the same bucket is immediately considered duplicates or duplicates must lie
within adjacent buckets. So this actually gives us a range of possible bucket size, i.e. t
and t + 1. We just choose it to be t and a bucket mapping to be num / t.
Another complication is that negative ints are allowed. A simple num / t just shrinks
everything towards 0. Therefore,
*/