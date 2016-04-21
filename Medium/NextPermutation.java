import java.util.*;

/**
 * Implement next permutation, which rearranges numbers into the
 * lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest
 * possible order (ie, sorted in ascending order).
 * 
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its
 * corresponding outputs are in the right-hand column.
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * 
 * Tags: Array
 */
class NextPermutation {
    public static void main(String[] args) {
        
    }
	
	/*****
	1. 从后向前找递减区间。 从区间里找到比区外（相邻）点大的数。 至少存在一个，是（i+1）。
		 swap， reverse.
	*****/
    
    /**
     * O(n) Time
     * Move pointer to second last element of ascending order 
     * From that index, find the farthest element that is bigger
     * Swap these two elements
     * Reverse the order from the next index
     */
    public void nextPermutation(int[] num) {
        if (num == null || num.length == 0) return;
        for (int i = num.length - 2; i >= 0; i--) {
            if (num[i] < num[i + 1]) {  //step 1 - find peak
                int j = num.length - 1;
                for (; j > i; j--) if (num[j] > num[i]) break;
                swap(num, i, j);  // step 2
                reverse(num, i + 1); //step 3
                return;
            }
        }
        reverse(num, 0); 
        return;
    }
    
    private void swap(int[] num, int i, int j) {
        int t = num[i];
        num[i] = num[j];
        num[j] = t;
    }
    
    private void reverse(int[] num, int s) {
        int e = num.length - 1;
        while (s < e) {
            swap(num, s, e);
            s++;
            e--;
        }
    }
}
