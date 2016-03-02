import java.util.*;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard
 * such that no two queens attack each other.
 * 
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * 
 * Each solution contains a distinct board configuration of the n-queens'
 * placement, where 'Q' and '.' both indicate a queen and an empty space
 * respectively.
 * 
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * 
 * [
 *  [".Q..",  // Solution 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 * 
 *  ["..Q.",  // Solution 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 
 * Tags: Backtracking, Bit Manipulation
 
 http://www.geeksforgeeks.org/backtracking-set-3-n-queen-problem/
 */
class NQueens {
    public static void main(String[] args) {
        
    }
    
    int limit, total; // limit is all ones, total is # of rows
    StringBuilder sb; // for a row
    
    /**
     * 
     */
    public List<String[]> solveNQueens(int n) {
        List<String[]> res = new ArrayList<String[]>();
        if (n <= 0) return res;
        total = n;
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(".");
        limit = (1 << n) - 1;
        dfs(0, 0, 0, new ArrayList<Integer>(), res);
        return res;
    }
    
    /**
     * Save hs of each line in a list
     * Retrieve the hs of each line when there is a solution
     */
    public void dfs(int h, int r, int l, List<Integer> hs, List<String[]> res) {
        if (h == limit) {
            String[] qs = new String[total];
            for (int i = hs.size() - 1; i >= 0; i--) {
                int hDiff = h - hs.get(i); // last position
				int newQueenIdx = 0;
                while(hDiff > 1){// 0th
                    hDiff >> 1;
                    newQueenIdx++;
                }
                qs[i] = new StringBuilder(sb).setCharAt(newQueenIdx, 'Q').toString();
				h = hs.get(i);
            }
            res.add(qs); // add to result
            return;
        }
        hs.add(h); // add then remove
        int pos = limit & (~(h|r|l)); // set unsaved pos to zero, note ~
        while (pos != 0) {
            int p = pos & (-pos); // rightmost 1
            pos -= p; // note how to place a queen
            dfs(h + p, (r + p) << 1, (l + p) >> 1);
        }
        hs.remove(hs.size() - 1); // remove added h
    }
}