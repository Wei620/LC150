/**
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 * 
 * What if the given tree could be any binary tree? Would your previous
 * solution still work?
 * 
 * Note:
 * 
 * You may only use constant extra space.
 * For example,
 * Given the following binary tree,
 *          1
 *        /  \
 *       2    3
 *      / \    \
 *     4   5    7
 * After calling your function, the tree should look like:
 *          1 -> NULL
 *        /  \
 *       2 -> 3 -> NULL
 *      / \    \
 *     4-> 5 -> 7 -> NULL
 * 
 * Tags: Tree ,DFS
 */
class PopulatingNextRight2 {
    public static void main(String[] args) {
        
    }
    
    /**
     * Store the head of next level
     * Store previous node 
     * Do level order traversal with a pointer
     */
    public void connect(TreeLinkNode root) {
        if(root == null) return;
        
        TreeLinkNode cur = root;  // current node of current level
        TreeLinkNode currNL; // previous node. on next level
        TreeLinkNode dummyNL = new TreeLinkNode(0);; // dummyNL of the next level

        
        while (cur != null) {
            currNL = dummyNL;
            while (cur != null) {
                if (cur.left != null) { // left child
                    currNL.next = cur.left;
                    currNL = currNL.next; // move right
                }
                if (cur.right != null) { // right child
                    currNL.next = cur.right;
                    currNL = currNL.next; // move right
                }
                cur = cur.next; // move right to next node in same level
            }
            // move to next level
            cur = dummyNL.next;
        }
    }
    
    
    
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
}