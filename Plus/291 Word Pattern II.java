/*
291 Word Pattern II

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
1.pattern = "abab", str = "redblueredblue" should return true.
2.pattern = "aaaa", str = "asdasdasdasd" should return true.
3.pattern = "aabb", str = "xyzabcxzyabc" should return false.



Notes:
 You may assume both pattern and str contains only lowercase letters.
*/

/*****
1. 更复杂的回溯,双指针. (add operator 双选项).遍历各种情况,找到结束后,回溯到根就结束.
2. 一一映射,map加其值域set. map.key里有c, 看str是不是startwith(val). 是, i+1, j+val.length递归
							   没有, 对str开始回溯substring(i, k+1). 跳过值域set里已有的字符.
3. 值域相当于visted的strings, 回溯前后要对map和值域set修改.
4. 终止条件.双指针全走完-匹配,只有一个走完-false.

303
1. map查有pattern, set剔除已有work的情况(因为但前pat不在map里).
2. 注意map有pat, ET !str.startwith(val, i)
				否则, 递归(i+val.lentth(), j+1)
				
411
1. hashmap 有keySet, 但没valueSet, 有values但是collection
   set是必须的
2. c在不在map里,分情况讨论
3. 构成的s在不在值域里? 在, continue
4. 只要找到一个就行,所以在remove前可以return

*****/

public class Solution {
	public boolean wordPatternMatch(String pattern, String str) {
		Map<Character, String> map = new HashMap<>();
		Set<String> set = new HashSet<>();
		return isMatch(str, 0, pattern, 0, map, set);
	}

	// set - existing mapped string
	boolean isMatch(String str, int i, String pat, int j, Map<Character, String> map, Set<String> set) {
		// base case
		if (i == str.length() && j == pat.length()) return true;
		if (i == str.length() || j == pat.length()) return false;
		
		// get current pattern character
		char c = pat.charAt(j);
		
		// if the pattern character exists
		if (map.containsKey(c)) {
			String s = map.get(c);
			// then check if we can use it to match str[i...i+s.length()]
			if (!str.startsWith(s, i)) {
				return false;
			}
			// if it can match, great, continue to match the rest
			return isMatch(str, i + s.length(), pat, j + 1, map, set);
		}
		
		// pattern character does not exist in the map
		for (int k = i; k < str.length(); k++) {
			String p = str.substring(i, k + 1);
			if (set.contains(p)) {
				continue;
			}
			// create it
			map.put(c, p);
			set.add(p);
			// continue to match the rest
			if (isMatch(str, k + 1, pat, j + 1, map, set)) {
				return true;
			}
			// backtracking
			map.remove(c);
			set.remove(p);
		}
		// we've tried our best but still no luck
		return false;
	}
}