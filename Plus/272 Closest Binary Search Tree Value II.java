/*
272 Closest Binary Search Tree Value II
Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.

Note:
Given target value is a floating point.
You may assume k is always valid, that is: k ≤ total nodes.
You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

Follow up:
Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

Hint:
Consider implement these two helper functions:
1) getPredecessor(N), which returns the next smaller node to N.
2) getSuccessor(N), which returns the next larger node to N.
Try to assume that each node has a parent pointer, it makes the problem much easier.
Without parent pointer we just need to keep track of the path from the root to the current node using a stack.
You would need two stacks to track the path in finding predecessor and successor node separately.
*/

/*****
###
1. In-order interative way, thread tree or stack.
2. pre-order vs in-order, 入栈访问 vs 出栈访问. pop().right != null, 再压入.
3. 出栈后去填充一个k长queue, 右-target > 左-target 填完. BST无等号.

*****/


public class Solution {
	// My implementation
	Queue<TreeNode> q = new LinkedList<>();	
	private boolean coutnK(TreeNode node, int target, int k){
		if(q.size() < k) q.offer(node);
		else if(target - q.peek().val > node.val - target){ 
			q.poll();
			q.offer(node);
		}
		else return true; // BST, no equivalence.
		return false;
	}
	
	public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> stk = new Stack<TreeNode>();
        while(!stk.isEmpty()){
            TreeNode curr = stk.peek();
			if(curr.left != null) stk.push(curr.left);
			else{
				stk.pop();
				if(countK(curr, target, k)) return new LinkedList(q);//出栈访问
				if(curr.right != null) stk.push(curr.right);
			}
		}
		return false;
    }
	
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        LinkedList<Integer> klist = new LinkedList<Integer>();
        Stack<TreeNode> stk = new Stack<TreeNode>();
        // in-order transversal.
        while(root != null){
            stk.push(root);
            root = root.left;
        }
		
        while(!stk.isEmpty()){
            TreeNode curr = stk.pop();
            // 维护一个大小为k的队列
            // 队列不到k时直接加入
            if(klist.size() < k){
                klist.add(curr.val);
            } else {
            // 队列到k时，判断下新的数是否更近，更近就加入队列并去掉队头
                int first = klist.getFirst();
                if(Math.abs(first - target) > Math.abs(curr.val - target)){
                    klist.remove();
                    klist.add(curr.val);
                } else {
                // 如果不是更近则直接退出，后面的数只会更大
                    break;
                }
            }
			
            // in-oder transversal
            if(curr.right != null){
                curr = curr.right;
                while(curr != null){
                    stk.push(curr);
                    curr = curr.left;
                }
            }
        }
        // 强制转换成List，是用LinkedList实现的所以可以转换
        return klist;
    }
}

public List<Integer> closestKValues(TreeNode root, double target, int k) {
    List<Integer> res = new ArrayList<>();
    Stack<Integer> s1 = new Stack<>(); // predecessors
    Stack<Integer> s2 = new Stack<>(); // successors
    inorder(root, target, false, s1);
    inorder(root, target, true, s2);
    while (k-- > 0) {
        if (s1.isEmpty())
            res.add(s2.pop());
        else if (s2.isEmpty())
            res.add(s1.pop());
        else if (Math.abs(s1.peek() - target) < Math.abs(s2.peek() - target))
            res.add(s1.pop());
        else
            res.add(s2.pop());
    }
    return res;
}
// inorder traversal
void inorder(TreeNode root, double target, boolean reverse, Stack<Integer> stack){
    if (root == null) return;
    inorder(reverse ? root.right : root.left, target, reverse, stack);
    // early terminate, no need to traverse the whole tree
    if ((reverse && root.val <= target) || (!reverse && root.val > target)) return;
    // track the value of current node
    stack.push(root.val);
    inorder(reverse ? root.left : root.right, target, reverse, stack);
}