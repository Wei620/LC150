/**
 * Given a string s and a dictionary of words dict, add spaces in s to
 * construct a sentence where each word is a valid dictionary word.
 * 
 * Return all such possible sentences.
 * 
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * 
 * A solution is ["cats and dog", "cat sand dog"].
 * 
 * Tags: DP, Backtracking
 */
public class Solution {
    
    /**
     * Memory function
     * Store how a word can be decomposed
     */
    Map<String, List<String>> cache = new HashMap<String, List<String>>();
    
    /**
     * DP, Backtracking
     * Store successful decomposition in a map
     * Get prefix
     * If not in dictionary, just ignore
     * If in dictionary, check current position
     * If reaches the end, add prefix to a solution
     * If within length do the following: 
     * Check whether the cachet of the string is already decomposed
     * If not, backtracking the cachet of the string
     * If yes, get the cacheult from memory function
     * If there is an cacheult, add each word to current solution with front in
     */
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>(); 

        int len = s.length();
        for (int i = 1; i <= len; i++) {
            String pref = s.substring(0, i);
            if (dict.contains(pref)) {
                if (i == len) words.add(pref); // reach the end.
                else {
                    String remain = s.substring(i, len); // remaining string
                    List<String> remainDecomp = cache.containsKey(remain) ?
                        cache.get(remain) : wordBreak(remain, dict); // avoid backtracking if a decomposition is already there
                    //if (remainDecomp != null) {
                        for (String w : remainDecomp) words.add(pref + " " + w);
                        cache.put(remain, remainDecomp); // add to cache
                    //}
                }
            }
        }
        return words;
    }

    /**
     * Backtracking
     * Get prefix first
     * If prefix is in dictionary, check current length
     * If reaches the end, add prefix to cacheult 
     * If not, go ahead and decompose the remain string
     * Get the cacheult list, and concat prefix with those cacheults
     * Add the concatenated string to cacheult and return
     */
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> words = new ArrayList<String>();

        int len = s.length();
        for (int i = 1; i <= len; i++) {
            String pref = s.substring(0, i);
            if (dict.contains(pref)) {
                if (i == len) words.add(pref);
                else {
                    String remain = s.substring(i, len);
                    List<String> remainDecomp = wordBreak(remain, dict);
                    if (remainDecomp != null) { // has decompositions
                        for (String item : remainDecomp) {
                            words.add(pref + " " + item);
                        }
                    }
                }
            }
        }
        return words;
    }
}
