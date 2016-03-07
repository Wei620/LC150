/*
159. Longest Substring with At Most Two Distinct Characters

Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = “eceba”,

T is "ece" which its length is 3.
*/
//My implementation
public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if(s.length() == 0) return 0;
        int[] count = new int[256];
        int start = 0, len = 1, numOfDN = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            count[c]++;
            if(count[c] == 1){
                numOfDN++;
                while(numOfDN > 2){
                    char cs = s.charAt(start++);
                    if(--count[cs] == 0){
                        numOfDN--;
                    }
                }
            }
            int curLen = i - start + 1;
            len = curLen > len? curLen : len;
        }
        return len;
    }
}


public int lengthOfLongestSubstringTwoDistinct(String s) {
    int i = 0, j = -1, maxLen = 0; // i: head, j: tail
    for (int k = 1; k < s.length(); k++) {
        if (s.charAt(k) == s.charAt(k - 1)) continue;
        if (j >= 0 && s.charAt(j) != s.charAt(k)) {
            maxLen = Math.max(k - i, maxLen);
            i = j + 1;
        }
        j = k - 1;
    }
    return Math.max(s.length() - i, maxLen);
}