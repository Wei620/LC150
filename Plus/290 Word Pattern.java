/*
290 Word Pattern

Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.

Examples:
pattern = "abba", str = "dog cat cat dog" should return true.
pattern = "abba", str = "dog cat cat fish" should return false.
pattern = "aaaa", str = "dog cat cat dog" should return false.
pattern = "abba", str = "dog dog dog dog" should return false.
Notes:
You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
*/

/* ****
1. 一一映射， 用map一次添加两个entry
	a->dog, dog->a. 检查时麻烦。
	a->i， dog->i 简明， 如果存在 put后返回上次的位置。
2. early termination. 两组长度不等。

303
1. 字母解码同题, 专业词 (?). 换成 String->int的map, 字母可用数组.
	数组-两个
	一个Map 碰上 单字母词于pattern同字母,重复出现,必错.
		改为1map,1数组才行!

411
1. 直接put如果不存在key,返回null(Integer), 会unwrap成0. 不如加判断.
*****/
//411
public class Solution {
    public boolean wordPattern(String pattern, String str) {
        int[] pMap = new int[128];
        Arrays.fill(pMap, -1);
        HashMap<String, Integer> sMap = new HashMap<>();
        
        char[] cArr = pattern.toCharArray();
        String[] words = str.split(" ");
        if(words.length != cArr.length) return false;
        
        for(int i = 0; i < cArr.length; i++){
            int lastIdxP = pMap[cArr[i]];
            int lastIdxW = sMap.containsKey(words[i])? sMap.get(words[i]) : -1;
            if(lastIdxP != lastIdxW) return false;
            pMap[cArr[i]] = i;
            sMap.put(words[i], i);
        }
        return true;
    }
}

// one-to-one map
public boolean wordPattern(String pattern, String str) {
    String[] words = str.split(" ");
    if (words.length != pattern.length())
        return false;
    Map index = new HashMap();
    for (Integer i=0; i<words.length; ++i)
        if (index.put(pattern.charAt(i), i) != index.put(words[i], i))
            return false;
    return true;
}

/*
I go through the pattern letters and words in parallel and compare the indexes
where they last appeared.
*/