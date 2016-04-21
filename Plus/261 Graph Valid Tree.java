/*
261 Graph Valid Tree

Question:
 Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

For example:
Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
*/
/*****
293
1. 用path expression, weighted quick sort 的并查集.
2. 查环路,查连通性

411.
1. id[i] = id[id[i]]; //path compression
*****/
//411
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges){
            if(uf.isConnected(edge[0], edge[1])) return false;
            uf.union(edge[0], edge[1]);
        }
        return uf.count() == 1;
    }
    
    class UnionFind{
        int count = 0;
        int[] id;
        int[] sz;
        
        public UnionFind(int size){
            count = size;
            id = new int[size];
            sz = new int[size];
            for(int i = 0; i < count; i++){
                id[i] = i;
                sz[i] = 1;
            }
        }
        
        private int root(int i){
            while(id[i] != i){
                id[i] = id[id[i]]; //path compression
                i = id[i];
            }
            return i;
        }
        
        public boolean isConnected(int p, int q){
            if(p < 0 || p >= id.length || q < 0 || q >= id.length) return false;
            return root(p) == root(q);
        }
        
        public void union(int p, int q){
			if(p < 0 || p >= id.length || q < 0 || q >= id.length) return;
            int rp = root(p), rq = root(q);
            if(sz[rp] > sz[rq]){
                id[rq] = rp;
                sz[rp] += sz[rq];
            }
            else{
                id[rp] = rq;
                sz[rq] += rp;
            }
            count--;
        }
        
        public int count(){
            return count;
        }
    }
}
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for(int i = 0; i < edges.length; i++){
            // 如果两个节点已经在同一集合中，说明新的边将产生环路
            if(!uf.union(edges[i][0], edges[i][1])){
                return false;
            }
        }
        return uf.count() == 1;
    }
    
    public class UnionFind {
        
        int[] ids;
        int cnt;
        
        public UnionFind(int size){
            this.ids = new int[size];
            //初始化并查集，每个节点对应自己的集合号
            for(int i = 0; i < this.ids.length; i++){
                this.ids[i] = i;
            }
            this.cnt = size;
        }
        public boolean union(int m, int n){
            int src = find(m);
            int dst = find(n);
            //如果两个节点不在同一集合中，将两个集合合并为一个
            if(src != dst){
                for(int i = 0; i < ids.length; i++){
                    if(ids[i] == src){
                        ids[i] = dst;
                    }
                }
                // 合并完集合后，集合数减一
                cnt--;
                return true;
            } else {
                return false;
            }
        }
        public int find(int m){
            return ids[m];
        }
        public boolean areConnected(int m, int n){
            return find(m) == find(n);
        }
        public int count(){
            return cnt;
        }
    }
}

/*
union-find
复杂度
时间 O(N^M) 空间 O(1)

思路
判断输入的边是否能构成一个树，我们需要确定两件事：

这些边是否构成环路，如果有环则不能构成树
这些边是否能将所有节点连通，如果有不能连通的节点则不能构成树
因为不需要知道具体的树长什么样子，只要知道连通的关系，所以并查集相比深度优先搜索是更好的方法。我们定义一个并查集的数据结构，并提供标准的四个接口：

union 将两个节点放入一个集合中
find 找到该节点所属的集合编号
areConnected 判断两个节点是否是一个集合
count 返回该并查集中有多少个独立的集合
具体并查集的原理，参见这篇文章。简单来讲，就是先构建一个数组，节点0到节点n-1，刚开始都各自独立的属于自己的集合。这时集合的编号是节点号。然后，每次union操作时，我们把整个并查集中，所有和第一个节点所属集合号相同的节点的集合号，都改成第二个节点的集合号。这样就将一个集合的节点归属到同一个集合号下了。我们遍历一遍输入，把所有边加入我们的并查集中，加的同时判断是否有环路。最后如果并查集中只有一个集合，则说明可以构建树。

注意
因为要判断是否会产生环路，union方法要返回一个boolean，如果两个节点本来就在一个集合中，就返回假，说明有环路

代码

*/