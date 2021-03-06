/*
243 Shortest Word Distance

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.

Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.


Hints:
 Two variable to track the last positions.
*/

/*****
1. 一遍扫描,p, q记录两词最新索引, 若p,q均不为初始值,计算距离.
2. 比较最短.
*****/
public int shortestDistance(String[] words, String word1, String word2) {
	int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;
	for (int i = 0; i < words.length; i++) {
		if (words[i].equals(word1)) p1 = i;
		else if (words[i].equals(word2)) p2 = i;
		//if (p1 != -1 && p2 != -1)
		if (p1 == i || p2 == i) // just updated
			min = Math.min(min, Math.abs(p1 - p2));
	}
	return min;
}