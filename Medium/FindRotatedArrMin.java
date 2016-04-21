/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * You may assume no duplicate exists in the array.
 * 
 * Tags: Array, Binary Search
 */
class FindRotatedArrMin {
    public static void main(String[] args) {
        int[] num = { 3, 4, 5, 6, 0, 1, 2 };
        // int[] num = { 4, 5, 6, 7, 0, 1, 2 };
        System.out.println(findMin(num));
    }
	
	/* ****
	1. 不是找到继续找，所以不同于209开区间， 用闭区间。
	2. 处理dups, 区间不rotate， 区间rotate. 三种情况。
		 第一种， 一步破除。
		 最后一种， 只能处理num[l] > num[r]的情况, 没等号。 否则图想， 逻辑相反。
	*****/
	
	//410
	public int findMin(int[] nums) {
        if(nums == null || nums.length == 0) return 0;
        int l = 0, r = nums.length - 1;
        while(l < r){
			/* dups for II
            if(nums[l] == nums[r]){
                r--;
                continue;
            }*/
            if(nums[l] < nums[r]) return nums[l];
            // rotated
            int mid = l + (r - l)/2;
            if(nums[l] <= nums[mid]) l = mid + 1;
            else r = mid;
        }
        return nums[r];
    }
    
    // Keep consistent with II
    public int findMin(int[] num) {
        if (num == null || num.length == 0) return 0;
        int l = 0;
        int r = num.length - 1;
        while (l < r) {
            // case 1  num[l] == num[r]
            /* 
            int k = r;
            while (k >= l && num[k] == num[l]) k--; // move left dups
            if (k < l) return num[l]; // num[l] = ... = num[r]
            r = k;
            */
            
            // case 2
            if(num[l] < num[r]) return num[l];
            
            // case 3 num[l] > num[r] [l,mid][mid+1,r]
            int mid = l + (r - l)/2; //unique definition for [l,mid][mid+1].
            //If mid = r - (r-l)/2, mid == r, l = mid + 1 may exceeds bound. It's for [l,mid-1][mid,r].
            if(num[mid] >= num[l]) l = mid + 1; //num[l] > num[r] > num[mid] - must be last move. so num[l] is the min.
            //else if(num[mid] == num[l]) l = mid+1;
            else r = mid; //num[l] > num[r] >= num[mid]
        }
        return num[l]; // l == r
        
    }
    
    
    static int findMin(int[] num) {
        int l = 0;
        int r = num.length - 1;
        if (num.length == 1 || num[l] < num[r]) return num[0];
        
        int mid = 0;
        
        /* There's n-1 pairs of adjacent nodes.
         * Rotation brings one pair with reverse order.
         * If the original sequence is in ascending order, the special one is max-min.
         * Any range [left,right] contains this pair must satisify num[left] > num[right].
         * So, num[right] < num[0] < num[left].
         * Meanwhile, if num[left] > num[right], we have max-min within [left, right] range.
         * Try to capture this range with Binary Search.
         */        
        while (l < r) {
            mid = l + (r - l) / 2;
            //if(num[l] > num[mid]) r = mid;
            //else l = mid; //no dups. no "=", num[l] < num[mid]
            //System.out.println(mid);
            if (num[l] < num[mid]) l = mid; // revert to the original solution. when mid == l, should avoid l = mid; dead loop.
            else r = mid;
        }
        return num[l + 1]; //num[l]:max, num[r]:min
    }
    
}