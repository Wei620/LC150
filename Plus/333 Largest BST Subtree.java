/*
333 Largest BST Subtree

Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.

Note:
A subtree must include all of its descendants.
Here’s an example:

    10
    / \
   5  15
  / \   \ 
 1   8   7
The Largest BST Subtree in this case is the highlighted one.
The return value is the subtree’s size, which is 3.
*/

class Value{
	int bstSize = 0;
	int min = Integer.MAX_VALUE;
	int max = Integer.MIN_VALUE;
}

int maxBSTSize = 0;

int largestBST(Node node){
	largestBSTHelper(node);
	return maxBSTSize;
}


Value largestBSTHelper(Node node) {
	if(node == null){
		return new Value();
	}	
	
	Value lVal = largestBSTHelper(node.left);
	Value rVal = largestBSTHelper(node.right);
	Value curVal = new Value();
	curVal.bstSize = -1;
	maxBSTSize = Math.max(Math.max(lVal.bstSize, rVal.bstSize), maxBSTSize);
	if(lVal.bstSize >= 0 && lVal.bstSize >= 0 &&
		node.val > lVal.max && node.val < rVal.min){
			bstSize = lVal.bstSize + rVal.bstSize + 1;
			if(curVal.bstSize > maxBSTSize) maxBSTSize = curVal.bstSize;
			curVal.min = lVal.min < node.val? lVal.min : node.val;
			curVal.max = rVal.max > node.val? rVal.max : node.val;
	}
	return curVal;
}




// Java program to find largest BST subtree in given Binary Tree
 
class Node {
 
    int data;
    Node left, right;
 
    Node(int d) {
        data = d;
        left = right = null;
    }
}
 
class Value {
 
    int max_size = 0; // for size of largest BST
    boolean is_bst = false;
    int min = Integer.MAX_VALUE;  // For minimum value in right subtree
    int max = Integer.MIN_VALUE;  // For maximum value in left subtree
 
}
/* 
http://www.geeksforgeeks.org/find-the-largest-subtree-in-a-tree-that-is-also-a-bst/
In method 1, we traverse the tree in top down manner and do BST test for every node. If we traverse the tree in bottom up manner, then we can pass information about subtrees to the parent. The passed information can be used by the parent to do BST test (for parent node) only in constant time (or O(1) time). 

A left subtree need to tell the parent whether it is BST or not and also need to pass maximum value in it. So that we can compare the maximum value with the parent’s data to check the BST property. 

Similarly, the right subtree need to pass the minimum value up the tree. The subtrees need to pass the following information up the tree for the finding the largest BST.

1) Whether the subtree itself is BST or not (In the following code, is_bst_ref is used for this purpose)
2) If the subtree is left subtree of its parent, then maximum value in it. And if it is right subtree then minimum value in it.
3) Size of this subtree if this subtree is BST (In the following code, return value of largestBSTtil() is used for this purpose)

max_ref is used for passing the maximum value up the tree and min_ptr is used for passing minimum value up the tree. 
*/

class BinaryTree {
 
    static Node root;
    Value val = new Value();
 
    /* Returns size of the largest BST subtree in a Binary Tree
     (efficient version). */
    int largestBST(Node node) {
 
        largestBSTUtil(node, val, val, val, val);
 
        return val.max_size;
    }
 
    /* largestBSTUtil() updates *max_size_ref for the size of the largest BST
     subtree.   Also, if the tree rooted with node is non-empty and a BST,
     then returns size of the tree. Otherwise returns 0.*/
    int largestBSTUtil(Node node, Value min_ref, Value max_ref,
            Value max_size_ref, Value is_bst_ref) {
 
        /* Base Case */
        if (node == null) {
            is_bst_ref.is_bst = true; // An empty tree is BST
            return 0;    // Size of the BST is 0
        }
 
        int min = Integer.MAX_VALUE;
 
        /* A flag variable for left subtree property
         i.e., max(root->left) < root->data */
        boolean left_flag = false;
 
        /* A flag variable for right subtree property
         i.e., min(root->right) > root->data */
        boolean right_flag = false;
 
        int ls, rs; // To store sizes of left and right subtrees
 
        /* Following tasks are done by recursive call for left subtree
         a) Get the maximum value in left subtree (Stored in *max_ref)
         b) Check whether Left Subtree is BST or not (Stored in *is_bst_ref)
         c) Get the size of maximum size BST in left subtree (updates *max_size) */
        max_ref.max = Integer.MIN_VALUE;
        ls = largestBSTUtil(node.left, min_ref, max_ref, max_size_ref, is_bst_ref);
        if (is_bst_ref.is_bst == true && node.data > max_ref.max) {
            left_flag = true;
        }
 
        /* Before updating *min_ref, store the min value in left subtree. So that we
         have the correct minimum value for this subtree */
        min = min_ref.min;
 
        /* The following recursive call does similar (similar to left subtree) 
         task for right subtree */
        min_ref.min = Integer.MAX_VALUE;
        rs = largestBSTUtil(node.right, min_ref, max_ref, max_size_ref, is_bst_ref);
        if (is_bst_ref.is_bst == true && node.data < min_ref.min) {
            right_flag = true;
        }
 
        // Update min and max values for the parent recursive calls
        if (min < min_ref.min) {
            min_ref.min = min;
        }
        if (node.data < min_ref.min) // For leaf nodes
        {
            min_ref.min = node.data;
        }
        if (node.data > max_ref.max) {
            max_ref.max = node.data;
        }
 
        /* If both left and right subtrees are BST. And left and right
         subtree properties hold for this node, then this tree is BST.
         So return the size of this tree */
        if (left_flag && right_flag) {
            if (ls + rs + 1 > max_size_ref.max_size) {
                max_size_ref.max_size = ls + rs + 1;
            }
            return ls + rs + 1;
        } else {
            //Since this subtree is not BST, set is_bst flag for parent calls
            is_bst_ref.is_bst = false;
            return 0;
        }
    }
 
    public static void main(String[] args) {
        /* Let us construct the following Tree
                50
             /      \
            10        60
           /  \       /  \
          5   20    55    70
         /     /  \
        45   65    80
         */
 
        BinaryTree tree = new BinaryTree();
        tree.root = new Node(50);
        tree.root.left = new Node(10);
        tree.root.right = new Node(60);
        tree.root.left.left = new Node(5);
        tree.root.left.right = new Node(20);
        tree.root.right.left = new Node(55);
        tree.root.right.left.left = new Node(45);
        tree.root.right.right = new Node(70);
        tree.root.right.right.left = new Node(65);
        tree.root.right.right.right = new Node(80);
 
        /* The complete tree is not BST as 45 is in right subtree of 50.
         The following subtree is the largest BST
             60
            /  \
          55    70
          /     /  \
        45     65   80
        */
        System.out.println("Size of largest BST is " + tree.largestBST(root));
    }
}