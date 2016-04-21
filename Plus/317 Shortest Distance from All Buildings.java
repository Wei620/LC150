/*
317 Shortest Distance from All Buildings

You want to build a house on an empty land which reaches all buildings in the shortest amount of distance. You are given a 2D grid of values 0, 1 or 2, where:

Each 0 marks an empty land which you can pass by freely.
Each 1 marks a building which you cannot pass through.
Each 2 marks an obstacle which you cannot pass through.
The distance is calculated using Manhattan Distance, where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.

For example, given three buildings at (0,0), (0,4), (2,2), and an obstacle at (0,2):

    1 - 0 - 2 - 0 - 1
    |   |   |   |   |
    0 - 0 - 0 - 0 - 0
    |   |   |   |   |
    0 - 0 - 1 - 0 - 0
The point (1,2) is an ideal empty land to build a house, as the total travel distance of 3+3+1=7 is minimal. So return 7.

Note:
There will be at least one building. If it is not possible to build such house according to the above rules, return -1.

Hide Company Tags Google Zenefits
Hide Tags Breadth-first Search
Hide Similar Problems (M) Walls and Gates (H) Best Meeting Point
*/

/*****
1. 	总和最短， 不好所有building一起bfs（到每个bldg等距离）。
	有障碍，有的空地bfs不可达。也可能不存在一个空地可以到所有的bldg. 快速rule out这种情况， 返回-1.
		每次bfs只访问前bldg标记过的（bldgCnt）, 跟新标记 -（bldgCnt + 1）。 
		用dist[] 累加bfs的距离（level + 1）。
2.  最后找所有bldg可达的空地（标记为-bldgCnt）里，dist累加最小的。找不到返-1.
*****/

public class Solution {
    public int shortestDistance(int[][] grid) {
        if (grid==null || grid.length==0 || grid[0].length==0) return -1;
        int m = grid.length;
        int n = grid[0].length;
        
        int bldgCnt = 0;
		int[][] dist = new int[m][n];
        int[][] directions = {{-1,0},{1,0},{0,-1},{0,1}};
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 1) {// building
                    int level = 0;
                    LinkedList<Integer> queue = new LinkedList<Integer>();
                    queue.offer(i*n+j);
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        for (int t=0; t<size; t++) {
                            int cur = queue.poll();
                            int x = cur/n;
                            int y = cur%n;
                            for (int[] dir : directions) {
                                int xnew = x + dir[0];
                                int ynew = y + dir[1];
                                if (xnew>=0 && xnew<m && ynew>=0 && ynew<n && 
									grid[xnew][ynew]== -bldgCnt {// only check the empty which can be reached by every preious found building.
                                    queue.offer(xnew*n+ynew);
                                    dist[xnew][ynew] += level + 1;
                                    grid[xnew][ynew] = - (bldgCnt + 1);
                                }
                            }
                        }
                        level++;
                    }
					bldgCnt++;
                }
            }
        }
        
        int minDist = Integer.MAX_VALUE;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == -bldgCnt) {
                    minDist = Math.min(minDist, dist[i][j]);
                }
				if(grid[i][j] < 0) grid[i][j] = 0; // resume
            }
        }
        return minDist==Integer.MAX_VALUE? -1 : minDist;
    }
}

/*
刚看到这道题的时候，觉得和296. Best Meeting Point很像，但是实际上不能用296的思想。因为这次有obstacle，并且“meeting point”不能是building的位置。

这道题得用BFS。对每一个grid[r][c]==1的点进行BFS，但使用一些技巧，使得后面执行的BFS尽可能的少访问一些点。方法是：

bfs第一个building的时候，只bfs那些等于0的点，bfs后将这些点的值设成-1
bfs第二个building的时候，只bfs那些等于-1的点，bfs后将这些点的值设成-2
bfs第三个building的时候，只bfs那些等于-2的点，bfs后将这些点的值设成-3
bfs第四个building的时候，只bfs那些等于-3的点，bfs后将这些点的值设成-4
……….
*/