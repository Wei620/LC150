/*
301 Remove Invalid Parentheses

Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

Note: The input string may contain letters other than the parentheses ( and ). 

Examples:

"()())()" -> ["()()()", "(())()"]
"(a)())()" -> ["(a)()()", "(a())()"]
")(" -> [""]

*/

/*****
1.	匹配括号,数个数.
2.	"最小" 想到BFS, 先枚举删一个情况, 再枚举只删两个的情况.
3.  BFS一定要有个visited集合.没visited的才能入队. (入过栈了)

313
1. 数左括号数.
2. BFS, 每次出栈先检查, 再删除一个括号.

411
1. 找到那层后，输出停止。
2. BFS防止重复
*****/
//411
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        // sanity check
        if (s == null) return res;
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        // initialize
        queue.add(s);
        visited.add(s);
        
        while (!queue.isEmpty()) {
            int sz = queue.size();
            boolean isFound = false;
            for(int j = 0; j < sz; j++){
                s = queue.poll();
                if (isValid(s)) {
                    // found an answer, add to the result
                    res.add(s);
                    isFound = true;
                    continue;
                }

                // generate all possible states
                for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren
                    if (s.charAt(i) != '(' && s.charAt(i) != ')')   continue;
                    String t = s.substring(0, i) + s.substring(i + 1); //remove ith
                    if (!visited.contains(t)) {
                        // for each state, if it's not visited, add it to the queue
                        queue.add(t);
                        visited.add(t);
                    }
                }
            }
            if(isFound) break;
        }
        return res;
    }
    
    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
        int count = 0; // "(" left
        for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')') count--;
			if(count < 0) return false;
        }
        return count == 0;
    }
}