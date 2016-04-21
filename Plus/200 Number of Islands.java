/*
200 Number of Islands

Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
11110
11010
11000
00000

Answer: 1

Example 2:
11000
11000
00100
00011

Answer: 3

*/

/*****
1. DFS， in-place mark
	same order, tranverse all directions.
2. UnionFind, dynamic add.

411
1. 不管扫描顺序如何, 必须四向探索. 如"工"字岛.
*****/

public class Solution {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length, cnt = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(explore(i, j, grid)) cnt++;
            }
        }
        return cnt;
    }
    
    private boolean explore(int i, int j, char[][] grid){
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') return false;
        
        grid[i][j] = '0';
        explore(i-1, j, grid);
        explore(i, j-1, grid);
        explore(i, j+1, grid);
        explore(i+1, j, grid);
        return true;
    }
}

public class NumberofIslands {
	static int[] dx = {-1,0,0,1};
	static int[] dy = {0,1,-1,0};
	public static int numIslands(char[][] grid) {
		if(grid==null || grid.length==0) return 0;
		int islands = 0;
		for(int i=0;i<grid.length;i++) {
			for(int j=0;j<grid[i].length;j++) {
				if(grid[i][j]=='1') {
					explore(grid,i,j);
					islands++;
				}
			}
		}
		return islands;
	}
	
	// only mark x
	public static void explore(char[][] grid, int i, int j) {
		grid[i][j]='x';
		for(int d=0;d<dx.length;d++) {
			if(i+dy[d]<grid.length && i+dy[d]>=0 && 
				j+dx[d]<grid[0].length && j+dx[d]>=0 && 
				grid[i+dy[d]][j+dx[d]]=='1') {
				explore(grid,i+dy[d],j+dx[d]);
			}
		}
	}
}

/*
The algorithm works as follow:
1. Scan each cell in the grid.
2. If the cell value is '1' explore that island.
3. Mark the explored island cells with 'x'.
4. Once finished exploring that island, increment islands counter.
The arrays dx[], dy[] store the possible moves from the current cell. Two land cells
['1'] are considered from the same island if they are horizontally or vertically
adjacent (possible moves (-1,0),(0,1),(0,-1),(1,0)). Two '1' diagonally adjacent are not
considered from the same island.*/