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
    String[] QueenStrings; // for a solution
    List<String[]> res; // solutions
    StringBuilder sb; // for a row
    List<Integer> QueensPos; // store solution
    
    /**
     * 
     */
    public List<String[]> solveNQueens(int n) {
        res = new ArrayList<String[]>();
        if (n <= 0) return res;
        total = n;
        QueenStrings = new String[n];
        sb = new StringBuilder();
        for (int i = 0; i < n; i++) sb.append(".");
        QueensPos = new ArrayList<Integer>();
        limit = (1 << n) - 1;
        dfs(0, 0, 0);
        return res;
    }
    
    /**
     * Save QueensPos of each line in a list
     * Retrieve the QueensPos of each line when there is a solution
     */
    public void dfs(int h, int r, int l) {
        if (h == limit) {
            for (int i = QueensPos.size() - 1; i >= 0; i--) {
                int newQueenBitPos = h - QueensPos.get(i); // last position
				int newQueenIdx = 0;
				for(int bitMask = 1; bitMask < newQueenBitPos; bitMask <<= 1){
					newQueenIdx++
				}
                QueenStrings[i] = new StringBuilder(sb).setCharAt(newQueenIdx, 'Q').toString();
				h = QueensPos.get(i);
            }
            res.add(QueenStrings); // add to result
            QueenStrings = new String[total]; // reset QueenStrings
            return;
        }
        QueensPos.add(h); // add then remove
        int pos = limit & (~(h|r|l)); // set unsaved pos to zero, note ~
        while (pos != 0) {
            int p = pos & (-pos); // rightmost 1
            pos -= p; // note how to place a queen
            dfs(h + p, (r + p) << 1, (l + p) >> 1);
        }
        QueensPos.remove(QueensPos.size() - 1); // remove added h
    }
}