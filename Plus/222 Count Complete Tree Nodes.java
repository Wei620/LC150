/*
222 Count Complete Tree Nodes

Given a complete binary tree, count the number of nodes.

Definition of a complete binary tree from Wikipedia:
 In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2^h nodes inclusive at the last level h.

*/


/*****
1. 二叉树 第h（0-based）层最多有2^h个节点。 h是二叉树的高度。 总共最多有2^(h+1) - 1 = 2^h + 2^h - 1 个。
2. 完成树，每层左至右尽量填满。
3. 右树高h-1，左树高h-1的完成树，共2^h - 1个，加根后，数右树。
      高小于h-1,右树高h-2的完成树， 共 2^(h-1)-1个，加根后，数左树。
4. 求2^h， 1<<h
5. O（logn * logn)

238
1. 递归数树高(左).
2. 高h的满树, 节点共有 2^(h+1)+1.

411
1. 注意怎么数complete tree的树高. null 反 -1.
2. 高h的满树, 2^h + 2^h　－　１
*****/


class Solution {
	int height(TreeNode root) {
		return root == null ? -1 : 1 + height(root.left);
	}
	
	//FULL: 2^(h+1) - 1
	public int countNodes(TreeNode root) {
		int h = height(root);
		if(h < 0) return 0;
		return height(root.right) == h-1 ? (1 << h) + countNodes(root.right)
					: (1 << h-1) + countNodes(root.left);//right: h - 2
	}
}

/*
Explanation
The height of a tree can be found by just going left. Let a single node tree have height
0. Find the height h of the whole tree. If the whole tree is empty, i.e., has height -1,
there are 0 nodes.
Otherwise check whether the height of the right subtree is just one less than that of
the whole tree, meaning left and right subtree have the same height.
If yes, then the last node on the last tree row is in the right subtree and the left
subtree is a full tree of height h-1. So we take the 2^h-1 nodes of the left subtree
plus the 1 root node plus recursively the number of nodes in the right subtree.
If no, then the last node on the last tree row is in the left subtree and the right
subtree is a full tree of height h-2. So we take the 2^(h-1)-1 nodes of the right
subtree plus the 1 root node plus recursively the number of nodes in the left
subtree.
Since I halve the tree in every recursive step, I have O(log(n)) steps. Finding a height
costs O(log(n)). So overall O(log(n)^2).
*/