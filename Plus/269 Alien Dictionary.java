/*
269 Alien Dictionary

There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, wherewords are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
 

The correct order is: "wertf".

Note:

You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
*/

/*****
1. 	给出多个前后关系(相邻词间的同字母), 按前后排序 - 拓扑排序. 
2. 	有重复的前后关系,用 char->hashset的hashmap,否则 List<>[], 注hashset不是list.
	建map的同时,统计preCnt[26], 用以找到无predecessor的节点.
			   用chars (hashset)统计所有字母, 最后可以得到总字母数.(和order的大小比较,已决定能否全排完)	
3.  省空间, chars删成无前继子集
4.  拓扑排序后看能不能排完.

*****/

String alienOrder(List<Integer> words){
	Map<Character, Set<Character>> suc = new HashMap<Character,Set<Character>>(){{
		for(char c = 'a'; c <= 'z'; c++){
			put(c, new HashSet<Character>());
		}
	}};
	int[] preCnt = new int[26];
	
	Set<Character> chars = new HashSet<Character>(); // all characters.
	String s = "";
	
	for(String t : words){
		//chars.addAll(Array.asList(t.toCharArray()));
		for(int i = 0; i < Math.min(s.length(), t.length()); i++){
			char a = s.charAt(i), b = t.charAt(i); // s is the last word.
			if(a != b){
				suc.get(a).add(b);
				preCnt[b - 'a']++;
				break;
			}
		}
		s = t;
	}
	
	int size = chars.size();
	for(int i = 0; i < preCnt.length; i++){
		if(preCnt[i] != 0){
			chars.remove(i + 'a');
		}
	} // chars with no predecessor
	
	String order;
	while(!chars.isEmpty()){
		char a = chars.iterator().next();
		chars.remove(a);
		order += a;
		for(char b: suc.get(a)){
			if(--preCnt[b - 'a'] == 0){
				chars.add(b);
			}			
		}
	}
	return order.length() == size ? order : "";	
}