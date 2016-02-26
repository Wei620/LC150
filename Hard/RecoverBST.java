/**
 * Two elements of a binary search tree (BST) are swapped by mistake.
 *
 * Recover the tree without changing its structure.
 *
 * Note:
 * A solution using O(n) space is pretty straight forward. Could you devise a
 * constant space solution?
 *
 * Tags: Tree, DFS
 */
class RecoverBST {

    public static void main(String[] args) {

    }

    TreeNode prev;
    TreeNode first;
    TreeNode second;

    /**
     * Do morris traversal to find those swapped nodes
     */
    public void recoverTree(TreeNode root) {
        if (root == null) return;
        prev = new TreeNode(Integer.MIN_VALUE);
        morrisInorder(root);
        // swap values of first and second
        int t = first.val;
        first.val = second.val;
        second.val = t;
    }

    /**
     * Morris Traversal, link null left node to its inorder predecessor
     * link null right node to its inorder successor
     * Initialize current as root
     * While current is not NULL
     * If current does not have left child
     * a) Print current’s data
     * b) Go to the right, i.e., current = current->right
     * Else
     * a) Make current as right child of the rightmost node in current's left
     * subtree
     * b) Go to this left child, i.e., current = current->left
     */
	 
	 //http://fisherlei.blogspot.com/2012/12/leetcode-recover-binary-search-tree.html
	 //https://en.wikipedia.org/wiki/Threaded_binary_tree
	 //http://www.cnblogs.com/TenosDoIt/p/3445682.html
	 
    void morrisInorder(TreeNode root) {
        TreeNode cur = root;
        TreeNode pred = null;
        while (cur != null) {
            if (cur.left == null) {// left most
                // set first and second if first still doesn't exist
                if (cur.val <= prev.val && first == null) first = prev;
                // set second only if first exists
                if (cur.val <= prev.val && first != null) second = cur;
                prev = cur; // note that previous node needs to be saved
                cur = cur.right; // move to next node
            } else {
                pred = cur.left;
                while (pred.right != null && pred.right != cur) pred = pred.right;
                if (pred.right == null) { // not connected
                    pred.right = cur; // connect predecessor to current node, pred.left == null line 55
                    cur = cur.left; // move to left child
                } else { // connected, revisted curr via the thread
                    if (cur.val <= prev.val && first == null) first = prev;  //prev is assigned by line 62
                    if (cur.val <= prev.val && first != null) second = cur;
                    pred.right = null; // break connection
                    prev = cur; // previous node needs to be saved
                    cur = cur.right; // move to right child
                }
            }
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
