/*
307 Range Sum Query - Mutable

Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
The update(i, val) function modifies nums by updating the element at index i to val. 
Example:

Given nums = [1, 3, 5]

sumRange(0, 2) -> 9
update(1, 2)
sumRange(0, 2) -> 8



Note:

1.The array is only modifiable by the update function.
2.You may assume the number of calls to update and sumRange function is distributed evenly.

*/

public class NumArray {
	Bit bit;
    public NumArray(int[] nums) {
        bit = new Bit(nums.length);
        for(int i = 0; i < nums.length; i++){
            update(i, nums[i]);
        }
    }

    void update(int i, int val) {
        int diff = val - sumRange(i,i);
		bit.update(i + 1, diff);
    }

    public int sumRange(int i, int j) {
		return bit.sum(j+1) - bit.sum(i);        
    }
	
	class Bit{
		int size;
		int[] tree;
		public Bit(int sz){
			size = sz + 1;
			tree = new int[size];
		}
		
		private int lowBit(int idx){
			return idx & (-idx);
		}
		
		public void update(int idx, int diff){
			for(int i = idx; 0 < i && i < size; i += lowBit(i)){
				tree[i] += diff;
			}
		}
		
		public int sum(int idx){
			int sum = 0;
			for(int i = idx; 0 < i && i < size; i -= lowBit(i)){
				sum += tree[i];
			}
			return sum;
		}				
	}
}
