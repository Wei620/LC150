/*
257 Binary Tree

Given a binary tree, return all root-to-leaf paths. 

For example, given the following binary tree: 


   1
 /   \
2     3
 \
  5



All root-to-leaf paths are: 
["1->2->5", "1->3"]

*/
/* ****
1. 临时变量用string更好。
2. base是叶子， 如果base是null，会打印两遍。
*****/
// 411
public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<String>();
        if (root != null) searchBT(root, "", answer);
        return answer;
    }
    
    private void searchBT(TreeNode root, String path, List<String> answer) {
        if (root.left == null && root.right == null) answer.add(path + root.val);
        if (root.left != null) searchBT(root.left, path + root.val + "->", answer);
        if (root.right != null) searchBT(root.right, path + root.val + "->", answer);
    }
}

public class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        helper(root, new ArrayList<Integer>(), res);
        return res;
    }
    
    private void helper(TreeNode node, List<Integer> path, List<String> res){
        if(node ==  null) return;
        if(node.left == null && node.right == null){
            String str = "";
            for(int id : path){
                str += id + "->";
            }
            res.add(str + node.val);
            return; 
        }
        
        path.add(node.val);
        if(node.left != null) helper(node.left, path, res);
        if(node.right != null) helper(node.right, path, res);
        path.remove(path.size() - 1);
    }
}
