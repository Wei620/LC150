/*
288 Unique Word Abbreviation

An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:
a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n
Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.
Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]

isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true
*/

/*****
1. 给出的词可能是字典外的

411.
1. word的abbre是不是唯一的
	1) 字典里没有词产生该abbre, true
	2) 字典里只有一个词产生该 abbre, word == dict词?
	3) 字典里多个词产生该abbre, false
2) 区分三种情况, 用map, set不行
	3) 放入一个word不可能取到的词 比如 ""
*****/


public class ValidWordAbbr {
    Map<String, String> map = new HashMap<>();
    public ValidWordAbbr(String[] dictionary) {
        for (String word : dictionary) {
            String key = genKey(word);
            if (map.containsKey(key)) {
                map.put(key, "");  //dup -> "", 
            } else {
                map.put(key, word);
            }
        }
    }
    public boolean isUnique(String word) {
        String key = genKey(word);
        return !map.containsKey(key) || map.get(key).equals(word); // key not included is also unique.
    }//
    private String genKey(String word) {
        String key = word.charAt(0) + Integer.toString(word.length() - 2) + word.charAt(word.length() - 1);
        return key;
    }
}