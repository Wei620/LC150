/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once.
 * 
 * For example,
 * Given board =
 * [
 *   ["ABCE"],
 *   ["SFCS"],
 *   ["ADEE"]
 * ]
 * 
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * 
 * Tags: Array, Backtracking
 */
class WordSearch {
    public static void main(String[] args) {
        char[][] board = {
            {'A', 'B', 'C', 'E'},
            {'S', 'F', 'C', 'S'},
            {'A', 'D', 'E' ,'E'}
        };
        String word = "SEE";
        // String word = "ABCCED";
        // String word = "ABCB";
        System.out.println(exist(board, word));
    }
    
	/* ****
	1. 	四向DNS可以显式表达，更清晰。
	2.	排除越界。
	3.  要求不能重复走，所以要mark和reset.
	*****/
    /**
     * Use boolean array to remember whether a word is used
     * Traverse each position and do DFS
     */
    public static boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0 || word == null) return false;
        if (word.length() == 0) return true;

        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                if (dfs(board, i, j, word, 0)) return true;
        return false;
    }
    
    /**
     * Remember position in board
     * Remember position in matched word
     */
    // String word, int n - target; i, j - starting pos, board - set    
    public static boolean dfs(char[][] board, int i, int j, String word, int n) {
        if (word.length() == n) return true;
        // outside board or doesn't match
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(n)) return false;
        board[i][j] = '#'; // mark 
        // search 4 connectivity
        boolean res = 	dfs(board, i - 1, j, word, n + 1) ||
						dfs(board, i + 1, j, word, n + 1) ||
						dfs(board, i, j - 1, word, n + 1) ||
						dfs(board, i, j + 1, word, n + 1);
        
		board[i][j] = word.charAt(n);// reset mark
        return res;
    }
}