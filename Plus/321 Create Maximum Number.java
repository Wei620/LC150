/*
321 Create Maximum Number

Given two arrays of length m and n with digits 0-9 representing two numbers. Create the maximum number of length k <= m + n from digits of the two. The relative order of the digits from the same array must be preserved. Return an array of the k digits. You should try to optimize your time and space complexity. 

Example 1:


nums1 = [3, 4, 6, 5]
 nums2 = [9, 1, 2, 5, 8, 3]
 k = 5
 return [9, 8, 6, 5, 3] 

Example 2:


nums1 = [6, 7]
 nums2 = [6, 0, 4]
 k = 5
 return [6, 7, 6, 0, 4] 

Example 3:


nums1 = [3, 9]
 nums2 = [8, 9]
 k = 3
 return [9, 8, 9] 

*/

/**
 * Created by hrwhisper on 2015/11/23.
 * https://www.hrwhisper.me/leetcode-create-maximum-number/
 */
 
/*****
1.	总共要k个元素， 其中i个出自num1，k-i个出租num2， 枚举所有情况， 找到最大的数。
2.  从num中找i个最大序列， 也就是维持原先后关系的最大数。
		一遍扫描，尽可能（要找够i个）把大数字往前放。
			堆栈。curr大于栈顶，出栈。 但前提是栈里个数和栈外个数大于k个（没有=， 准备出栈顶的）。
					   否则， 如果栈内不到k，入栈。
			因为栈最大k长，可用数组实现。
3.  两个最大序列merge， 大数靠前。相等？后面的数大的靠前。需要比较当前位及以后的数字。
		greater 函数。 相等看后面，一直相等，长序列大， 因为长序列取走第一个， 补位后可与短序列再比较。
		
*****/

public class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        for (int i = Math.max(k - nums2.length, 0); i <= Math.min(nums1.length, k); i++) {
            int[] res1 = get_max_sub_seq(nums1, i);
            int[] res2 = get_max_sub_seq(nums2, k - i);
            int[] res = new int[k];
            int pos1 = 0, pos2 = 0, tpos = 0;

            while (pos1 < res1.length || pos2 < res2.length) {
                res[tpos++] = greater(res1, pos1, res2, pos2) ? res1[pos1++] : res2[pos2++];
            }

            if (!greater(ans, 0, res, 0))
                ans = res;
        }

        return ans;
    }

    public boolean greater(int[] nums1, int start1, int[] nums2, int start2) {
        while(start1 < nums1.length && start2 < nums2.length) {
            if (nums1[start1] > nums2[start2]) return true;
            if (nums1[start1] < nums2[start2]) return false;
			start1++;
			start2++
        }
        return start1 != nums1.length;
    }

    public int[] get_max_sub_seq(int[] nums, int k) {
        int[] res = new int[k];
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            while (len > 0 && len + (nums.length - i) > k /* remaining is enough to fill the stack*/ && res[len - 1] < nums[i]) {
                len--; // pop stack
            }
            if (len < k)
                res[len++] = nums[i];
        }
        return res;
    }
}

/*
To find the maximum ,we can enumerate how digits we should get from nums1 , we suppose it is i.

So ,  the digits from nums2 is K – i.

And we can use a stack to get the get maximum number(x digits) from one array.（just like leetcode Remove Duplicate Letters）

OK, Once we choose two maximum subarray , we should combine it to the answer.

It is just like merger sort, but we should pay attention to the case: the two digital are equal.

we should find the digits behind it to judge which digital we should choose now.

In other words,we should judge which subarry is bigger than the other.

That’s all.

The algorithm is O((m+n)^3) in the worst case.
*/