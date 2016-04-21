/*
318 Maximum Product of Word Lengths

Given a string array words, find the maximum value of length(word[i]) * length(word[j]) where the two words do not share common letters. You may assume that each word will contain only lower case letters. If no such two words exist, return 0. 

Example 1:


Given ["abcw", "baz", "foo", "bar", "xtfn", "abcdef"]
 Return 16
 The two words can be "abcw", "xtfn". 

Example 2:


Given ["a", "ab", "abc", "d", "cd", "bcd", "abcd"]
 Return 4
 The two words can be "ab", "cd". 

Example 3:


Given ["a", "aa", "aaa", "aaaa"]
 Return 0
 No such pair of words. 

*/

/*****
1.公用字母， 属于频率统计的变种，但只要boolean[26], 为了方便比较，int 32bit即可
	word[i] & word[j] == 0, 没有公用字母。
*****/

public class Solution {
    public int maxProduct(String[] words) {
        if (words == null || words.length <= 1) {
            return 0;
        }
         
        int n = words.length;
        int[] encodedWords = new int[n];
         
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                encodedWords[i] |= (1 << (c - 'a'));
            }
        }
         
        int maxLen = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((encodedWords[i] & encodedWords[j]) == 0) {
                    maxLen = Math.max(maxLen, 
                        words[i].length() * words[j].length());
                }
            }
        }
         
        return maxLen;
    }
}

/*
Since each word contains 26 characters in lower case only. We can use a bit map to encode the string. Since we only need 26 bits for a word, it is enough to use an integer to encode a string. 
*/