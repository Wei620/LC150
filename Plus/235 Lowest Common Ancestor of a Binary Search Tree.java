/*
235 Lowest Common Ancestor of a Binary Search Tree

Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST. 

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T that has both v and w as descendants (where we allow a node to be a descendant of itself).” 
        _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5


For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.

*/

/* ****
1.	BT, 只有LCA才会lt,rt都找到p,q之一. 递归.
2.	只有LCA,p,q才会首次出现在异侧. BST出现在异侧的条件, (rv - p)*(rv - q) < 0.

411
0. top-down
1. 循环条件, 出现在同侧. 当root为p,q或, 出现异侧,就返回.
*****/

public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
	while ((root.val - p.val) * (root.val - q.val) > 0)
		root = p.val < root.val ? root.left : root.right; //同侧
	return root; // <=
}

