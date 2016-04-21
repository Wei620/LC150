/*
310 Minimum Height Trees

For a undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels. 

Format
 The graph contains n nodes which are labeled from 0 to n - 1. You will be given the number n and a list of undirected edges (each edge is a pair of labels). 

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges. 

Example 1: 

Given n = 4, edges = [[1, 0], [1, 2], [1, 3]] 
        0
        |
        1
       / \
      2   3


return  [1] 

Example 2: 

Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]] 
     0  1  2
      \ | /
        3
        |
        4
        |
        5


return  [3, 4] 

Hint:
1.How many MHTs can a graph have at most?

Note: 

(1) According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.” 

(2) The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf. 

*/

/*****
1. 不同于拓扑排序，edge的两端对等，无向图。 adj每次添加两个。adj里的val要用set。处理重复边。
	如用postCnt, 可以找到newLeave，但不知道它set里哪个才是剩下的唯一连接点。还是删边好。
2. leaves只有一个连接点（size == 1）.
   图像化想象， 最长leaves路径的median点就是MHT的跟，可能1个或两个。
	外围删叶法。 找到所有叶子。删除的同时可能产生也的叶子， 直至新叶子的数目为1或2.
	
303
1. 数组里不能用Generic， 所以只能眼红arraylist。
2. 要一层一层剪枝， 一层完后再看n>2是否满足。 
	    0 - 1 - 2
			  - 3
	用原解， 两个list倒。 n -= leaves.size();
	
411
1. 一层一层， 直至只剩下两个节点。 “不是”队列里只剩两个节点。
*****/
//411
public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) return Arrays.asList(new Integer[]{0});
        List<Set<Integer>> adj = new ArrayList<>(n);
        for(int i = 0; i < n; i++) adj.add(new HashSet<>());
        for(int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        
        Queue<Integer> leaves = new LinkedList<>();
        for(int i = 0; i < n; i++){
            if(isLeaf(adj, i)) leaves.offer(i);
        }
        
        while(n > 2){
            int sz = leaves.size();
            for(int i = 0; i < sz; i++){
                int leaf = leaves.poll();
                int neighbor = adj.get(leaf).iterator().next();
                adj.get(neighbor).remove(leaf);
                if(isLeaf(adj, neighbor)) leaves.offer(neighbor);
            }
            n -= sz;
        }
        
        return (List)leaves;
    }
    
    boolean isLeaf(List<Set<Integer>> adj, int id){
        return adj.get(id).size() == 1;
    }
}

public List<Integer> findMinHeightTrees(int n, int[][] edges){
    if (n == 1) return Collections.singletonList(0);
    
	List<Set<Integer>> adj = new ArrayList<>(n);
    for (int i = 0; i < n; ++i) adj.add(new HashSet<>());
    for (int[] edge : edges) {
        adj.get(edge[0]).add(edge[1]);
        adj.get(edge[1]).add(edge[0]);
    }
	
    List<Integer> leaves = new ArrayList<>();
    for (int i = 0; i < n; ++i)
        if (adj.get(i).size() == 1) leaves.add(i);
    
	
	while (n > 2) {// n == 2, both are in leaves already.
        n -= leaves.size();
        List<Integer> newLeaves = new ArrayList<>();
        for (int i : leaves) {
            int j = adj.get(i).iterator().next();//connected graph
            adj.get(j).remove(i);
            if (adj.get(j).size() == 1) newLeaves.add(j);
        }
        leaves = newLeaves;
    }
    return leaves;
}

/*
We start from every end, by end we mean
vertex of degree 1 (aka leaves). We let the pointers move the same speed. When two
pointers meet, we keep only one of them, until the last two pointers meet or one step
away we then find the roots.
It is easy to see that the last two pointers are from the two ends of the longest path
in the graph.
The actual implementation is similar to the BFS topological sort. Remove the leaves,
update the degrees of inner vertexes. Then remove the new leaves. Doing so level by
level until there are 2 or 1 nodes left. What's left is our answer!
The time complexity and space complexity are both O(n).
*/