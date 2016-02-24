/**
 * Follow up for "Search in Rotated Sorted Array":
 * What if duplicates are allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * 
 * Write a function to determine if a given target is in the array.
 * 
 * Tags: Array, Binary Search
 */
class SearchInRotatedSortedArray2 {
    public static void main(String[] args) {
        
    }
    
    /**
     * Clarification: non-descending order
     * Ends up same as sequential search at worst. 
     */
    public boolean search(int[] A, int target) {
        if (A == null || A.length == 0) return false;
        int l = 0;
        int r = A.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
			/*
			 * [l,m][m,r]
			 * target vs A[m]
			 * BS: target < A[m], target in [l,m]
			 * Rotate: m may less than l, but in [m,r]instead.
			 * So, check if target is in the sorted half
			 * case 1. left sorted. A[l] < A[m]
			 * case 2. right sorted. A[m] < A[r]
			 * Update [l,r] to capture the potential target.
			 */
            if (A[m] == target) return true;
            /*skip*/  // m != target in 3 skip cases.
            if(A[l] == A[m] && A[m] == A[r]) {
                l++;  // pattern -'-   fast escape
                r--; // not necessary, but considering line 36, it's ok.
            } else if(A[l] == A[m]) l = m + 1; // 1) l == m 2)A[l] = ... = A[m] 
            else if(A[m] == A[r]) r = m - 1; //1) r == m 2)A[m] = ... = A[r] 
			// A[l], A[r], A[m] all different.
            else if (A[l] < A[m]) { // left half sorted
                if (A[l] <= target && target < A[m]) r = m - 1;
                else l = m + 1;
            } else if (A[l] > A[m]) { // right half sorted
                if (A[m] < target && target <= A[r]) l = m + 1;
                else r = m - 1;
            }
        }
        return false;
    }
}