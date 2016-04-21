/*
244 Shortest Word Distance II

This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = “coding”, word2 = “practice”, return 3.
 Given word1 = "makes", word2 = "coding", return 1.

Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.


Hints:
 Use a hash table mapping word to index list.
*/

/*****
1. 重复多次, 固定空间, 查找快速- hashmap
2. 可能重复, str->list. 
3. 两list都已排序, 求最短距离. 不要两重循环! merge排序,相邻距离短.

293
1. 	merge的像 skyline题(有x限制,只有出list才能比)
		此题循环形式很好.
		
411
1. skyLine 有队首相等的情况.
2. 此题在对中比较即可, 不用出队.
*****/
public class WordDistance {
	private Map<String, List<Integer>> map;
	public WordDistance(String[] words) {
		map = new HashMap<String, List<Integer>>();
		for(String str : words){
			map.put(str, new ArrayList<Integer>());
		}
		for(int i = 0; i < words.length; i++) {
			map.get(words[i]).add(i);
		}
	}
	
	public int shortest(String word1, String word2) {
		List<Integer> list1 = map.get(word1); // in ascengding order
		List<Integer> list2 = map.get(word2);
		int ret = Integer.MAX_VALUE;
		int i = 0, j = 0;
		while(i < list1.size() && j < list2.size()) {
			int index1 = list1.get(i), index2 = list2.get(j);
			if(index1 < index2) {
				ret = Math.min(ret, index2 - index1);
				i++;
			} else {// > 
				ret = Math.min(ret, index1 - index2);
				j++;
			}
		}
		return ret;
	}
}