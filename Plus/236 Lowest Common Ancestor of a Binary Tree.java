/*
236 Lowest Common Ancestor of a Binary Tree

Given a binary tree, find the lowest common ancestor (LCA) of two given nodes in the tree. 

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).” 
        _______3______
       /              \
    ___5__          ___1__
   /      \        /      \
   6      _2       0       8
         /  \
         7   4


For example, the lowest common ancestor (LCA) of nodes 5 and 1 is 3. Another example is LCA of nodes 5 and 4 is 5, since a node can be a descendant of itself according to the LCA definition.

*/

/*****
1. ==, 所以可以处理与p,q值相等的情况
2. 找第一个左右都包含的情况， 返回此节点。
		左右都不包含，null
		左右只有一个， 返回p or q
		左右都有，返回该节点
283
1.	BST top down, interative
2.	BT bottom up, recursive
		base 融合p,q 很巧妙 early termination
3.  只有4个返回值, null, p, q, lca
		此题要无重复,否则 left = p, right = lca
4.	找到后, left(right) = lca, 对方right(left) 必然为null. 因为无重复节点.

411
1. 只存在唯一的p和q.
2. 优先返回非零结果.
		
*****/

public class Solution {
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if(root == null || root == p || root == q) return root;
		//根不是pq
		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		
		if(left != null && right != null) return root; //找到, 
		return left != null ? left : right;// left = right = null, return null
	}
}