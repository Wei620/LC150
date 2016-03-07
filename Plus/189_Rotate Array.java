/*
189 Rotate Array

Rotate an array of n elements to the right by k steps.

For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. 

*/

public class Solution {
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k%n;
        // swap(nums, 0, n); left shift
        swap(nums, 0, n - k);
        swap(nums, n - k, n);
        swap(nums, 0, n);
    }

    
    private void swap(int[] A, int s, int e){
        e--;
        while(s < e){
            int tmp = A[s];
            A[s] = A[e];
            A[e] = tmp;
            s++;
            e--;
        }    
    }
}