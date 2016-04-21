/*
245 Shortest Word Distance III

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,

Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = "makes", word2 = "coding", return 1. Given word1 = "makes", word2 = "makes", return 3.

Note:

You may assume word1 and word2 are both in the list.

*/

/*****
1. 对于同一个字, 两遍更新法, i2是i1的备份.
2(旧). 初始 long dist, i1 = dist, i2 = -i1
*****/

public class Solution {
	public int shortestWordDistance(String[] words, String word1, String word2) {
		int min = Integer.MAX_VALUE, i1 = -1, i2 = -1;
		for (int i=0; i<words.length; i++) {
			if (words[i].equals(word1)){
				if(word1.equals(word2)) i2 = i1; // back up
				i1 = i;
			}
			else if (words[i].equals(word2)) {
				i2 = i;
			}
			//if(i1 != -1 && i2 != -1){
			if(i1 == i || i2 == i){ //just updated.
				min = Math.min(min, Math.abs(i1 - i2));
			}
		}
		return min;
	}
	
	/*
	i1 and i2 are the indexes where word1 and word2 were last seen. Except if they're
	the same word, then i1 is the previous index.
	*/
	public int shortestWordDistance(String[] words, String word1, String word2) {
		long dist = Integer.MAX_VALUE, i1 = dist, i2 = -dist;
		for (int i=0; i<words.length; i++) {
			if (words[i].equals(word1))
				i1 = i;
			if (words[i].equals(word2)) {
				if (word1.equals(word2))
					i1 = i2;
				i2 = i;
			}
			dist = Math.min(dist, Math.abs(i1 - i2));
		}
		return (int) dist;
	}
}
