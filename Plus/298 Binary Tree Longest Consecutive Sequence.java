/*
298 Binary Tree Longest Consecutive Sequence

Given a binary tree, find the length of the longest consecutive sequence path.

 

The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).

For example,

   1
    \
     3
    / \
   2   4
        \
         5
Longest consecutive sequence path is 3-4-5, so return 3.

   2
    \
     3
    / 
   2    
  / 
 1
Longest consecutive sequence path is 2-3,not3-2-1, so return 2.


Time Complexity - O(n)，  Space Complexity - O(n)。
*/

/*****
###
1. 	Top-down 简单.
2. root和tartet不匹配时, 重新开始, cur = 1
*****/

public class Solution {
private int max = 0;
public int longestConsecutive(TreeNode root) {
	if(root == null) return 0;
	helper(root, 0, root.val);
	return max;
}

//Top-down
public void helper(TreeNode root, int cur, int target){
	if(root == null) return;
	if(root.val == target) cur++;
	else cur = 1;
	max = Math.max(cur, max);
	helper(root.left, cur, root.val + 1);
	helper(root.right, cur, root.val + 1);
}

//Bottom-up my solution
public int helper(TreeNode node){
	if(node.left == null && node.right == null) return 1;
	int lLen = 0, rLen = 0;
	if(node.left != null && node.left.val = node.val + 1) lLen = helper(node.left);
	if(node.right != null && node.right.val = node.val + 1) lLen = helper(node.right);
	return Math.max(lLen, rLen) + 1;
	if(node.leftlLen = helper(node.left);	
}


}