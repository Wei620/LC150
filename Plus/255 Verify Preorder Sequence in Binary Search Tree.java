/*
255 Verify Preorder Sequence in Binary Search Tree

Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.

You may assume each number in the sequence is unique.

Follow up:
Could you do it using only constant space complexity?
*/
/*****
1.	图形化想象， preorder，一直走左树，直至最左。（一直减小）。 
	遇到一个右节点（变大），挂哪里？ 往回走（出栈）， 直至 栈顶>右节点。 最后一个弹出的是右节点的父节点。 栈顶是父节点的父节点
2.  继续图形化， 下一个节点不能再父节点的左树上。 （下一个节点 < parent）
3. 写的时候 26， 24， 22.

411.
1. 找到parent后，别忘了push（p）.
*****/

public boolean verifyPreorder(int[] preorder) {
	int parent = Integer.MIN_VALUE;
	Stack<Integer> path = new Stack();
	for (int p : preorder) {
		if (p < parent) 
			return false; 
		while (!path.empty() && p > path.peek())
			parent = path.pop();
		path.push(p); 
	}
	return true;
}

/*
The idea is traversing the preorder list and using a stack to store all predecessors.
currp is a predecessor of current node and current node is in the right subtree of
currp.
For example, for the following bst with preorder 6,3,1,2,5,4,7:
       6
      / \
     3 7
    / \
    1 5
    \ /
    2 4
We push to stack before we see 2. So at 2 the stack is 6,3,1. For 2, we pop stack until
we see 3 which is greater than 2 and currp is 1. 2 is in left subtree of 3 and is right
child of 1. Stack is 6,3,2 now. Then we see 5, and we pop stack until 6 and currp is
3. Stack now is 6,5.

*/