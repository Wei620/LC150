/*
205 Isomorphic Strings

Given two strings s and t, determine if they are isomorphic.

Two strings are isomorphic if the characters in s can be replaced to get t.

All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.

For example,
 Given "egg", "add", return true.

Given "foo", "bar", return false.

Given "paper", "title", return true.

*/

/*****
1.	一一映射, 位置标号法. 标每个字母上次出现的位置. (首次更好, 这样只有当-1时才更新)
2.	不直观, 用 -1表示没出现过更好. 
		Arrays.fill(m1, -1). 
		m1[c1] = i;
3.	也可用hashmap. 256 包含大小写. 其实应该转 c - 'a'

411
1. test里有非字母. 
*****/

public class Solution {
boolean isIsomorphic(String s, String t) { // same position brings equivalence
	int[] m1 = new int[256], m2 = new int[256];
	int n = s.length();
	for (int i = 0; i < n; i++) {
		char c1 = s.charAt(i);
		char c2 = t.charAt(i);
		if (m1[c1] != m2[c2]) return false; //never emerges, 0 == 0
		m1[c1] = i + 1;	// needs 0 to represent the never showing char
		m2[c2] = i + 1;
	}
	return true;
}
}

/*
The main idea is to store the last seen positions of current (i-th) characters in both
strings. If previously stored positions are different then we know that the fact they're
occuring in the current i-th position simultaneously is a mistake. We could use a
map for storing but as we deal with chars which are basically ints and can be used as
indices we can do the whole thing with an array.
*/

public class Solution {
public boolean isIsomorphic(String s1, String s2) {
int[] m = new int[512];
for (int i = 0; i < s1.length(); i++) {
if (m[s1.charAt(i)] != m[s2.charAt(i)+256]) return false;
m[s1.charAt(i)] = m[s2.charAt(i)+256] = i+1;
}
return true;
}
}