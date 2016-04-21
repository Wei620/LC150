/*
314 Binary Tree Vertical Order Traversal

Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the order should be from left to right.

Examples:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
 

return its vertical order traversal as:

[
  [9],
  [3,15],
  [20],
  [7]
]
 

Given binary tree [3,9,20,4,5,2,7],

    _3_
   /   \
  9    20
 / \   / \
4   5 2   7
 

return its vertical order traversal as:

[
  [4],
  [9],
  [3,5,2],
  [20],
  [7]
]
*/

/*****
1. column标号， 按标号输出。
2. 图想，标号只能从root开始； 如果从最做开始，下一列（右子，父-难找）。
	root 表0， 左标 -1， 右边+1， 标号不是自然数，不能数组， 只能map<标号， List<>>, list是列。
3. 从root开始bfs遍历标号， 还需要一个map<node, 标号>, 这样node可以找到所在列，并添加。
4. 标号的同时记录最小标号， 循环输出map.get(min++)， 直至key不含min

303
1. DFS, 错， preorder， postorder（右树越界左伸）都不行， 列里的添加顺序不对。 
2. 一定bfs，入队时存入w，出对时找回。

411
1. 必须需要 weight（node -> col）
   无法用Queue<int[]>
*****/


public class Solution {
public List<List<Integer>> verticalOrder(TreeNode root) {
	List<List<Integer>> res = new ArrayList<>();
	if (root == null) {
		return res;
	}
	
	//map's key is column, we assume the root column is zero, the left node wi	ll minus 1 ,and the right node will plus 1
	HashMap<Integer, ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	Queue<TreeNode> queue = new LinkedList<>();
	//use a HashMap to store the TreeNode and the according cloumn value
	HashMap<TreeNode, Integer> weight = new HashMap<TreeNode, Integer>();
	
	queue.offer(root);
	weight.put(root, 0);
	int min = 0; // when output the arraylist from map, need to start with min.
	
	while (!queue.isEmpty()) {
		TreeNode node = queue.poll();
		int w = weight.get(node);
		
		if (!map.containsKey(w)) {
			map.put(w, new ArrayList<>());
		}
		map.get(w).add(node.val);
		
		if (node.left != null) {
			queue.add(node.left);
			weight.put(node.left, w - 1);
		}
		
		if (node.right != null) {
			queue.add(node.right);
			weight.put(node.right, w + 1);
		}
		//update min ,min means the minimum column value, which is the left most node
		min = Math.min(min, w);
	}
		
	while (map.containsKey(min)) {
		res.add(map.get(min++));
	}
	return res;
}
}