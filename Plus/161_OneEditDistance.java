/*
161. One Edit Distance

Given two strings S and T, determine if they are both one edit distance apart.

Subscribe to see which companies asked this question

*/
// T3
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int m = s.length(), n = t.length();
        if(m > n) return isOneEditDistance(t, s);
        if(n - m > 1) return false;
        for (int i = 0; i < m; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                return s.substring(i + (m == n? 1 : 0)).equals(t.substring(i + 1)); //m+1 == n
            }
        }
        return m + 1 == n; 
    }
}

public boolean isOneEditDistance(String s, String t) {
    int m = s.length(), n = t.length();
    if (m > n) return isOneEditDistance(t, s);
    if (n - m > 1) return false;
    // shift = 0 or 1
    int i = 0, shift = n - m;
    while (i < m && s.charAt(i) == t.charAt(i)) i++;
    if (i == m) return shift == 1;
    if (shift == 0) i++;
    while (i < m && s.charAt(i) == t.charAt(i + shift)) i++;
    return i == m;
}