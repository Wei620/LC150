/*
286 Walls and Gates

You are given a m x n 2D grid initialized with these three possible values.
-1 - A wall or an obstacle.
0 - A gate.
INF - Infinity means an empty room. We use the value 2^31 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.

Fill each empty room with the distance to its nearest gate. If it is impossible to reach a gate, it should be filled with INF.
For example, given the 2D grid:
    INF  -1  0  INF
    INF INF INF  -1
    INF  -1 INF  -1
      0  -1 INF INF
After running your function, the 2D grid should be:
      3  -1   0   1
      2   2   1  -1
      1  -1   2  -1
      0  -1   3   4
*/

/*****
1. DFS需要回溯,每个房间还可修改(找最小值),麻烦.
2. 所有gate一起开始BSF只需改INF的.简单!
3. Queue<int[]> 不用转坐标,方便.

303
1. 同级改邻居, dist都一样, 不用覆盖更新, 所以只查INF.
2. 不用额外的dist和层节点计数.
		用poll出来room的值 + 1
*****/

public class Solution {
public void wallsAndGates(int[][] rooms) {
    if (rooms.length == 0 || rooms[0].length == 0) return;
    int m = rooms.length, n = rooms[0].length;
    
    Queue<int[]> queue = new LinkedList<>();
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (rooms[i][j] == 0){
                queue.add(new int[]{i, j});
            }
        }
    }
    
    int[][] dirs = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
    
    while (!queue.isEmpty()) {
        int[] curr = queue.poll();
        int x = curr[1], y = curr[0];
        for(int k = 0; k < dirs.length; k++){
            int nx = x + dirs[k][0];
            int ny = y + dirs[k][0];
            if(nx >= 0 && nx < m && ny >= 0 && ny < n && rooms[nx][ny] == Integer.MAX_VALUE){
                rooms[nx][ny] = rooms[x][y] + 1;
                queue.add(new int[]{nx, ny});
            }
        }
    }
}
}