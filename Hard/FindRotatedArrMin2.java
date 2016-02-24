/**
 * Follow up for "Find Minimum in Rotated Sorted Array":
 * What if duplicates are allowed?
 * Would this affect the run-time complexity? How and why?
 * 
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * The array may contain duplicates.
 * 
 * Tags: Array, Binary Search
 */
class FindRotatedArrMin2 {
    public static void main(String[] args) {
        // int[] num = { 2, 3, 3, 4, 5, 6, 7, 0, 0, 0, 1, 1, 2, 2, 2 };
        // int[] num = { 3, 3, 1 };
        int[] num = { 10, 1, 10, 10, 10 };
        System.out.println(new FindRotatedArrMin2().findMin(num));
    }
    
    /**
     * Skip all the indentical elements on the left in each search
     */
    // [l, r] = [l, m] + [m+1, r]
    public int findMin(int[] num) {
        if (num == null || num.length == 0) return 0;
        int l = 0;
        int r = num.length - 1;
        while (l < r) {
            int k = l;
            while (k <= r && num[k] == num[r]) k++; // move left dups
            if (k > r) return num[l]; // num[l] = ... = num[r]
            l = k;  //line 31-34 can move right dups intead.
            if (num[l] < num[r]) return num[l];
            // num[l] > num[r]
            int mid = l + (r - l) / 2; // mid == l
            if (num[mid] >= num[l]) l = mid + 1; // left half sorted, min is not here.
            else r = mid;   // num[l] > num[mid], left half unsorted, min is here.
        }
        return num[l]; // l == r
    }
}
