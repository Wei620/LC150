/*
293 Flip Game

You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
] 

If there is no valid move, return an empty list [].
*/

/* ****
1. 利用indexOf("++",i), i = 0 to length()-2;
2. 利用substring

411
1. 记得更新 i = j, 否则会有重复.
*****/
//411
public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List list = new ArrayList();
        for(int i = 0; i < s.length(); i++){// s.length-2 is the end
            int j = s.indexOf("++",i);
            if(j != -1){
                list.add(s.substring(0,j) + "--" + s.substring(j+2));
                i = j;
            }
            else break;
        }
        return list;    
    }
}