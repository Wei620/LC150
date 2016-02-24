/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * You are given a target value to search. If found in the array return its
 * index, otherwise return -1.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * Tags: Array, Binary Search
 */
class SearchRotatedSortedArr {
    public static void main(String[] args) {
        
    }
    
    /**
     * Binary Search. 
     * Check which half is sorted.
     * If target is within that half, search in that half. 
     * If not, search in the other half. 
     */
    
    // Explictly handle A[m]== A[l] or A[r], don't care the definition of m.    
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) return -1;
        int l = 0;
        int r = A.length - 1;
        while (l <= r) {
            int m = l + (r - l) / 2;
            /*
             * [l,m-1][m][m+1,r]
             * target vs A[m]
             * BS: target < A[m], target in [l,m-1]
             * Rotate: m may less than l, but in [m+1,r]instead.
             * So, check if target is in the sorted half
             * case 1. left sorted. A[l] < A[m]
             * case 2. right sorted. A[m] < A[r]
             * Update [l,r] to capture the potential target.
             */
            if (A[m] == target) return m;
            /*skip*/  // m != target in 3 skip cases.
            /*if(A[l] == A[m] && A[m] == A[r]) {
                l++;  // pattern -'-   fast escape
                r--; // not necessary, but considering line 36, it's ok.
            } else*/ if(A[l] == A[m]) l = m + 1; // 1) l == m 2)A[l] = ... = A[m] 
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
        return -1;    
    }
    public int search(int[] A, int target) {
        if (A == null || A.length == 0) return -1;
        int l = 0;
        int r = A.length - 1;
        int m;
        while (l <= r) {
            m = l + (r - l) / 2;
            if (A[m] == target) return m;
            if (A[m] >= A[l]) { // left half sorted, why "="? see line 30, m will equals to l. search in the sorted.
                if (target >= A[l] && target < A[m]) {
                    r = m - 1;
                } else l = m + 1;
            } else { // right half sorted
                if (target > A[m] && target <= A[r]) {
                    l = m + 1;
                } else r = m - 1;
            }
        }
        return -1;
    }
}