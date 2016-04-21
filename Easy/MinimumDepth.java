/**
 * Given a binary tree, find its minimum depth.
 * 
 * The minimum depth is the number of nodes along the shortest path from the
 * root node down to the nearest leaf node.
 * 
 * Tags: Tree, DFS
 */
/* ****
1. 	BFS while-for
2.  递归， base不能返 MAX， 否则叶子depth overflow. 
			只能靠判断 子树高度为零。
*****/
class MinimumDepth {
    public static void main(String[] args) {
        
    }
	
	public int minDepth(TreeNode root) {
		if(root == null) return 0;
		Queue<TreeNode> q = new Queue<>();
		q.offer(root);
		int depth = 0;
		
		while(!q.isEmpty()){
			int sz = q.size();
			depth++;
			for(int i = 0; i < sz; i++){
				TreeNode curr = q.poll();
				if(curr.left == null && curr.right == null) return depth;
				if(curr.left != null) q.offer(curr.left);
				if(curr.right != null) q.offer(curr.right);
			}
		}
	}
    
    /**
     * Recursive
     * Get minDepth of left and right subtree
     * If one side is 0, return the other side plus 1
     * Return the smaller one + 1
     */
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        if (left == 0) return right + 1;	// the only difference compared to the maxDepth()
        if (right == 0) return left + 1;
        return Math.min(left, right) + 1; // plus root
    }
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}