/*
285 Inorder Successor

Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

Note: If the given node has no in-order successor in the tree, return null.
*/
/* ****
1. 找p的后继,p是BST的节点.
2. base, 当root.val = p.val, 继续右树找, 就会一路向左,找到后继. 注意拐弯.
3. 找前剂, 当root.val = p.val, 继续左树找, 就会一路向右, 找到前继.

303
1. BST无重复, p又一定在BST里
2. 单左树,根相等,返回null.
3. 想 BS里找最小A[]>target的情况. 返high+1.
4. left == null, 左树找不到 p(target) < root的情况, 只能用缓存的父节点.

411
1. iterative
2. successor, 朝左走时,更新 succ. 标记所有左拐点.
3. predecessor, 朝有走时, 更新 pred 无early termination. 标记所有右刮点.
4. !!!! 标记所有 succ关系.
*****/
//411
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode succ = null;
        while (root != null) {
            if (p.val < root.val) {
                succ = root;
                root = root.left; //same direction, more close
            }
            else root = root.right;
        }
        return succ;
    }
}
//411 pre
public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode pred = null;
        while (root != null) {
            if (p.val > root.val) {
                pred = root;
                root = root.right;
            }
            else root = root.left;
        }
        return pred;
    }
}

public class Solution {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
    	if (root == null)
    		return null;
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p); // ones on right tree must have a successor
        } else {
    		TreeNode left = inorderSuccessor(root.left, p);
    		return (left != null) ? left : root;
        }
    }
}


public TreeNode predecessor(TreeNode root, TreeNode p) {
    if (root == null)
        return null;
    if (root.val >= p.val) {
        return predecessor(root.left, p);
    } else {
        TreeNode right = predecessor(root.right, p);
        return (right != null) ? right : root;
    }
}