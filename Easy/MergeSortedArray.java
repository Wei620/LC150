/**
 * Given two sorted integer arrays A and B, merge B into A as one sorted array.
 * 
 * Note:
 * You may assume that A has enough space (size that is greater or equal to m +
 * n) to hold additional elements from B. The number of elements initialized in
 * A and B are m and n respectively.
 * 
 * Tags: Array
 */
 /* ****
 1. B merge to A.
 2. 数组，移动最少， 从后向前合并。 m， n自减， 妙！
 3. 记得处理B中的剩余元素。
 *****/
 
class MergeSortedArray {
    public static void main(String[] args) {
        
    }
    
    /**
     * Merge from behind
     */
    private void merge(int A[], int m, int B[], int n) {
		if (n == 0) return;
        while (m > 0 && n > 0) {
            if (A[m - 1] > B[n - 1]) {
                A[m + n - 1] = A[m - 1];
                m--;
            } else {
                A[m + n - 1] = B[n - 1];
                n--;
            }
        }
        /*still elements in B*/		 //B里的较小
        while (n > 0) A[n - 1] = B[n-- - 1];
    }
}
