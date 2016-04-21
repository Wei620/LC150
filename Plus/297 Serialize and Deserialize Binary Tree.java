/*
297 Serialize and Deserialize Binary Tree

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment. 

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree 
    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]", just the same as how LeetCode OJ serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself. 

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless. 

*/

/*****
1. 序列化,转成string,但要加分隔符.
2. 三种树遍历可任选一种,叶子上的空也要表明.
3. serialize的时候可以用stringbuilder做参数,这样修改的可保存.
4. deserialize 先分割存list. 用list,就要传iterator. 用queue省事.
5. 注意queue其实不会poll空. 最后一个是null. (说不清)

313
1. pre和inorder 都是stack形式. stack里不能放null.
		pre,入栈访问, push
		in  出栈访问. pop
	push多于pop, append时候略麻烦. 所以 prefer inorder.
2. 判断!=null后,都要加MN空节点.
3. append连写.也可append(a+b).
4. str.split()返arr.
5. decoding iterative太复杂.
	递归要想透. base是MN

411
1. Integer.valueOf(val)
2. preorder 解码清晰。
*****/

public class Codec {
    private static final String spliter = ",";
    private static final String NN = "X";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        buildString(root, sb);
        return sb.toString();
    }
    private void buildString(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append(NN).append(spliter);
        } else {
            sb.append(node.val).append(spliter);
            buildString(node.left, sb);
            buildString(node.right,sb);
        }
    }
	
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(spliter)));
        return buildTree(nodes);
    }
	
    private TreeNode buildTree(Queue<String> nodes) {
        String val = nodes.poll();
        if (val.equals(NN)) return null;
        else {
            TreeNode node = new TreeNode(Integer.valueOf(val));
            node.left = buildTree(nodes);
            node.right = buildTree(nodes);
            return node;
        }
    }
	
	// preorder encoding
	private void buildString(TreeNode node, StringBuilder sb) {
		Stack<TreeNode> stk = new ArrayDeque<>();
		if(node == null) return;
		stk.push(node); // 1
		sb.append(node.val).append(splitter);
		while(!stk.isEmpty()){
			TreeNode curr = stk.peek();
			if(curr.left != null){
				stk.push(node.left); //2
				sb.append(node.left.val).append(splitter);
			}
			else{
				sb.append(MN).append(splitter);
				stk.pop();
				if(curr.right != null){
					stk.push(curr.right); //3
					sb.append(curr.right.val).append(splitter);
				}
				else sb.append(MN).append(splitter);
			}		
		}		
	}
	
	// inorder
	private void buildString(TreeNode node, StringBuilder sb) {
		Stack<TreeNode> stk = new ArrayDeque<>();
		if(node == null) return;
		stk.push(node);
		while(!stk.isEmpty()){
			TreeNode curr = stk.peek();
			if(curr.left != null){
				stk.push(node.left);
			}
			else{
				sb.append(MN).append(splitter);
				sb.append(curr).append(splitter);
				stk.pop(); //1
				if(curr.right != null){
					stk.push(curr.right);
				}
				else sb.append(MN).append(splitter);
			}		
		}
	}
}