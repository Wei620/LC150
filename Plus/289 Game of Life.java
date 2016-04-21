/*
289 Game of Life

According to the Wikipedia's article: "The Game of Life, also known simply as Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970."

Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following four rules (taken from the above Wikipedia article):

Any live cell with fewer than two live neighbors dies, as if caused by under-population.
Any live cell with two or three live neighbors lives on to the next generation.
Any live cell with more than three live neighbors dies, as if by over-population..
Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
Write a function to compute the next state (after one update) of the board given its current state.

Follow up: 
Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. How would you address these problems?
*/

/*****
1. 长题简单逻辑。
2. int阵 复用第2位存下轮状态， 默认为dead（0）
3. live -> live count = 2, 3
   dead -> live count = 3
4. count live neighbor 不算自己， 体现在cnt的初始值。 注意，仅累加第一位 board[][]&1.

303
1. 三个LN,一定aLive, 两个LN,keep alive.
2. 数LNs,所有都要&1, 因为第二位可能已更新.
*****/
void gameOfLife(int[][] board) {
    if(board == null || board.length == 0) return;
    int m = board.length, n = board[0].length;
    
    for (int i=0; i<m; ++i) {
        for (int j=0; j<n; ++j) {
            int count = liveNeighbors(board, i, j);
            // by default, -> dead. so i don't care dead -> dead 
            // live -> live count = 2, 3
            // dead -> live count = 3
            
			/*
            if(count | board[i][j] == 3){
                board[i][j] = 2; // don't care 1st bit
            }*/
			if(count == 3 || (count == 2 && board[i][j] == 0))
				board[i][j] |= 2.
        }
    }
    for (int i=0; i<m; ++i)
        for (int j=0; j<n; ++j)
            board[i][j] >>= 1;
}

int liveNeighbors(int[][] board, int i, int j){
    int m = board.length, n = board[0].length;
    int ps = Math.max(0, i-1), pe = Math.min(m - 1, i + 1);
    int qs = Math.max(0, j-1), qe = Math.min(n - 1, j + 1);
	
	int cnt= -(board[p][q] & 1); // 2nd bit may have been modified.
    for(int p = ps; p <= pe; p++){
        for(int q = qs; q <= qe; q++){
            cnt += board[p][q] & 1;
        }
    }
}

/*
Since the board has ints but only the 1-bit is used, I use the 2-bit to store the new
state. At the end, replace the old state with the new state by shifting all values one
bit to the right.

To solve it in place, we use 2 bits to store 2 states:
[2nd bit, 1st bit] = [next state, current state]
- 00 dead (current) -> dead (next)
- 01 live (current) -> dead (next)
- 10 dead (current) -> live (next)
- 11 live (current) -> live (next)
*/
