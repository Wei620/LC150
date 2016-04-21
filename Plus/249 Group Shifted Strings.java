/*
249 Group Shifted Strings

Given a string, we can “shift” each of its letter to its successive letter, for example: “abc” -> “bcd”. We can keep “shifting” which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example,

given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list’s elements must follow the lexicographic order.
*/

/*****
1.	找基准offset, 字符减找到原型. hashmap 原型->词
2.	log数学, 分组排序快.

411
1. 记得组内排序.
*****/
public class Solution {
	public List<List<String>> groupStrings(String[] strings) {
		List<List<String>> result = new ArrayList<List<String>>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String str : strings) {
			String key = findBase(str);
			if (!map.containsKey(key)) {
				map.put(key, new ArrayList<String>());
			}
			map.get(key).add(str);
		}
		for (String key : map.keySet()) {
			List<String> list = map.get(key);
			Collections.sort(list);
			result.add(list);
		}
		return result;
	}
	
	private String findBase(String str){
		if(str == null ||str.length() == 0) return "";
		str = str.toLowerCase();
		int offset = str.charAt(0) - 'a';
		String base = "";
		for (int i = 0; i < str.length(); i++) {
			base += (char) (str.charAt(i) - offset);
		}
		return base;
	}
}

