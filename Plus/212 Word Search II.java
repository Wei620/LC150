/*
212 Word Search II
Given a 2D board and a list of words from the dictionary, find all words in the board. 

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word. 

For example,
 Given words = ["oath","pea","eat","rain"] and board = 
[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]

Return ["eat","oath"]. 

Note:
 You may assume that all inputs are consist of lowercase letters a-z. 

click to show hint.


You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.

*/

/*****
1. Trie 可快速排除不在树上的节点。用于DFS的early termintation.
2. DFS 的当前路径 即 prefix。
3. Base case 1. 出界 2. 已访问过。
4. DFS里 visited一定要还原， 该节点可由别的起始点再次访问
*****/

public class Solution {
	Set<String> res = new HashSet<String>();
	public List<String> findWords(char[][] board, String[] words) {
		Trie trie = new Trie();
		for (String word : words) {
			trie.insert(word);
		}
		int m = board.length;
		int n = board[0].length;
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				dfs(board, visited, "", i, j, trie);// visited will be reset each round.
			}
		}
		return new ArrayList<String>(res);
	}
	public void dfs(char[][] board, boolean[][] visited, String prefix, int x, int y
	, Trie trie) {
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) return;
		if (visited[x][y]) return;
		
		prefix += board[x][y];
		
		if (!trie.startsWith(prefix)) return; // early termintation. 
		if (trie.search(prefix)) {	// 并未返回 继续查找
			res.add(prefix);
		}
		visited[x][y] = true;
		dfs(board, visited, prefix, x - 1, y, trie);
		dfs(board, visited, prefix, x + 1, y, trie);
		dfs(board, visited, prefix, x, y - 1, trie);
		dfs(board, visited, prefix, x, y + 1, trie);
		visited[x][y] = false; // can be revisited when comparing to other starting position.
	}
}