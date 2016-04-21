/*
226 Invert Binary Tree

Invert a binary tree. 
     4
   /   \
  2     7
 / \   / \
1   3 6   9

to 
     4
   /   \
  7     2
 / \   / \
9   6 3   1
Trivia:
 This problem was inspired by this original tweet by Max Howell: 
Google: 90% of our engineers use the software you wrote (Homebrew), but you can’t invert a binary tree on a whiteboard so fuck off.
*/

/*****
Top-down
1. DFS, preorder. 左右已换，所以先呀左，再压右（原左）。
2. BfS, 形式完全一样，stack换queue
3. 为什么用final？

411
1. DFS 没有用常规一直压左树的方法, 因为有swap, 很麻烦.
       preorder结合stack 是iterative的变形, 很新颖. 其实和BFS同形式.
	   注意是如何获得original left的.
*****/

// DFS
public class Solution {
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		
		final Deque<TreeNode> stack = new LinkedList<>();
		stack.push(root);
		while(!stack.isEmpty()) {
			final TreeNode node = stack.pop();
			final TreeNode left = node.left;
			node.left = node.right;
			node.right = left;
			if(node.left != null) { 
				stack.push(node.left);
			}
			if(node.right != null) {
				stack.push(node.right); // has switched. original left subtree
			}
		}
		return root;
	}
}

// BFS
public class Solution {
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		final Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while(!queue.isEmpty()) {
			final TreeNode node = queue.poll();
			final TreeNode left = node.left;
			node.left = node.right;
			node.right = left;
			if(node.left != null) {
				queue.offer(node.left);
			}
			if(node.right != null) {
				queue.offer(node.right);
			}
		}
		return root;
	}
}

public class Solution {
	public TreeNode invertTree(TreeNode root) {
		if (root == null) {
			return null;
		}
		final TreeNode left = root.left,
		right = root.right;
		root.left = invertTree(right);
		root.right = invertTree(left);
		return root;
	}
}

/*
The above solution is correct, but it is also bound to the application stack, which
means that it's no so much scalable - (you can find the problem size that will
overflow the stack and crash your application), so more robust solution would be to
use stack data structure.
*/
