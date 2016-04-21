/*
207 Course Schedule

There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses? 

For example:
2, [[1,0]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
2, [[1,0],[0,1]]

There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.

Note:
 The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented. 

click to show more hints.

Hints: 1.This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
2.Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
3.Topological sort could also be done via BFS.

*/

/* ****
1. 拓扑排序建表， pre -> courselist; 统计 preCnt[course]
2. loop couseCnt 次， 每次找一个preCnt[course] == 0. 将此对你供应courselist里的preCnt[]--
   （等效于移除边，但速度更快）。找不到，说明只剩下cycle
3. 全做完，return true.

1. BFS(就用hashset), 入队准备修. 出队修完了, 已修课程数++, 最后比较 已修和总课数. 后继set--.
2. DFS. for preCnt[] == 0的课, 修到不能修. 任何一个上完(已修课 = 总课数), true. 否则 false. 
		recursive, 已修++, 遍历后继set. 其中课程 preCnt--, == 0, 用这个开始迭代.
		
411
1. pre -> afters. 统计 preCnt[after]++
2. 用queue来bfs.
*****/

//411
public class Solution {
    public boolean canFinish(int numCourses, int[][] prq) {
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
        
        int cnt = 0;
        while(!q.isEmpty()){
            int pre = q.poll();
            cnt++;
            for(int after : adj.get(pre)){
                if(--preCnt[after] == 0) q.offer(after);
            }
        }
        
        return cnt == numCourses;
    }
}

http://blog.csdn.net/ljiabin/article/details/45846837
public class Solution { 
	//Hashset
    public boolean canFinish(int numCourses, int[][] prerequisites) {  
        // init the adjacency list  
        List<Set> posts = new ArrayList<Set>();  
        for (int i = 0; i < numCourses; i++) {  
            posts.add(new HashSet<Integer>());  
        }  
          
        // fill the adjacency list  
        for (int i = 0; i < prerequisites.length; i++) {  
            posts.get(prerequisites[i][1]).add(prerequisites[i][0]);  
        }  
          
        // count the pre-courses  
        int[] preNums = new int[numCourses];  
        for (int i = 0; i < numCourses; i++) {  
            Set set = posts.get(i);  
            Iterator<Integer> it = set.iterator();  
            while (it.hasNext()) {  
                preNums[it.next()]++;  
            }  
        }  
          
        // remove a non-pre course each time  
        for (int i = 0; i < numCourses; i++) {  
            // find a non-pre course  
            int j = 0;  
            for ( ; j < numCourses; j++) {  
                if (preNums[j] == 0) break;  
            }  
              
            // if not find a non-pre course  
            if (j == numCourses) return false;  
              
            preNums[j] = -1;  
              
            // decrease courses that post the course  
            Set set = posts.get(j);  
            Iterator<Integer> it = set.iterator();  
            while (it.hasNext()) {  
                preNums[it.next()]--;  
            }  
        }  
          
        return true;  
    }  
}  

public class Solution {
	//BFS
    public boolean canFinish(int numCourses, int[][] prerequisites) {  
        List<List<Integer>> posts = new ArrayList<List<Integer>>();  
        for (int i = 0; i < numCourses; i++) {  
            posts.add(new ArrayList<Integer>());  
        }  
          
        int[] preNums = new int[numCourses];  
        for (int i = 0; i < prerequisites.length; i++) {  
            posts.get(prerequisites[i][1]).add(prerequisites[i][0]);  
            preNums[prerequisites[i][0]]++;  // dups count twice
        }  
          
        Queue<Integer> queue = new LinkedList<Integer>();  
        for (int i = 0; i < numCourses; i++) {  
            if (preNums[i] == 0){  
                queue.offer(i);  
            }  
        }  
          
        int count = numCourses;  
        while (!queue.isEmpty()) {  
            int cur = queue.poll();  
            for (int i : posts.get(cur)) {  
                if (--preNums[i] == 0) {  
                    queue.offer(i);  
                }  
            }  
            count--;  
        }  
          
        return count == 0;  
    }  
}



bool canFinish(int numCourses, vector<vector<int>>& prerequisites)
{
vector<unordered_set<int>> matrix(numCourses); // save this directed graph
for(int i = 0; i < prerequisites.size(); ++ i)
matrix[prerequisites[i][1]].insert(prerequisites[i][0]);
unordered_set<int> visited;
vector<bool> flag(numCourses, false);
for(int i = 0; i < numCourses; ++ i)
if(!flag[i])
if(DFS(matrix, visited, i, flag))
return false;
return true;
}

bool DFS(vector<unordered_set<int>> &matrix, unordered_set<int> &visited, int b,
vector<bool> &flag)
{
flag[b] = true;
visited.insert(b);
for(auto it = matrix[b].begin(); it != matrix[b].end(); ++ it)
if(visited.find(*it) != visited.end() || DFS(matrix, visited, *it, flag))
return true;
visited.erase(b);
return false;
}

public boolean canFinish(int numCourses, int[][] prerequisites) {
	int[][] matrix = new int[numCourses][numCourses]; // i -> j
	int[] indegree = new int[numCourses];
	for (int i=0; i<prerequisites.length; i++) {
		int ready = prerequisites[i][0];
		int pre = prerequisites[i][1];
	if (matrix[pre][ready] == 0)
	indegree[ready]++; //duplicate case
	matrix[pre][ready] = 1;
	}
	int count = 0;
	Queue<Integer> queue = new LinkedList();
	for (int i=0; i<indegree.length; i++) {
	if (indegree[i] == 0) queue.offer(i);
	}
	while (!queue.isEmpty()) {
	int course = queue.poll();
	count++;
	for (int i=0; i<numCourses; i++) {
	if (matrix[course][i] != 0) {
	if (--indegree[i] == 0)
	queue.offer(i);
	}
	}
	}
	return count == numCourses;
}