/*
208 Implement Trie

Implement a trie with insert, search, and startsWith methods. 

Note:
 You may assume that all inputs are consist of lowercase letters a-z. 

*/

http://www.programcreek.com/2014/05/leetcode-implement-trie-prefix-tree-java/
/*****
1. 26叉树 prefix tree
2. 从TrieNode root.children[] 找到当前字母的节点。
3. seachnode（str), 找str最后一个字母对应的节点。 利用结果查word 或 prefix是否存在。
4. 整词插入. 查词,前缀,都是找最后一个字母所在的TreeNode节点. Null or Leaf?

411
1. 构造调用构造。
*****/

//411
class TrieNode {
    // Initialize your data structure here.
    boolean isLeaf = false;
    char c;
    TrieNode[] children = new TrieNode[26];
    
    public TrieNode(char c){this.c = c;}
    public TrieNode(){this(' ');}
}

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        char[] cArr = word.toCharArray();
        TrieNode curr = root;
        for(int i = 0; i < cArr.length; i++){
            char c = cArr[i];
            if(curr.children[c - 'a'] == null){
                curr.children[c - 'a'] = new TrieNode(c);
            }
            curr = curr.children[c - 'a'];
        }
        curr.isLeaf = true;
    }
    
    private TrieNode searchEnd(String word){
        char[] cArr = word.toCharArray();
        TrieNode curr = root;
        for(int i = 0; i < word.length(); i++){
            int idx = cArr[i] - 'a';
            if(curr.children[idx] == null) return null;
            curr = curr.children[idx];
        }
        return curr;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode end = searchEnd(word);
        return end != null && end.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        TrieNode end = searchEnd(prefix);
        return end != null;
    }
}


class TrieNode {
    char c;
    TrieNode[] children = new TrieNode[26];
    boolean isLeaf;
	
    public TrieNode() {}
 
    public TrieNode(char c){
        this.c = c;
    }
}

public class Trie {
    private TrieNode root;
 
    public Trie() {
        root = new TrieNode();
    }
 
    // Inserts a word into the trie.
    public void insert(String word) {
        TrieNode curr = root; 
        for(int i=0; i<word.length(); i++){
            TrieNode[] children = curr.children;
			char c = word.charAt(i); 
            TrieNode t = children[c - 'a'];
			
			if(t == null){
				t = new TrieNode(c);
                children[c - 'a'] = t;
            }
			curr = t;
 
            //set leaf node
            if(i == word.length() - 1)
                t.isLeaf = true;
        }
    }
 
    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode t = searchNode(word);
		return t != null && t.isLeaf
    }
 
    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }
 
	// Search the node for the last char in str.
    public TrieNode searchNode(String str){
        TrieNode curr = root; 
        TrieNode t = null;
        for(int i=0; i<str.length(); i++){
			TrieNode[] children = curr.children;
            char c = str.charAt(i);
			t = children[c - 'a'];
            if(t == null) break；
            children = t.children;
        } 
        return t;
    }
}