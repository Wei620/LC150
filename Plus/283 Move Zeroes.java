/*
283 Move Zeroes

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].

Note:
You must do this in-place without making a copy of the array.
Minimize the total number of operations.
*/

/*****
1. 标准同向双指针移动

411
1. 最后要把len后的全填零
*****/

public void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 0) return;
    int len = 0;
    for (int num: nums) {
        if (num != 0) nums[len++] = num;
    }
    while (len < nums.length) {
        nums[len++] = 0;
    }
}