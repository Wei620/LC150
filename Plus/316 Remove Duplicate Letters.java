/*
316 Remove Duplicate Letters

Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results. 

Example:


Given "bcabc"
 Return "abc" 

Given "cbacdcbc"
 Return "acdb" 

*/

/*
Given the string s, the greedy choice is the smallest s[i], s.t. the suffix s[i .. ] contains all the unique letters. (Note that, when there are more than one smallest s[i], we choose the leftmost one. Why? Simply consider the example: "abcacb".)

After determining the greedy choice s[i], we get a new string s' from s by
1. removing all letters to the left of s[i],
2. removing all s[i]'s from s.
We then recursively solve the problem w.r.t. s'.
The runtime is O(26 * n) = O(n).
*/

/*****
1. 重复字母，用map(数组[26])， 先统计词频。
2. 扫描找最小字母， 直至map[c - 'a'] == 1. 不能再往后找最小字母（pos），否则丢失c。
		暗示 c前所有 map[]>2。 可以把最小字母前的部分砍掉，
			从pos开始递归。后面的pos，从递归结果里再扣除。	

*****/

public class Solution {
	public String removeDuplicateLetters(String s) {
		int[] cnt = new int[26];
		int pos = 0; // the position for the smallest s[i]
		for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
		for (int i = 0; i < s.length(); i++) {
			char ch = s.charAt(i);
			if (ch < s.charAt(pos)) pos = i;
			if (--cnt[ch - 'a'] == 0) break; // before this, all letters will be dups.
		}
		//But we want letter order.
		return s.length() == 0 ? "" : 
					s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
	}
}

/*
思想是：维护一个栈Stack<Character> st来存最后的结果，先scan一次input，构造一个字典int[] count，记录每个char出现次数。然后进行第二次scan,用st来存最后在string里的character

这次scan的时候，需要维护一个boolean[] visited, 来记录每个字符是否在当前储存结果的stack里。因为每个字符在结果里只会出现一次，所以如果visited显示某个字符已经在stack里，就不要再添加同样字符了

开始scan，这时候count数组意义变成：remain input里各个字符remain的情况。

遇到一个字符，

1. count数组对应次数减一

2. 检查是否result stack里已经存在这个字符c，如果存在，不再加入（continue就好了）

3. 尝试把这个字符c加入stack，就需要检查当前栈顶往下依次各个字符是不是比c大，而且后面还有这些字符（不是最后一次出现），如果满足这两个条件，可以pop它们。不满足就不能pop

4. stack的历史遗留问题处理完毕后，c入栈，同时设置visited

这样最后stack里面就是字符序的possible result
*/

public class Solution {
    public String removeDuplicateLetters(String s) {
        if (s==null || s.length()==0) return "";
        Stack<Character> st = new Stack<Character>();
        StringBuffer sb = new StringBuffer();
        boolean[] visited = new boolean[26];
        int[] count = new int[26];
        
        for (int i=0; i<s.length(); i++) {
            count[(int)(s.charAt(i) - 'a')]++;
        }
        
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            count[(int)(c - 'a')]--;
            if (visited[(int)(c-'a')]) continue; //stack already has this char, do not add this to result stack
            while (!st.isEmpty() && st.peek()>c && count[(int)(st.peek()-'a')]>0) { //check if we should delete chars from stack
                visited[(int)(st.peek() - 'a')] = false;
                st.pop();
            }
            st.push(c);
            visited[(int)(c-'a')] = true;
        }
        
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }
        sb = sb.reverse();
        return sb.toString();
    }
}