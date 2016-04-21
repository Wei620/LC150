/*
210 Course Schedule II

There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array. 

For example:
2, [[1,0]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
4, [[1,0],[2,0],[3,1],[3,2]]

There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].

Note:
 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented. 

click to show more hints.

Hints: 1.This problem is equivalent to finding the topological order in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
2.Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
3.Topological sort could also be done via BFS.

*/

/*
This question asks for an order in which prerequisite courses must be taken first.
This prerequisite relationship reminds one of directed graphs. Then, the problem
reduces to find a topological sort order of the courses, which would be a DAG if it
has a valid order.*/

public int[] findOrder(int numCourses, int[][] prerequisites) {
	int[] preCounts = new int[numCourses];
	List<List<Integer>> adjs = new ArrayList<>(numCourses);
	initialiseGraph(preCounts, adjs, prerequisites);
	//return solveByBFS(preCounts, adjs);
	return solveByDFS(adjs);
}

private void initialiseGraph(int[] preCounts, List<List<Integer>> adjs, int[]
[] prerequisites){
	int n = preCounts.length;
	while (n-- > 0){
		adjs.add(new ArrayList<>());
	}
	for (int[] edge : prerequisites) {
		preCounts[edge[0]]++;
		adjs.get(edge[1]).add(edge[0]);	//assume no dup edge， otherwise List<HashSet<Integer>> or HashMap<Integer, HashSet<Integer>>
	}
}
/*
The first step is to transform it into a directed graph. Since it is likely to be sparse,we use adjacency list graph data structure. 1 -> 2 means 1 must be taken before 2.

How can we obtain a topological sort order of a DAG?
We observe that if a node has incoming edges, it has prerequisites. Therefore, the
first few in the order must be those with no prerequisites, i.e. no incoming edges.
Any non-empty DAG must have at least one node without incoming links. You can
draw a small graph to convince yourself. If we visit these few and remove all edges
attached to them, we are left with a smaller DAG, which is the same problem. This
will then give our BFS solution.
*/

/*****
1. BSF 所有基础课上完， DFS 先专攻一个方向
2. InitGraph 很清晰
3. BFS 用preCnt数组， preCnt[]==0 作为入队条件。 判断是否全走完， 出队列个数 = 总课程数？
4. DFS 用三态visited数组， order数组从后向前填， 因为只有最后的序号是确定的。
	set里都上完了, 再上key. (dfs-bt) 倒填数组ok.
	
411
0. 注意List<Integer> 转 int[] 
1. BFS, 统计后续课程的 PreCnt
2. DFS， 三态数组
	1. 外层， 找新课 （== 0）
	2. DFS 新课。
	3. set里的课上完了再上 key
*****/

//411 BFS
public class Solution {
    public int[] findOrder(int numCourses, int[][] prq) {
        List<Set<Integer>> adj = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; i++){
            adj.add(new HashSet<Integer>());
        }
        
        int[] preCnt = new int[numCourses];
        for(int i = 0; i < prq.length; i++){
            if(adj.get(prq[i][1]).add(prq[i][0])) preCnt[prq[i][0]]++;
        }
        
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < numCourses; i++){
            if(preCnt[i]  == 0) q.offer(i);
        }
        
        List<Integer> res = new ArrayList<>(numCourses);
        while(!q.isEmpty()){
            int pre = q.poll();
            res.add(pre);
            for(int after : adj.get(pre)){
                if(--preCnt[after] == 0) q.offer(after);
            }
        }
        
        Integer[] tmp = res.toArray(new Integer[0]);
        int[] order = new int[tmp.length];
        for(int i = 0; i < order.length; i++){
            order[i] = tmp[i];
        }
        
        return order.length == numCourses? order : new int[0];
    }
}

//DFS
public class Solution {
    public int[] findOrder(int numCourses, int[][] prq) {
        List<Set<Integer>> adj = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; i++){
            adj.add(new HashSet<Integer>());
        }
        for(int i = 0; i < prq.length; i++){
            adj.get(prq[i][1]).add(prq[i][0]);
        }
        
        int[] status = new int[numCourses];
        List<Integer> res = new ArrayList<>(numCourses);
        for(int i = 0; i < numCourses; i++){
            if(status[i] == 0 && !DFS(adj, i, status, res)) return new int[0];
        }
        
        Integer[] tmp = res.toArray(new Integer[0]);
        int[] order = new int[tmp.length];
        for(int i = 0; i < order.length; i++){
            order[i] = tmp[i];
        }
        
        return order.length == numCourses? order : new int[0];
    }
    
    private boolean DFS(List<Set<Integer>> adj, int newCourse, int[] status, List<Integer> res){
        status[newCourse] = 1;
        Set<Integer> nexts = adj.get(newCourse);
        for(int next : nexts){
            if(status[next] == 0){
                if(!DFS(adj, next, status, res)) return false;
            }
            else if(status[next] == 1) return false;
        }
        
        res.add(0, newCourse);
        status[newCourse] = 2;
        return true;
    }
}

private int[] solveByBFS(int[] preCounts, List<List<Integer>> adjs){
	int[] order = new int[preCounts.length];
	Queue<Integer> toVisit = new ArrayDeque<>();
	for (int i = 0; i < preCounts.length; i++) {
		if (preCounts[i] == 0) toVisit.offer(i);
	}
	int visited = 0;
	while (!toVisit.isEmpty()) {
		int from = toVisit.poll();
		order[visited++] = from;
		for (int to : adjs.get(from)) {
			preCounts[to]--;
			if (preCounts[to] == 0) toVisit.offer(to);
		}
	}
	return visited == preCounts.length ? order : new int[0];
}

/*
Thinking it recursively means if one node has unvisited child node, you should visit them first before you put this node down in the final order array. This sounds like the post-order of a DFS. Since we are putting
nodes down in the reverse order, we should reverse it back to correct ordering or use
a stack.*/

int curr;
int[] result;

private int[] solveByDFS(List<List<Integer>> adjs) {
	int numCourses = adjs.size();	
	int[] visited = new int[numCourses]; //0 / 1 / 2: not visited / visiting / visited
	
	cur = numCourses - 1;
	result = new int[numCourses];
	
	//detect cycle in each connected component
	for (int node = 0; node < numCourses; node++) {
		if (visited[node] == 0){
			if (DFS(node, adjs, visited)) {// no circle
				continue;
			} else {
				return new int[0];
			}
		}
	}
	return result;
}

// return 0 if has a circle.
public boolean DFS(int node, List<List<Integer>> graphMap, int[] visited) {
	if (graphMap.get(node)){// 有后继 不是最后一门
		visited[node] = 1; //1: visiting
		for (int next : graphMap.get(node)) {
			if (visited[next] == 1) {
				return false; //cycle
			}
			//DFS
			if (visited[next] == 0 && !DFS(next, graphMap, visited)) {
				return false;
			}
		}
	}
	
	// Track back, base case
	visited[node] = 2; //2: visit done
	result[cur] = node;
	cur--;
	return true;
}

//http://blog.welkinlan.com/2015/05/13/course-schedule-ii-leetcode-java-dfs-topological-sort/
