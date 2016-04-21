/*
242 Valid Anagram

Given two strings s and t, write a function to determine if t is an anagram of s.

For example,
s = "anagram", t = "nagaram", return true.
s = "rat", t = "car", return false. 

Note:
 You may assume the string contains only lowercase alphabets.

Follow up:
 What if the inputs contain unicode characters? How would you adapt your solution to such case?

*/

/*****
1. 	Anagram 字母重排词.
2.	两表费空间, 一遍加,一边减,检查是否全零.
3.	Unicode用map
*****/

public class Solution {
	public boolean isAnagram(String s, String t) {
		int[] alphabet = new int[26];
		for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
		for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
		for (int i : alphabet) if (i != 0) return false;
		return true;
	}
}