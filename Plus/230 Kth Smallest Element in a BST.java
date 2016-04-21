/*
230 Kth Smallest Element in a BST

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Note: 
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Follow up:
 What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Hint:
1.Try to utilize the property of a BST.
2.What if you could modify the BST node's structure? we could add a count field in the BST node class,
3.The optimal runtime complexity is O(height of BST).

*/

/*****
1.  看smallest， 所以只要看左树。 左树上的所有节点均小于root， 如果左树上节点个数 count = k-1，root就是第Kth smallest.
2.  一旦去右树，就找 k - count(左树) - 1（跟）。

238
1.	递归数节点。

411
1. 递归数节点,base 0, 递归数树高 base -1.
*****/

//Binary Search (dfs): most preferable
public int kthSmallest(TreeNode root, int k) {
	int count = countNodes(root.left);
	if (k <= count) return kthSmallest(root.left, k);
	if (k == count + 1) return root.val;
	return kthSmallest(root.right, k - (1 + count)); 
}

public int countNodes(TreeNode n) {
	if (n == null) return 0;
	return 1 + countNodes(n.left) + countNodes(n.right);
}


//DFS in-order iterative:
public int kthSmallest(TreeNode root, int k) {
	Stack<TreeNode> st = new Stack<>();
	while (root != null) {
		st.push(root);
		root = root.left;
	}
	while (k != 0) {
		TreeNode n = st.pop();
		k--;
		if (k == 0) return n.val;
		//TreeNode right = n.right;
		while (n.right != null) {
			st.push(n.right);
			//right = right.left;
		}
	}
	return -1; // never hit if k is valid
}