/*
282 Expression Add Operators

Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary) +, -, or * between the digits so they evaluate to the target value.
Examples: 
"123", 6 -> ["1+2+3", "1*2*3"] 
"232", 8 -> ["2*3+2", "2+3*2"]
"105", 5 -> ["1*0+5","10-5"]
"00", 0 -> ["0+0", "0-0", "0*0"]
"3456237490", 9191 -> []

Understand the problem:
A classic DFS problem. 
*/

/* ****
1. Back tracking. 提取数字可选, 提取后的符号可选. substring(pos, i+1).
2. 切分数字是避免02等情况.
3. 本次累加结果(可正负),直接在target里预扣除. 作为lastIncrease参数pass给下一轮.对下一轮乘法的情况,需要在target补偿回来.

303.
1. 可加减,结果没有单调性,不能根据target的值,early termination.
2. 字符数字,用Long.parseLong(str)

411
1. String immutable 不用add, remove, new arraylist
2. 计算器要一位一位扫, 这个可用parser
*****/

public class Solution {
    public List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
            helper(rst, "", num, target, 0, 0);
        return rst;
    }
    
    // lastIncrease - the temp result was just accumulated
    public void helper(List<String> rst, String path, String num, int target, int pos, long lastIncrease){
        if(pos == num.length()){
            if(target == 0) rst.add(path);
            return;
        }
        
        for(int i = pos; i < num.length(); i++){
            if(num.charAt(pos) == '0' && i > pos) break; // no 02 as a number
            long cur = Long.parseLong(num.substring(pos, i + 1));
            if(pos == 0){
                helper(rst, path + cur, num, target - cur, i + 1, cur);
            }
            else{
                helper(rst, path + "+" + cur, num, target - cur, i + 1, cur); 
                helper(rst, path + "-" + cur, num, target + cur, i + 1, -cur);
                helper(rst, path + "*" + cur, num, target - lastIncrease * (cur - 1), i + 1, lastIncrease * cur ); // "lastIncrease * 1" is a compensation for last round.
            }
        }
    }
}

/*
This problem has a lot of edge cases to be considered:
1. overflow: we use a long type once it is larger than Integer.MAX_VALUE or minimum, we get over it.
2. 0 sequence: because we can't have numbers with multiple digits started with zero, we have to deal with it too.
3. a little trick is that we should save the value that is to be multiplied in the next recursion.
*/