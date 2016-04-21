/*
199 Binary Tree Right Side View

Given a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom.

For example:
 Given the following binary tree,

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---



You should return [1, 3, 4]. 

*/
/*
The core idea of this algorithm:
1.Each depth of the tree only select one node.
2. View depth is current size of result list.
*/

/*
 *****
 DFS + currDepth vs expected depth.
 1. Right first preorder.
 2. expected = res.size(), 
 3. 当currDepth超过已记录的范围是(currDepth = res.size)更新.
 *****
*/

//411
public class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(root, 0, res);
        return res;
    }
    
    private void helper(TreeNode node, int depth, List<Integer> res){
        if(node == null) return;
        if(depth == res.size()) res.add(node.val);
        helper(node.right, depth + 1, res);
        helper(node.left, depth + 1, res);
    }
}

public class Solution {
	public List<Integer> rightSideView(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		rightView(root, result, 0);
		return result;
	}
	
	public void rightView(TreeNode curr, List<Integer> result, int currDepth){
		if(curr == null){
			return;
		}
		if(currDepth == result.size()){
			result.add(curr.val);
		}
		rightView(curr.right, result, currDepth + 1);
		rightView(curr.left, result, currDepth + 1);
	}
}


public class Solution {
	public List<Integer> rightSideView(TreeNode root) {
		// reverse level traversal
		List<Integer> result = new ArrayList();
		Queue<TreeNode> queue = new LinkedList();
		if (root == null) return result;
		queue.offer(root);
		while (queue.size() != 0) {
			int size = queue.size();
			for (int i=0; i<size; i++) {
				TreeNode cur = queue.poll();
				if (i == 0){
					result.add(cur.val);
				}
				if (cur.right != null) queue.offer(cur.right);
				if (cur.left != null) queue.offer(cur.left);
			}
		}
		return result;
	}
}