/*
294 Flip Game II
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip twoconsecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to determine if the starting player can guarantee a win.

For example, given s = "++++", return true. The starting player can guarantee a win by flipping the middle "++" to become "+--+".

Follow up:
Derive your algorithm's runtime complexity.

*/

/*****
1. 递归. 用I的原理
2. base. indexOf() == -1, 输
3. 遍历I找到的所有下一步情况next, 如果使对手输(!canWin(next)),我赢. 
							找不到这样, 怎么走对方赢, 我输.
303
1. 对方很聪明,不能把胜棋给他.
   我也很聪明,枚举所有下一步.
		下一步只要有对手输的情况, 就走那一步,必胜.
						  否则, 走哪步,对方胜.
		对方输无法直接看出来, 递归 !canWin(next).
2. 注意base, 整个不存在"++"

411.
1. 要更新 i =j						  
*****/

public class Solution {
    public boolean canWin(String s) {
        //if(s.indexOf("++") == -1) return false;
        for(int i = 0; i < s.length(); i++){
            int j = s.indexOf("++",i);
            if(j != -1){
                String next = s.substring(0,j) + "--" + s.substring(j+2);
    			i = j;
                if(!canWin(next)){
                    return true;
                }           
            }
            else break;
        }
        return false; 
    }
}