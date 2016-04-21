/**
 * Given a sorted array of integers, find the starting and ending position of a
 * given target value.
 * 
 * Your algorithm's runtime complexity must be in the order of O(log n).
 * 
 * If the target is not found in the array, return [-1, -1].
 * 
 * For example,
 * Given [5, 7, 7, 8, 8, 10] and target value 8,
 * return [3, 4].
 * 
 * Tags: Array, Binary Search
 */
class SearchForARange {
    public static void main(String[] args) {
        SearchForARange s = new SearchForARange();
        int[] A = { 1, 2, 3, 3, 3, 3, 3, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        int[] range = s.searchRange(A, 3);
        System.out.println(range[0] + " ~ " + range[1]);
    }
	
	/* ****
	1. 209 基本型
	2. 找下限， 找到后继续向左， 挪r
	   找上限， 找到后继续向右， 挪l
	3. 找不到，越界。 r没动。
				  l没动。
	4. 基本型合并了等号， 所以还要验证最后找到的 = target？
	*****/
	
	public int[] searchRange(int[] A, int target) {
		int l = 0, r = A.length;
		int[] res = new int[2];
		while(l < r){
			int mid = l + (r - l)/2;
			if(A[mid] >= target) r = mid; //开区间，不再看mid
			else l = mid + 1;
		}
		res[0] = r;
		if(r == A.length || A[r] != target) res[0] = -1; 
		
		l = 0; r = A.length; 
		while(l < r){
			int mid = l + (r - l)/2;
			if(A[mid] <= target) l = mid + 1;
			else r = mid;
		}
		res[1] = l - 1;
		if(l == 0 || A[l - 1] != target) res[1] = -1;
		
		return res;	
    }
    
    /**
     * Suppose we have a binary search helper method
     * With array, start index, end index, and target as arguments
     * We can first search for the target in the whole array
     * If found, then search for its starting position
     * Then search for its ending position
     * Update range with search result and return
     */
    public int[] searchRange(int[] A, int target) {
        int[] range = {-1, -1};
        if (A == null || A.length == 0) return range;
        int index = binarySearch(A, 0, A.length - 1, target);
        if (index != -1) {
            int left = index;
            int right = index;
            range[0] = left; // if no more occurrence, set left and right first
            range[1] = right;
            while ((left = binarySearch(A, 0, left - 1, target)) != -1) range[0] = left;
            while ((right = binarySearch(A, right + 1, A.length - 1, target))  != -1) range[1] = right;
        }
        return range;
    }
    
    private int binarySearch(int[] A, int left, int right, int target) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] == target) return mid;
            else if (A[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }
}
