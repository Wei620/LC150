/*
292 Nim Game

You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, the last stone will always be removed by your friend.

*/

/*
Theorem: The first one who got the number that is multiple of 4 (i.e. n % 4 ==
0) will lost, otherwise he/she will win.

Proof:
1. the base case: when n = 4 , as suggested by the hint from the problem, no
matter which number that that first player,
*/

/* ****
1. 基本情况, n = 4.
	那最后一个子赢
	对方聪明,不会让你剩3,2,1, 只有上手就是3,2,1
		赢法,放对方永远剩4的倍数
2. 重复基本.

411.
1. 假设每次拿[1,k]个球,拿到最后的赢, 赢区(剩余球数) [1, k], 谁先进入谁赢.
	先到 k+1 输. 
		重复base 所以 return n % (k + 1) != 0;
1. 拿到1赢 => 拿到0输.
  0 [1, k] k+1 [k+2, 2k+1] 2k+2 .....
  return n % (k+1) != 0

2  拿到1输
    1 [2, k+1] k+2 [k+3, 2k+2] 2k+3 ....
	return (n-1)%(k+1) != 0
*****/

public boolean canWinNim(int n) {
    return n % 4 != 0 ;
}