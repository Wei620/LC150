/*
270 Closest Binary Search Tree Value

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Note:

Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
链接： http://leetcode.com/problems/closest-binary-search-tree-value/

题解：

求BST中跟target最近的数字。我们先设置一个min = root.val，然后用iterative的办法尝试更新min， 然后比较target与root的大小，进行二分查找。

Time Complexity - O(logn)， Space Complexity - O(1)。
*/

/* ****
1. BST遍历找target， 直到null位置。
2. target是double， 无法写 root.val == target的情况。
*****/
//411
public class Solution {
    public int closestValue(TreeNode root, double target) {
        int closest = root.val;
        while(root != null){
            if(Math.abs(target - root.val) < Math.abs(target - closest)){
                closest = root.val;
            }
            root = root.val > target? root.left: root.right;
        }
        return closest;
    }
}

public class Solution {
    public int closestValue(TreeNode root, double target) {
        int a = root.val;
        TreeNode child = target < a ? root.left : root.right;
        if (child == null) return a;
        int b = closestValue(child, target);
        return Math.abs(a - target) < Math.abs(b - target) ? a : b;
    }
}