/*
305 Number of Islands II

A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example:

Given m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]].
Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).

0 0 0
0 0 0
0 0 0
Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.

1 0 0
0 0 0   Number of islands = 1
0 0 0
Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.

1 1 0
0 0 0   Number of islands = 1
0 0 0
Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.

1 1 0
0 0 1   Number of islands = 2
0 0 0
Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.

1 1 0
0 0 1   Number of islands = 3
0 1 0
We return the result as an array: [1, 1, 2, 3]

Challenge:

Can you do it in time complexity O(k log mn), where k is the length of the positions?
*/

/*****
1. 联通问题， 用union-find. 4个方向依次union。
2. id阵最好还是1d， 需要（x,y)转idx. 
3. 岛是逐步添加，为了快速，（x,y)到id还是用array. （也可以用map）。
	id是1d，需要对（x,y)降维index. 题中x*n+y+1,把0空出来做无效index。
		好处是0也作为id的无效初始值。 
		对有效idx>0（岛），初始id[idx] = idx
4. 添加（x,y)后， 对于有效的邻居（idx>0),如果和p未连接， union。
	可选新技术， id[]表父节点序号 而不是集合号
				1. path compression，
					每次root把路径id[i]存爷爷，最终会指向祖先。 
			 2. weighted quick union
					小树的根的父（原味自己）改成大树的根。
			 
http://blog.csdn.net/dm_vincent/article/details/7655764

311
1. 是否越界, 用看id[i]是否>0.
2. 添加的时候返回id, 这样连接的时候可以直接使用.
3. connet 和 unite 独立比较好.
4. add里count++

*****/

public class Solution {
    private int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind2D islands = new UnionFind2D(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] position : positions) {
            int x = position[0], y = position[1];
            int p = islands.add(x, y);
            for (int[] d : dir) {
                int q = islands.getID(x + d[0], y + d[1]);
                if (q > 0 && !islands.isConnected(p, q))
                    islands.unite(p, q);
            }
            ans.add(islands.size());
        }
        return ans;
    }
}

class UnionFind2D {
    private int[] id;
    private int[] sz;
    private int m, n, count;
    public UnionFind2D(int m, int n) {
        this.count = 0;
        this.n = n;
        this.m = m;
        this.id = new int[m * n + 1];
        this.sz = new int[m * n + 1];
    }
    public int index(int x, int y) { return x * n + y + 1; }
    
    public int size() { return this.count; }
    
    public int getID(int x, int y) {
        if (0 <= x && x < m && 0<= y && y < n)
            return id[index(x, y)];
        return 0;
    }
    
    public int add(int x, int y) {
        int i = index(x, y);
        id[i] = i; sz[i] = 1;
        ++count;
        return i;
    }
    
    public boolean isConnected(int p, int q) {
        return root(p) == root(q);
    }
    
    public void unite(int p, int q) {
        int i = root(p), j = root(q);
        if (sz[i] < sz[j]) { //weighted quick union
			id[i] = j; sz[j] += sz[i]; // size[i] no effect any more.
        } else {
			id[j] = i; sz[i] += sz[j];
        }
        --count;
    }
    
    private int root(int i) {
        while(i != id[i]){
            id[i] = id[id[i]]; //path compression
            i = id[i];
        }
        return i;
    }
}

// basic union-find 323