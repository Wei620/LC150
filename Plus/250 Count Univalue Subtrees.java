/*
250 Count Univalue Subtrees
Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

For example:

Given binary tree,

              5
             / \
            1   5
           / \   \
          5   5   5
return 4.
*/

/* ****
1. top-down - better. 所有相同,在于pass下来的val比较
2. 注意图示中的右子树也是.
3. bottom-up
   int helper(TreeNode node){
	   if(node == null) return Integer.MIN_VALUE;
	   
	   int lVal = helper(node.left);
	   int rVal = helper(node.right);
	   if((lVal == Integer.MIN_VALUE || lVal == node.val) &&
			(rVal == Integer.MIN_VALUE || rVal == node.val)){
			count++;
			return node.val;
		}
   }

293
1.	记得数数.

411
1. 子树单另算.
2. 其实是bottom-up. 
		先查是否等于root.val, 
		再通知父 root.val 和 val的关系. 但不管结果,count++,因为以root为根已构成UniValue树.

*****/

public class Solution {
	int count = 0;
	
	// check if nodes on root tree are all with the same val.
	//返回是否root下所有点的值都为val.
	boolean all(TreeNode root, int val) {
		if (root == null)
			return true;
		/*
		if (!all(root.left, root.val) | !all(root.right, root.val))
			return false;
		count++;
		return root.val == val;*/
		
		// My logic
		if (all(root.left, root.val) && all(root.right, root.val)){
			count++;
			return root.val == val;
		}
		return false;		
	}
	
	public int countUnivalSubtrees(TreeNode root) {
		all(root, 0);
		return count;
	}
}

/*
Helper all tells whether all nodes in the given tree have the given value. And while
doing that, it also counts the uni-value subtrees.
*/


public class CountUnivalueSubtrees {

    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }

        int[] result = new int[]{0};
        helper(root, result);
        return result[0];
    }

    private boolean helper(TreeNode root, int[] result) {

        if (root.right == null && root.left == null) {
            result[0]++;
            return true;
        } else if (root.right != null && root.left == null) {
            if (helper(root.right, result) && root.val == root.right.val) {
                result[0]++;
                return true;
            } else {
                return false;
            }
        } else if (root.right == null) {
            if (helper(root.left, result) && root.val == root.left.val) {
                result[0]++;
                return true;
            } else {
                return false;
            }
        } else {
            boolean l = helper(root.right, result);
            boolean r = helper(root.left, result);
            if (l && r && root.val == root.left.val && root.val == root.right.val) {
                result[0]++;
                return true;
            } else {
                return false;
            }
        }

    }
}