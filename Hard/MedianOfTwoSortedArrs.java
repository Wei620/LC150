/**
 * There are two sorted arrays A and B of size m and n respectively. Find the
 * median of the two sorted arrays. The overall run time complexity should be
 * O(log (m+n)).
 * 
 * Tags: Divide and Conquer, Array, Binary Search
 */
class MedianOfTwoSortedArrs {
    public static void main(String[] args) {
        MedianOfTwoSortedArrs m = new MedianOfTwoSortedArrs();
        int[] A = {1, 2, 3, 4, 5};
        int[] B = {2, 4, 5, 6, 7};
        System.out.println(m.findMedianSortedArrays(A, B));
    }
    
	//http://www.geeksforgeeks.org/median-of-two-sorted-arrays/
    
	
    
    // Mine
    // http://articles.leetcode.com/find-k-th-smallest-element-in-union-of
    // http://articles.leetcode.com/median-of-two-sorted-arrays/
    public double findMedianSortedArrays(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int[] kkp1 = findElementsWithIdxOfKKp1(A, B, (m + n - 1) / 2);
        if((n+m)%2 == 1){
            return kkp1[0];
        } 
        else{
            return (kkp1[0] + kkp1[1])/2.0;
        }        
    }

    
    private int[] findElementsWithIdxOfKKp1(int[] A, int[] B, int k){
        int n = A.length;
        int m = B.length;
        if (n > m) return findElementsWithIdxOfKKp1(B, A, k); // shorter array first
        
        // j = k - i, 0 <= j <= m - 1
        int iMin = Math.max(0, k - m + 1);
        int iMax = Math.min(n - 1, k);
        int l = iMin, r = iMax;
        
        while(l <= r){
            int i = l + (r - l)/2;
            int j = k - i;

            if(A[i] < B[j]){
                if(A[i] >= getPrevElement(B, j)){
                    return new int[]{A[i], Math.min(B[j], getNextElement(A, i))};
                }
                else{//A[i] < Bj - 1, at most k - 1 elements are in front of A[i]
                    l = i + 1;
                }
            }
            else{//B[j] <= A[i]
                if(B[j] >= getPrevElement(A, i)){
                    return new int[]{B[j], Math.min(A[i], getNextElement(B, j))};
                }
                else{// B[j] < Aj_1, at least k + 1 elements are in front of A[i]. 
                    r = i;
                }
            }
        }
        return new int[]{B[k - l],getNextElement(B, k - l)};
    }
    
    private int getPrevElement(int[] arr, int idx){
        int n = arr.length;
        if(idx <= 0) return Integer.MIN_VALUE;
        if(idx < n) return arr[idx - 1];
        return arr[n-1];
    }
    
    private int getNextElement(int[] arr, int idx){
        int n = arr.length;
        if(idx < 0) return arr[0];
        if(idx < n - 1) return arr[idx + 1];
        return Integer.MAX_VALUE;
    }
    
	
	/**
     * Search in shorter array
     * Find 4 possible candidates A[l-1], A[l], B[k-1], B[k-l+1]
     * If total # of items is odd, return the max of A[l-1] and B[k-l], a
     * If total # of items is even, get the min of A[l] and B[k-l+1], b
     * Return the average of a and b
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        int n = A.length;
        int m = B.length;
        if (n > m) return findMedianSortedArrays(B, A); // shorter array first
        
        int k = (n + m - 1) / 2; // mid position INDEX
        int l = 0, r = Math.min(k, n); // r is n, NOT n-1, this is important!!
        
        // find A[l] > B[k - l]
        while (l < r) {
            int midA = l + (r - l) / 2; // A[i], avoid overflow
            int midB = k - midA; // B[j - 1]
            if (A[midA] < B[midB])//before midB:  # of A >= midA + 1, # of B = midB, total # >= K + 1
                l = midA + 1; // i + 1, r
            else
                r = midA; // l, i
        }
        // A[l-1], A[l], B[k-l], and B[k-l+1] 
        int a = Math.max(l > 0 ? A[l - 1] : Integer.MIN_VALUE, k - l >= 0 ? B[k - l] : Integer.MIN_VALUE);
        if ((n + m) % 2 == 1) return (double) a; // odd

        int b = Math.min(l < n ? A[l] : Integer.MAX_VALUE, k - l + 1 < m ? B[k - l + 1] : Integer.MAX_VALUE);
        return (a + b) / 2.0; // even
    }
}
